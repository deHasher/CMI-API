package com.Zrips.CMI.Modules.Ranks;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.Zrips.CMI.CMI;
import net.Zrips.CMILib.Colors.CMIChatColor;
import net.Zrips.CMILib.Entities.CMIEntityType;

import com.Zrips.CMI.Containers.CMIUser;
import com.Zrips.CMI.Containers.Snd;
import net.Zrips.CMILib.Items.CMIItemStack;
import net.Zrips.CMILib.Items.CMIMaterial;
import net.Zrips.CMILib.Logs.CMIDebug;
import com.Zrips.CMI.Modules.Particl.ParticleManager.CMIPresetAnimations;
import com.Zrips.CMI.Modules.Ranks.RankManager.rankupFailType;
import com.Zrips.CMI.Modules.Statistics.StatsManager.CMIStatistic;
import com.Zrips.CMI.Modules.Statistics.StatsManager.CMIType;

public class CMIRank {

    private String name;
    private String displayName;
    private boolean enabled;
    private boolean defaultRank;
    private boolean AutoRankup;
    private boolean rankUpConfirmation;
    private List<CMIRank> nextRanks;
    private List<String> nextRanksT;
    private List<String> Commands;
    private List<String> CommandsOnRankDown;
    private LinkedHashMap<CMIStatistic, LinkedHashMap<Object, Long>> statRequirements;
    private LinkedHashMap<String, String> permRequirements;
    private LinkedHashMap<String, Integer> mcmmoRequirements;
    private LinkedHashMap<String, Integer> JobsRequirements;
    private LinkedHashMap<CMIItemStack, Integer> ItemRequirements;
    private double moneyCost;
    private double expCost;
    private int votes;

    private int weight = 0;

    public void reset() {
    }

    public CMIRank(String name) {
	this.name = name;
	reset();
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public boolean isEnabled() {
	return enabled;
    }

    public void setEnabled(boolean enabled) {
	this.enabled = enabled;
    }

    public boolean isDefaultRank() {
	return defaultRank;
    }

    public void setDefaultRank(boolean defaultRank) {
	this.defaultRank = defaultRank;
    }

    public boolean isRankUpConfirmation() {
	return rankUpConfirmation;
    }

    public void setRankUpConfirmation(boolean rankUpConfirmation) {
	this.rankUpConfirmation = rankUpConfirmation;
    }

    public List<CMIRank> getNextRanks() {
	return nextRanks;
    }

    public List<CMIRank> getNextValidRankUps(CMIUser user) {
	return null;
    }

    public boolean isOnSamePathWith(CMIRank rank2) {
	return false;
    }

    private boolean checkPrevious(CMIRank rank1, CMIRank rank2) {
	return false;
    }

    private boolean checkNext(CMIRank rank1, CMIRank rank2) {
	return false;
    }

    public List<CMIRank> getPreviousRanks() {
	return null;
    }

    public List<CMIRank> getValidPreviousRanks(CMIUser user) {
	return null;
    }

    public String getValidPreviousRanksAsString() {

	return null;
    }

    public rankupFailType canRankup(CMIUser user) {
	return null;
    }

    public double getMoneyCost() {
	return moneyCost;
    }

    public void setMoneyCost(double moneyCost) {
	this.moneyCost = moneyCost;
    }

    public double getExpCost() {
	return expCost;
    }

    public void setExpCost(double expCost) {
	this.expCost = expCost;
    }

    public List<String> getCommands() {
	return Commands;
    }

    public void setCommands(List<String> commands) {
	Commands = commands;
    }

    public LinkedHashMap<CMIStatistic, LinkedHashMap<Object, Long>> getStatRequirements() {
	return statRequirements;
    }

    public LinkedHashMap<Object, Long> getStatRequirement(CMIStatistic stat) {
	return statRequirements.get(stat);
    }

    public CMIRank addStatRequirement(CMIStatistic stat, String name, Long amount) {

	return null;
    }

    public void setStatRequirements(LinkedHashMap<CMIStatistic, LinkedHashMap<Object, Long>> statRequirements) {
	this.statRequirements = statRequirements;
    }

    public boolean isAutoRankup() {
	return AutoRankup;
    }

    public void setAutoRankup(boolean autoRankup) {
	AutoRankup = autoRankup;
    }

    public void setNextRanksT(List<String> nextRanksT) {
	this.nextRanksT = nextRanksT;
    }

    public void calculateNextRanks() {
	nextRanks.clear();
	for (String one : nextRanksT) {
	    CMIRank rank = CMI.getInstance().getRankManager().getRank(one);
	    if (rank != null)
		nextRanks.add(rank);
	}
    }

    public void finalizeRankup(CMIUser user) {
	finalizeRankup(user, true, true);
    }

    public void finalizeRankup(CMIUser user, Boolean commands, Boolean cost) {

    }

    @Deprecated
    public void takeExp(CMIUser user) {

    }

    @Deprecated
    public void takeMoney(CMIUser user) {
	if (this.getMoneyCost() <= 0)
	    return;
	user.withdraw(this.getMoneyCost());
    }

    public void takeItems(CMIUser user) {
	CMI.getInstance().getRankManager().removeContents(user.getPlayer(), this.getItemRequirements());
    }

    public void performCommands(CMIUser user) {
	Snd snd = new Snd().setSender(user).setTarget(user);

	List<String> cp = new ArrayList<String>(this.Commands);
	cp = CMI.getInstance().getLM().updateSnd(snd, cp);
	CMI.getInstance().getSpecializedCommandManager().processCmds(cp, user.isOnline() ? user.getPlayer() : null);
    }

    public void performCommandsOnRankDown(CMIUser user) {
    }

    public String getDisplayName() {
	if (displayName == null)
	    return this.getName();
	return CMIChatColor.translate(displayName);
    }

    public void setDisplayName(String displayName) {
	this.displayName = displayName;
    }

    public LinkedHashMap<String, String> getPermRequirements() {
	return permRequirements;
    }

    public void setPermRequirements(LinkedHashMap<String, String> permRequirements) {
	this.permRequirements = permRequirements;
    }

    public LinkedHashMap<String, Integer> getMcmmoRequirements() {
	return mcmmoRequirements;
    }

    public void setMcmmoRequirements(LinkedHashMap<String, Integer> mcmmoRequirements) {
	this.mcmmoRequirements = mcmmoRequirements;
    }

    public LinkedHashMap<String, Integer> getJobsRequirements() {
	return JobsRequirements;
    }

    public void setJobsRequirements(LinkedHashMap<String, Integer> jobsRequirements) {
	JobsRequirements = jobsRequirements;
    }

    public LinkedHashMap<CMIItemStack, Integer> getItemRequirements() {
	return ItemRequirements;
    }

    public void setItemRequirements(LinkedHashMap<CMIItemStack, Integer> itemRequirements) {
	ItemRequirements = itemRequirements;
    }

    public int getVotes() {
	return votes;
    }

    public void setVotes(int votes) {
	this.votes = votes;
    }

    public List<String> getCommandsOnRankDown() {
	return CommandsOnRankDown;
    }

    public void setCommandsOnRankDown(List<String> commandsOnRankDown) {
	CommandsOnRankDown = commandsOnRankDown;
    }

    public int getWeight() {
	return weight;
    }

    public void setWeight(int weight) {
	this.weight = weight;
    }
}
