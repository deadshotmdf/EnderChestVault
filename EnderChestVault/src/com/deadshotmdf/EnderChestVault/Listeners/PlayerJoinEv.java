package com.deadshotmdf.EnderChestVault.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.deadshotmdf.EnderChestVault.Main;
import com.deadshotmdf.EnderChestVault.Utils.Maps;

public class PlayerJoinEv implements Listener{
	
	Main main;
	
	public PlayerJoinEv(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onIntEv(PlayerJoinEvent ev) {
		Player player = ev.getPlayer();
		
		if(!Maps.getCurrentOpenLevel().containsKey(player.getUniqueId())) Maps.getCurrentOpenLevel().put(player.getUniqueId(), 1);
	}
	
	@EventHandler
	public void onQuitEv(PlayerQuitEvent ev) {
		Player player = ev.getPlayer();
		
		if(Maps.getCurrentOpenLevel().containsKey(player.getUniqueId())) Maps.getCurrentOpenLevel().remove(player.getUniqueId());
	}

}
