package com.Zrips.CMI.Containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Zrips.CMI.CMI;

import net.Zrips.CMILib.Colors.CMIChatColor;

public class CommandAlias {

    private boolean state;
    private String cmiCommandName;
    private List<String> commands = new ArrayList<String>();
    private String alias = null;
    private boolean requiresPerm = false;
    private boolean tabComplete = true;
    private CommandAliasType type;
    
    private boolean overrideTab = false;
    private boolean addTabs = false;
    
    private boolean disableBase = false;

    private List<String> customTabList = null;
    private CMITabComplete customTab = null;

    private Boolean containsDynamic = null;

//    public CommandAlias(String command, boolean state) {
//	this.commands.add(command);
//	this.state = state;
//    }

    public CommandAlias(String alias, List<String> commands, boolean state) {
	this(alias, commands, state, null);
    }

    public CommandAlias(String alias, List<String> commands, boolean state, CommandAliasType type) {
	this(alias, commands, state, type, null);
    }

    public CommandAlias(String alias, List<String> commands, boolean state, CommandAliasType type, String cmiCommandName) {
	this.commands.addAll(commands);
	this.state = state;
	this.alias = CMIChatColor.stripColor(CMIChatColor.deColorize(alias));
	this.type = type;
	this.cmiCommandName = cmiCommandName;
    }

    public boolean getState() {
	return state;
    }

    public void setState(boolean state) {
	this.state = state;
    }

    public String getCommand() {
	if (commands.isEmpty())
	    return "";
	return commands.get(0);
    }

    public String getCleanCommand() {
	    return "";
    }

    public String getCommand(CommandSender sender, List<String> args) {

	    return "";
	
    }

    public List<String> getCommands() {
	return commands;
    }

    public String getCommandsForLore() {

	String lore = "";
	return lore;
    }

    public String getAlias() {
	return alias;
    }

    Pattern patern = Pattern.compile("[$?][\\d][-]?");

    public boolean containsDynamicVariables() {
	if (containsDynamic == null) {
	    recalculateDynamicVariables();
	}
	return containsDynamic == null ? false : containsDynamic;
    }

    public void recalculateDynamicVariables() {
	containsDynamic = null;
	for (String one : commands) {
	    Matcher match = patern.matcher(one);
	    if (match.find()) {
		containsDynamic = true;
		break;
	    }
	}
	if (containsDynamic == null)
	    containsDynamic = false;
    }

    public String getAliasBaseCommand() {
	return alias.contains(" ") ? alias.split(" ")[0] : alias;
    }

    public String getAliasAsOneWord() {
	return alias.replace(" ", "__");
    }

    public String getAliasAsOneWordNS() {
	return alias.replace(" ", "");
    }

    public void setAlias(String alias) {
	this.alias = alias;
    }

    public void setCommands(List<String> commands) {
	this.commands = commands;
    }

    public boolean isRequiresPerm() {
	return requiresPerm;
    }

    public void setRequiresPerm(boolean requiresPerm) {
	this.requiresPerm = requiresPerm;
    }

    public CommandAliasType getType() {
	return type == null ? CommandAliasType.custom : type;
    }

    public String getCmiCommandName() {
	return cmiCommandName;
    } 

    public boolean isTabComplete() {
	return tabComplete;
    }

    public void setTabComplete(boolean tabComplete) {
	this.tabComplete = tabComplete;
    }

    public List<String> getCustomTabRawList() {
	return customTabList;
    }

    public CMITabComplete getCustomTab() {
	return customTab;
    }

    public List<Object> getTabCompleteList(String[] args) {
	if (customTab == null)
	    return new ArrayList<Object>();
	return customTab.getTabCompleteList(args);
    }

    public void recheckTabCompletes() {
	this.customTab = new CMITabComplete();
	for (String tab : this.customTabList) {
	    this.customTab.addTabComplete(tab);
	}
    }

    public void setCustomTab(List<String> customTab) {
	this.customTab = new CMITabComplete();
	this.customTabList = customTab;
	recheckTabCompletes();
    }

    public boolean isDisableBase() {
	return disableBase;
    }

    public void setDisableBase(boolean disableBase) {
	this.disableBase = disableBase;
    }

    public boolean isOverrideTab() {
	return overrideTab;
    }

    public void setOverrideTab(boolean overrideTab) {
	this.overrideTab = overrideTab;
    }

    public boolean isAddTabs() {
	return addTabs;
    }

    public void setAddTabs(boolean addTabs) {
	this.addTabs = addTabs;
    }

}
