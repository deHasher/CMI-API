package com.Zrips.CMI.Modules.SpawnerCharge;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.Zrips.CMI.CMI;
import net.Zrips.CMILib.Items.CMIMaterial;
import net.Zrips.CMILib.Locale.LC;
import net.Zrips.CMILib.Effects.CMIEffectManager.CMIParticle;
import com.Zrips.CMI.Modules.Permissions.PermissionsManager.CMIPerm;

public class SpawnerProximityListener implements Listener {
    private CMI plugin;

    public SpawnerProximityListener(CMI plugin) {
	this.plugin = plugin;
    }

}
