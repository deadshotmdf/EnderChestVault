package com.deadshotmdf.EnderChestVault.Utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public class Colors {
	
	public static String color(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static List<String>color(List<String> s){
		List<String> l = new ArrayList<String>();
		for(String ss : s) {
			l.add(color(ss));
		}
		return l;
	}

}
