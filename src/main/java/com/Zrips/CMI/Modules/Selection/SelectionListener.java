package com.Zrips.CMI.Modules.Selection;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import com.Zrips.CMI.CMI;
import net.Zrips.CMILib.Locale.LC;
import net.Zrips.CMILib.Items.CMIMaterial;
import com.Zrips.CMI.Modules.Permissions.PermissionsManager.CMIPerm;
import com.Zrips.CMI.events.CMISelectionEvent;

public class SelectionListener implements Listener {
    private CMI plugin;

    public SelectionListener(CMI plugin) {
	this.plugin = plugin;
    }

}
