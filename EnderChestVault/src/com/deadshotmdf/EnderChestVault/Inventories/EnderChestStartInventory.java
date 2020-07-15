package com.deadshotmdf.EnderChestVault.Inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.deadshotmdf.EnderChestVault.Main;
import com.deadshotmdf.EnderChestVault.Utils.Colors;
import com.deadshotmdf.EnderChestVault.Utils.ItemUtil;


public class EnderChestStartInventory {
	
	Main main = (Main) Bukkit.getServer().getPluginManager().getPlugin("EnderChestVault");
	
	public EnderChestStartInventory() {
		
	}
	
	public Inventory startInv() {
		String name = Colors.color(main.getConfig().getString("enderChestStartInventoryName"));
		Inventory inv = Bukkit.getServer().createInventory(null, 54, name);
		
		String enderChestName = Colors.color(main.getConfig().getString("enderChestName"));
		
		ItemStack glass = ItemUtil.clearName(Material.STAINED_GLASS_PANE,(short) 10);
		ItemStack enderChest = ItemUtil.setName(Material.ENDER_CHEST,enderChestName);
		
		for(int i = 0; i < 54; i++) {
			if(i == 22) {inv.setItem(i, enderChest); continue;}
			inv.setItem(i, glass);
		}
		
		return inv;
	}

}
