package com.Zrips.CMI.Modules.Warnings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.entity.Player;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Containers.Snd;
import net.Zrips.CMILib.FileHandler.ConfigReader;
import net.Zrips.CMILib.Logs.CMIDebug;

public class WarningManager {

    private CMI plugin;

    private LinkedHashMap<Integer, List<String>> commands = new LinkedHashMap<Integer, List<String>>();

    private HashMap<String, CMIWarningCategory> Categories = new HashMap<String, CMIWarningCategory>();

    public WarningManager(CMI plugin) {
	this.plugin = plugin;
    }


    public void loadConfig() {

    }

    public CMIWarningCategory getDefaultCategory() {
	return Categories.get("default");
    }

    public CMIWarningCategory getCategory(String name) {
	if (name == null)
	    return null;
	return Categories.get(name.toLowerCase());
    }

    public List<String> getCommandsByWarns(Player player, int from, int to) {
	
	return null;
    }

    public List<String> getCommandsByWarns(int warns) {
	return commands.get(warns) != null ? new ArrayList<String>(commands.get(warns)) : null;
    }

    public HashMap<String, CMIWarningCategory> getCategories() {
	return Categories;
    }
}
