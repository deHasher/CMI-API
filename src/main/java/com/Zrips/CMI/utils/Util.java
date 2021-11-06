package com.Zrips.CMI.utils;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Config;
import com.Zrips.CMI.Containers.CMICollision;
import com.Zrips.CMI.Containers.CMIHitBox;
import com.Zrips.CMI.Containers.CMIRay;
import com.Zrips.CMI.Containers.CMIUser;
import com.Zrips.CMI.Containers.itemInfo;
import com.Zrips.CMI.Modules.Animations.AnimationManager;
import com.Zrips.CMI.Modules.Permissions.PermissionsManager.CMIPerm;
import com.Zrips.CMI.Modules.tp.TpManager.TpAction;
import com.google.common.base.Charsets;

import net.Zrips.CMILib.CMILib;
import net.Zrips.CMILib.Colors.CMIChatColor;
import net.Zrips.CMILib.Items.CMIItemStack;
import net.Zrips.CMILib.Items.CMIMaterial;
import net.Zrips.CMILib.Locale.LC;
import net.Zrips.CMILib.Logs.CMIDebug;
import net.Zrips.CMILib.NBT.CMINBT;
import net.Zrips.CMILib.RawMessages.RawMessage;
import net.Zrips.CMILib.Version.Version;

public class Util {

    public boolean Debug = true;

    private HashMap<String, replyResponder> replyMapBySender = new HashMap<String, replyResponder>();
    private HashMap<String, replyResponder> replyMapByReceiver = new HashMap<String, replyResponder>();

    private HashMap<UUID, String> worldCache = new HashMap<UUID, String>();

    static Random random = new Random(System.nanoTime());

    private CMI plugin;

    public Util(CMI plugin) {
	this.plugin = plugin;
	for (World one : Bukkit.getWorlds()) {
	    worldCache.put(one.getUID(), one.getName());
	}
    }

    public class replyResponder {
	private String name;
	private Long time;

	public replyResponder(String name, Long time) {
	    this.name = name;
	    this.time = time;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public Long getTime() {
	    return time;
	}

	public void setTime(Long time) {
	    this.time = time;
	}
    }

    public boolean isNPC(Player player) {
	if (player.hasMetadata("NPC"))
	    return true;
	return false;
    }

    public UUID generateUUID(String playerName) {
	return UUID.nameUUIDFromBytes(("OfflinePlayer:" + playerName).getBytes(Charsets.UTF_8));
    }

    Integer range = null;

    public int getPlayerTrackingRange() {
	return getPlayerTrackingRange(null);
    }

    public int getPlayerTrackingRange(World world) {
	if (range == null) {
	    range = 64;
	    try {
		Object r = null;
		if (world != null)
		    r = Bukkit.spigot().getConfig().get("world-settings." + world.getName() + ".entity-tracking-range.players");
		if (r == null)
		    r = Bukkit.spigot().getConfig().get("world-settings.default.entity-tracking-range.players");
		if (r != null)
		    range = (int) r;
	    } catch (Exception | Error e) {
	    }
	}
	return range;
    }

    @Deprecated
    public void addMessageReplayTo(String target, String sender) {
	addMessageReplyTo(target, sender);
    }

    public void addMessageReplyTo(String target, String sender) {
	replyMapBySender.put(sender, new replyResponder(target, System.currentTimeMillis()));
	replyMapByReceiver.remove(sender);
	replyMapByReceiver.put(target, new replyResponder(sender, System.currentTimeMillis()));
    }

    @Deprecated
    public void removeMessageReplayTo(String sender) {
	removeMessageReplyTo(sender);
    }

    public void removeMessageReplyTo(String sender) {
	replyResponder removed = replyMapByReceiver.remove(sender);
	if (removed != null) {
	    replyResponder got = replyMapBySender.get(removed.getName());
	    if (got != null && got.getName().equalsIgnoreCase(sender))
		replyMapBySender.remove(removed.getName());
	}
    }

    public double getDistance(Location loc1, Location loc2) {
	if (loc1 == null || loc2 == null || loc1.getWorld() != loc2.getWorld())
	    return Integer.MAX_VALUE;

	try {
	    return loc1.distance(loc2);
	} catch (Throwable e) {
	    return Integer.MAX_VALUE;
	}
    }

    public int getMaxWorldHeight(World world) {
	if (world == null)
	    return 256;

	Integer custom = plugin.getConfigManager().getFlyAboveRoofLimitationsMap().get(world.getName());
	if (custom != null && custom > 0)
	    return custom;

	switch (world.getEnvironment()) {
	case NETHER:
	    return 128;
	case NORMAL:
	case THE_END:
	    if (Version.isCurrentEqualOrHigher(Version.v1_17_R1))
		return world.getMaxHeight();
	    return 256;
	default:
	    break;
	}
	return 256;
    }

    public Vector getVector(Location loc1, Location loc2) {

	return null;
    }

    public double getYaw(Location loc1, Location loc2) {
	double dX = loc1.getX() - loc2.getX();
	double dZ = loc1.getZ() - loc2.getZ();
	return Math.toDegrees(Math.atan2(dZ, dX));
    }

    public double getPitch(Location loc1, Location loc2) {
	double dX = loc1.getX() - loc2.getX();
	double dY = loc1.getY() - loc2.getY();
	double dZ = loc1.getZ() - loc2.getZ();
	return Math.toDegrees(Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI);
    }

    @Deprecated
    public String getMessageReplayTo(String sender) {
	return null;
    }

    public String getMessageReplyTo(String sender) {
	return null;
    }

    public String createTextProbgressBar(int bars, int total, int current) {

	return null;
    }

    public Block getHighestBlockAt(Location loc, boolean includeSolids) {

	return null;
    }

    public TreeMap<Double, Entity> getClosestEntities(Location center, int range, double treshold, EntityType type) {

	return null;
    }

    public Entity getClosestEntity(Location center, int range, EntityType type) {

	return null;
    }

    public Entity getTargetEntity(Player player) {
	return getTargetEntity(player, 0.95, false, true);
    }

    public Entity getTargetEntity(Player player, double pov) {
	return getTargetEntity(player, pov, false, true);
    }

    public Entity getTargetEntity(Player player, boolean includeSpectator, boolean includeInvisible) {
	return getTargetEntity(player, 0.95, includeSpectator, includeInvisible);
    }

    public Entity getTargetEntity(Player player, double pov, boolean includeSpectator, boolean includeInvisible) {

	return null;
    }

    public Block getTargetBlock(Player player, int distance, boolean ignoreNoneSolids) {
	return getTargetBlock(player, null, distance, ignoreNoneSolids);
    }

    public Block getTargetBlock(Player player, int distance) {
	return getTargetBlock(player, null, distance, false);
    }

    public Block getTargetBlock(Player player, Material lookingFor, int distance) {
	return getTargetBlock(player, lookingFor, distance, false);
    }

    public Block getTargetBlock(Player player, Material lookingFor, int distance, boolean ignoreNoneSolids) {
	ArrayList<Block> blocks = getLastTwoTargetBlock(player, lookingFor, distance, ignoreNoneSolids);
	return blocks.isEmpty() ? null : blocks.get(blocks.size() - 1);
    }

    public ArrayList<Block> getLastTwoTargetBlock(Player player, int distance) {
	return getLastTwoTargetBlock(player, null, distance, false);
    }

    public ArrayList<Block> getLastTwoTargetBlock(Player player, Material lookingFor, int distance, boolean fluids) {

	return null;
    }

    public itemInfo getItemInfo(String text) {

	return null;
    }

    public int getItemData(ItemStack item) {
	if (item.getType().toString().contains("_EGG"))
	    return CMILib.getInstance().getReflectionManager().getEggId(item);
	else if (item.getType().equals(CMIMaterial.SPAWNER.getMaterial()))
	    return new CMIItemStack(item).getEntityType().getTypeId();
	if (Version.isCurrentEqualOrHigher(Version.v1_13_R1))
	    return 0;
	return item.getData().getData();
    }

    public String translateDamageCause(String cause) {
	if (CMILib.getInstance().getLM().containsKey("info.DamageCause." + cause.toLowerCase()))
	    cause = CMILib.getInstance().getLM().getMessage("info.DamageCause." + cause.toLowerCase());
	return cause;
    }

    public int getOnlinePlayerCount() {
	int i = 0;
	for (Player one : Bukkit.getOnlinePlayers()) {
	    i++;
	}
	return i;
    }

    public String getOnlinePlayersAsString() {
	return getOnlinePlayersAsString(null);
    }

    public String getOnlinePlayersAsString(String ignore) {
	String players = "";
	for (Player one : Bukkit.getOnlinePlayers()) {
	    if (ignore != null && one.getName().equalsIgnoreCase(ignore))
		continue;
	    if (!players.isEmpty())
		players += ",";
	    players += one.getName();
	}
	return players;
    }

    @Deprecated
    public List<Player> getPlayersFromRange(Location loc, int range, @SuppressWarnings("unused") boolean forced) {
	return getPlayersFromRange(loc, range);
    }

    public List<Player> getPlayersFromRange(Location loc, int range) {
	return getPlayersFromRange(null, loc, range);
    }

    public List<Player> getPlayersFromRange(Player player, Location loc, int range) {

	return null;
    }

    public List<Player> getPlayersFromRangeForCounter(Location loc, int range, boolean forced) {

	return null;
    }

    public World getWorld(String name) {

	return null;
    }

    public List<String> getWorldList() {

	return null;
    }

    public void resendBlockInfo(final Player player, final Block block) {

    }

    public boolean isFullInv(ItemStack[] cn, List<ItemStack> list) {
	int total = 36;
	for (ItemStack one : cn) {
	    if (one != null)
		total--;
	}
	if (total < list.size())
	    return true;
	return false;
    }

    public List<ItemStack> ConvertInvToList(Inventory inv) {
	ItemStack[] contents = inv.getContents();
	List<ItemStack> items = new ArrayList<ItemStack>();
	for (ItemStack one : contents) {
	    if (one != null)
		items.add(one);
	}
	return items;
    }

    public boolean isOnline(String name) {
	return Bukkit.getPlayer(name) != null;
    }

    public boolean canRepair(ItemStack item) {

	return true;
    }

    public boolean needRepair(ItemStack item) {

	return true;
    }

    public ItemStack repairItem(ItemStack item) {

	return item;
    }

    public boolean hasSilkTouch(ItemStack is, int lvl) {

	return false;
    }

    @SuppressWarnings("deprecation")
    public ItemStack setEntityType(ItemStack is, EntityType type) {

	return null;
    }

    public boolean haveBlackListedItems(Player player, TpAction action) {

	return false;
    }

    private void showBlackListedItemList(Player player, HashMap<Material, Integer> items) {

    }

    public HashMap<Material, Integer> getBlackListedItems(Player player) {

	return null;
    }

    private HashMap<Material, Integer> getAllItemsFromInv(Inventory inv, HashMap<Material, Integer> t) {

	return null;
    }

    public String convertLocToStringShort(Location loc) {

	return null;
    }

    public boolean validName(String name) {
	return validName("[^a-zA-Z0-9\\-\\_]", name);
    }

    public boolean validName(String regex, String name) {

	return false;
    }

    public String getWorldName(UUID uuid) {
	World w = Bukkit.getWorld(uuid);
	if (w != null)
	    return w.getName();
	return this.worldCache.get(uuid);
    }

    public CMIChatColor getTpsColor(Double tps) {

	return null;
    }

    public static File getFile(Player player) {
	return getFile(player.getUniqueId());
    }

    public static File getFile(UUID uuid) {
	return new File(Bukkit.getWorlds().get(0).getWorldFolder(), "playerdata" + File.separator + uuid + ".dat");
    }

    public Player getRandomOnlinePlayer() {
	return getRandomOnlinePlayer(null);
    }

    public Player getRandomOnlinePlayer(Set<Player> exclude) {

	return null;
    }

    public boolean blockedItemFromRenaming(CommandSender sender, ItemStack item, String newName) {

	return false;
    }

    public String getLineSplitter(String text) {

	return null;
    }

}
