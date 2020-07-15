package com.deadshotmdf.EnderChestVault.Listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.deadshotmdf.EnderChestVault.Main;
import com.deadshotmdf.EnderChestVault.Inventories.EnderChestStartInventory;

public class PlayerInteractEv implements Listener{
	
	Main main;
	
	public PlayerInteractEv(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onIntEv(PlayerInteractEvent ev) {
		boolean isActive = main.getConfig().getBoolean("vaultEnderChest");
		
		if(!isActive) return;
		
		if(ev.getClickedBlock() == null || ev.getClickedBlock().getType() == Material.AIR) return;
		
		if(ev.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		
		Block block = ev.getClickedBlock();
		
		if(block.getType() != Material.ENDER_CHEST) return;
		
		Player player = ev.getPlayer();
		
		ev.setCancelled(true);	
		
		EnderChestStartInventory ecvi = new EnderChestStartInventory();
		
		player.openInventory(ecvi.startInv());
		
		return;
	}

}