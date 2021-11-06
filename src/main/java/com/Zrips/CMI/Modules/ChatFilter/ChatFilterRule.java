package com.Zrips.CMI.Modules.ChatFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.Zrips.CMILib.Chat.ChatFilterBlockType;

public class ChatFilterRule {

    private String replaceWith;
    private ChatFilterBlockType blockType;
    private String ruleName;
    private List<Pattern> pattern = new ArrayList<Pattern>();
    private String messageToStaff;
    private List<String> commands = new ArrayList<String>();
    private String group;
    private boolean informConsole = false;
    private boolean applyToPrivateMessages = true;
    

    public ChatFilterRule(String ruleName, String group, List<String> list, String replaceWith, ChatFilterBlockType blockType, String messageToStaff, List<String> commands) {
	this.group = group;
	this.ruleName = ruleName;
	this.commands = commands;
	setPattern(list);
	this.messageToStaff = messageToStaff;
	this.replaceWith = replaceWith;
	this.blockType = blockType;
    }

    public ChatFilterRule(String ruleName, List<String> list) {
	this.ruleName = ruleName;
	setPattern(list);
	this.blockType = ChatFilterBlockType.None;
    }

    public ChatFilterRule() {
    }

    public List<Pattern> getPattern() {
	return pattern;
    }

    public ChatFilterRule setPattern(String list) {
	setPattern(Arrays.asList(list));
	return this;
    }

    public void setPattern(List<String> list) {
	pattern.clear();
	for (String one : list) {
	    pattern.add(Pattern.compile(one));
	}
    }

    public String getReplaceWith() {
	return replaceWith;
    }

    public void setReplaceWith(String replaceWith) {
	this.replaceWith = replaceWith;
    }

    public ChatFilterBlockType getBlockType() {
	return blockType;
    }

    public void setBlockType(ChatFilterBlockType blockType) {
	this.blockType = blockType;
    }

    public String getRuleName() {
	return ruleName;
    }

    public void setRuleName(String ruleName) {
	this.ruleName = ruleName;
    }

    public Matcher getMatcher(String msg) {
	Matcher matcher = null;
	for (Pattern one : pattern) {
	    if (one.matcher(msg).find()) { 
		matcher = one.matcher(msg);
		break;
	    }
	}
	return matcher;
    }

    public String getMessageToStaff() {
	return messageToStaff;
    }

    public void setMessageToStaff(String messageToStaff) {
	this.messageToStaff = messageToStaff;
    }

    public List<String> getCommands() {
	return commands;
    }

    public void setCommands(List<String> commands) {
	this.commands = commands;
    }

    public String getGroup() {
	return group;
    }

    public void setGroup(String group) {
	this.group = group;
    }

    public boolean isInformConsole() {
	return informConsole;
    }

    public void setInformConsole(boolean informConsole) {
	this.informConsole = informConsole;
    }

    public boolean isApplyToPrivateMessages() {
	return applyToPrivateMessages;
    }

    public void setApplyToPrivateMessages(boolean applyToPrivateMessages) {
	this.applyToPrivateMessages = applyToPrivateMessages;
    }

}
