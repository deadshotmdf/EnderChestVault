package com.deadshotmdf.EnderChestVault.Inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.deadshotmdf.EnderChestVault.Main;
import com.deadshotmdf.EnderChestVault.Utils.Colors;
import com.deadshotmdf.EnderChestVault.Utils.ItemUtil;
import com.deadshotmdf.EnderChestVault.Utils.Maps;
import com.deadshotmdf.EnderChestVault.Utils.Utils;;

public class EnderChestVaultInventory {
	
	Main main = (Main) Bukkit.getServer().getPluginManager().getPlugin("EnderChestVault");
	
	public EnderChestVaultInventory() {
		
	}
	
	public Inventory EnderChestVaultInv(Player player) {
		int currentLevel = Maps.getCurrentOpenLevel().get(player.getUniqueId());
		String name = Colors.color(main.getConfig().getString("enderChestVaultInventoryName").replace("{currentPage}", String.valueOf(currentLevel)));
		
		Inventory inv = Bukkit.getServer().createInventory(null, 54, name);
		
		String backArrowName = Colors.color(main.getConfig().getString("backArrowName"));
		String nextArrowName = Colors.color(main.getConfig().getString("nextArrowName"));
		
		ItemStack glass = ItemUtil.clearName(Material.STAINED_GLASS_PANE,(short) 15);
		ItemStack backArrow = ItemUtil.setName(Material.ARROW, backArrowName);
		ItemStack nextArrow = ItemUtil.setName(Material.ARROW, nextArrowName);
		
		Utils ut = new Utils();
		int maxLevel = ut.getLevel(player);
		
		for(ItemStack items : ut.retrievePage(player, currentLevel)) {
			if(items == null) continue;
			inv.addItem(items);
		}
		
		for(int i = 45; i < 54; i++) {
			if(i == 48) {
				if(currentLevel <= 1) {inv.setItem(i, glass); continue;}
				inv.setItem(i, backArrow); 
				continue;
			}
			if(i == 50) {
				if(currentLevel >= maxLevel) {inv.setItem(i, glass); continue;}
				inv.setItem(i, nextArrow); 
				continue;
			}
			
			inv.setItem(i, glass);
		}
		
		return inv;
	}

}
