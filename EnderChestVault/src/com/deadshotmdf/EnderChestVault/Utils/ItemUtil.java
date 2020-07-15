package com.deadshotmdf.EnderChestVault.Utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtil {
	
	public static ItemStack setName(Material mat,String s) {
		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(s);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack clearName(Material mat,short sh) {
		ItemStack item = new ItemStack(mat,1,sh);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(" ");
		item.setItemMeta(meta);
		
		return item;
	}

}
