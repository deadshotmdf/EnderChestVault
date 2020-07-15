package com.deadshotmdf.EnderChestVault;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.deadshotmdf.EnderChestVault.Commands.EnderChestCommand;
import com.deadshotmdf.EnderChestVault.Commands.ReloadConfigCommand;
import com.deadshotmdf.EnderChestVault.Listeners.InventoryClickEv;
import com.deadshotmdf.EnderChestVault.Listeners.PlayerInteractEv;
import com.deadshotmdf.EnderChestVault.Listeners.PlayerJoinEv;
import com.deadshotmdf.EnderChestVault.Utils.Maps;


public class Main extends JavaPlugin{
	
	File playersFile = new File(getDataFolder(), "players.yml");
	FileConfiguration playersConfig = YamlConfiguration.loadConfiguration(playersFile);
	
	public File getPlayersFile() {
		return playersFile;
	}
	
	public FileConfiguration getPlayersConfig() {
		return playersConfig;
	}
	
	public void onEnable() {
		if(!playersFile.exists()) {
			saveResource("players.yml",false);
		}
		saveDefaultConfig();
		onH();
		registerEvents();
		this.getCommand("enderchest").setExecutor((CommandExecutor)new EnderChestCommand(this));
		this.getCommand("enderchestreload").setExecutor((CommandExecutor)new ReloadConfigCommand(this));
	}
	
	void registerEvents() {
	    PluginManager pm = Bukkit.getPluginManager();
	    pm.registerEvents((Listener)new InventoryClickEv(this), this);
	    pm.registerEvents((Listener)new PlayerInteractEv(this), this);
	    pm.registerEvents((Listener)new PlayerJoinEv(this), this);
	}

	public void onH() {
		for(Player player : Bukkit.getServer().getOnlinePlayers()) {
			if(!Maps.getCurrentOpenLevel().containsKey(player.getUniqueId())) Maps.getCurrentOpenLevel().put(player.getUniqueId(), 1);
		}
	}
}
