package com.Zrips.CMI.Modules.Economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.Zrips.CMI.CMI;

import net.milkbowl.vault.economy.Economy;

public class VaultManager {

    private CMI plugin;
    private String version = "";
    private boolean supportsCmi = false;

    public VaultManager(CMI plugin) {
	this.plugin = plugin;
    }

//    private Economy vaultHandler = null;

    private Economy economy = null;

    public enum EconomySetupResponse {
	Vault, NoVault, NoVaultEconomy, CMI;
    }

    public EconomySetupResponse setupVault() {

	return economy != null ? EconomySetupResponse.Vault : EconomySetupResponse.NoVaultEconomy;
    }

    public Economy getVaultEconomy() {
	return economy;
    }

    public boolean isVaultEnabled() {
	return economy != null;
    }

    public String format(Double money) {
	if (money == null)
	    return "0";
	if (this.economy == null)
	    return String.valueOf(money);
	return this.economy.format(money);
    }

    public String getVersion() {
	return version;
    }

    public boolean isSupportsCmi() {
	return supportsCmi;
    }
}
