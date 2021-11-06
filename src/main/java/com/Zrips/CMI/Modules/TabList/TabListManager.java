package com.Zrips.CMI.Modules.TabList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Modules.ModuleHandling.CMIModule;

public class TabListManager {

    private HashMap<Integer, TabList> TabList = new HashMap<Integer, TabList>();

    private CMI plugin;

    public TabListManager(CMI plugin) {
	this.plugin = plugin;
    }

    private int sched = -1;
    private int sortSched = -1;
    private double interval = 1D;
//    private Boolean Enabled = true;
    private boolean async = true;
    private boolean UpdateTabListNames = true;
    private boolean GroupedEnabled = true;
    private boolean UpdatesOnJoin = true;
    private boolean UpdatesOnLeave = true;
    private boolean UpdatesOnWorldChange = true;
    private boolean UpdatesOnPlayerDeath = false;
    private boolean UpdatesOnAfkStateChange = false;
    private boolean UpdatesOnPlayerTeleport = false;
    private boolean UpdatesOnNickChange = false;

    private boolean addTabListHeader = true;
    private boolean addTabListFooter = true;

    private boolean sortingEnabled = false;
    private CMITabSortingType sortingType = CMITabSortingType.Name;
    private CMITabSortingOrder sortingOrder = CMITabSortingOrder.ASC;

    private List<String> SortingCustomGroup = new ArrayList<String>();
    private int SortingBalanceInterval = 10;
    private int SortingAutoUpdate = 1;

    public void stop() {
	if (sched != -1) {
	    Bukkit.getScheduler().cancelTask(sched);
	    sched = -1;
	}
	if (sortSched != -1) {
	    Bukkit.getScheduler().cancelTask(sortSched);
	    sortSched = -1;
	}
    }

    public void loadConfig() {
	
    }

    private void tasker() {
	
    }


    public TabList getTL(Player player) {
	
	return null;
    }

    public void updateTabList(int delay) {
    }

    public void updateTabList() {
	
    }

    public void updateTablistName(Player player) {
	
    }

    public void updateTabList(Player player) {
	
    }


    public void updateTabListSync(Player player) {
	

    }

    public void updateTabListAsync(final Player player) {
	
    }

    public boolean isUpdatesOnJoin() {
	return UpdatesOnJoin;
    }

    public boolean isUpdatesOnLeave() {
	return UpdatesOnLeave;
    }

    public boolean isUpdatesOnWorldChange() {
	return UpdatesOnWorldChange;
    }

    public boolean isUpdatesOnPlayerDeath() {
	return UpdatesOnPlayerDeath;
    }

    public boolean isUpdatesOnPlayerTeleport() {
	return UpdatesOnPlayerTeleport;
    }

    public boolean isUpdatesOnNickChange() {
	return UpdatesOnNickChange;
    }

    public boolean isUpdatesOnAfkStateChange() {
	return UpdatesOnAfkStateChange;
    }

    public boolean isEnabled() {
	return CMIModule.tablist.isEnabled();
    }

    public boolean isUpdateTabListNames() {
	return UpdateTabListNames;
    }

    public boolean isSortingEnabled() {
	return sortingEnabled;
    }

    public CMITabSortingType getSortingType() {
	return sortingType;
    }

    public CMITabSortingOrder getSortingOrder() {
	return sortingOrder;
    }

}
