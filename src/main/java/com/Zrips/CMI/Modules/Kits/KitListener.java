package com.Zrips.CMI.Modules.Kits;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.Zrips.CMI.CMI;

public class KitListener implements Listener {
    private CMI plugin;

    public KitListener(CMI plugin) {
	this.plugin = plugin;
    }
}
