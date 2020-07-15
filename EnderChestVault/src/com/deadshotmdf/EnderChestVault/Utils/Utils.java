package com.deadshotmdf.EnderChestVault.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.PermissionAttachmentInfo;

import com.deadshotmdf.EnderChestVault.Main;

public class Utils {
	
	Main main = (Main) Bukkit.getServer().getPluginManager().getPlugin("EnderChestVault");
	
	public Utils() {
		
	}
	
	public List<String> hasPerms(Player player) {
		List<String> l = new ArrayList<String>();
		for(PermissionAttachmentInfo per : player.getEffectivePermissions()) {
			if(!player.hasPermission(per.getPermission())) continue;
			if(!per.getPermission().startsWith("enderchestvaultsize.")) continue;
			l.add(per.getPermission());
		}
		return l;
	}
	
	public int getLevel(Player player) {
		Pair<String,Integer> p = new Pair<String,Integer>("Highest",-1);
		for(String perm : hasPerms(player)) {
			int lvl = getLevel(perm);
			if(lvl >= p.getValue()) {
				p = new Pair<String,Integer>(perm,lvl);
			}
		}
		
		return p.getValue();
	}
	
	public int getLevel(String perm) {
		String p = perm;
		String[] s = p.split("\\.");
		int i = Integer.parseInt(s[1]);
		
		return i;
	}
	
	public List<ItemStack> retrievePage(Player player,int id){
		List<ItemStack> l = new ArrayList<ItemStack>();
		if(main.getPlayersConfig().getConfigurationSection("Players") == null || !main.getPlayersConfig().getConfigurationSection("Players").contains(player.getUniqueId().toString()) || !main.getPlayersConfig().getConfigurationSection("Players." + player.getUniqueId()).contains(String.valueOf(id))) return l;
		for(String items : main.getPlayersConfig().getConfigurationSection("Players." + player.getUniqueId() + "." + id).getKeys(false)) {
//			Material mat = Material.getMaterial(main.getPlayersConfig().getString("Players." + player.getUniqueId() + "." + id + "." + items + ".material"));
//			String name = main.getPlayersConfig().getString("Players." + player.getUniqueId() + "." + id + "." + items + ".name");
//			List<String> lore = main.getPlayersConfig().getStringList("Players." + player.getUniqueId() + "." + id + "." + items + ".lore");
//			
//			l.add(ItemUtil.createItem(mat, name, lore));
			if(main.getPlayersConfig().getItemStack("Players." + player.getUniqueId() + "." + id + "." + items) == null) continue;
			ItemStack item = main.getPlayersConfig().getItemStack("Players." + player.getUniqueId() + "." + id + "." + items);
			l.add(item);
		}
		
		return l;
	}
	
	public void saveInv(Inventory inv,Player player,int id) {
		main.getPlayersConfig().set("Players." + player.getUniqueId() + "." + id,null);
		saveF();
		for(int i = 0; i < inv.getSize(); i++) {
			if(i >= 45) continue;
			if(inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR) continue;
			ItemStack item = inv.getItem(i);
//			String name = null;
//			List<String> lore = new ArrayList<String>();
//			
//			if(!inv.getItem(i).hasItemMeta() || !inv.getItem(i).getItemMeta().hasDisplayName()) {
//				net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
//				name = nmsStack.getItem().a(nmsStack);
//			}
//			else {
//				name = Colors.color(item.getItemMeta().getDisplayName());
//			}
//
//			
//			if(inv.getItem(i).getItemMeta().hasLore()) {
//				lore = Colors.color(item.getItemMeta().getLore());
//			}
//			
//			main.getPlayersConfig().set("Players." + player.getUniqueId() + "." + id + "." + item + i + ".material", item.getType().toString());
//			main.getPlayersConfig().set("Players." + player.getUniqueId() + "." + id + "." + item + i + ".name", name);
//			main.getPlayersConfig().set("Players." + player.getUniqueId() + "." + id + "." + item + i + ".lore", lore);
			main.getPlayersConfig().set("Players." + player.getUniqueId() + "." + id + "." + item + i, item);
		}
		saveF();
	}
	
	void saveF() {
		try {
			main.getPlayersConfig().save(main.getPlayersFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}