package com.deadshotmdf.EnderChestVault.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.deadshotmdf.EnderChestVault.Main;
import com.deadshotmdf.EnderChestVault.Inventories.EnderChestVaultInventory;
import com.deadshotmdf.EnderChestVault.Utils.Colors;
import com.deadshotmdf.EnderChestVault.Utils.Maps;
import com.deadshotmdf.EnderChestVault.Utils.Utils;

public class InventoryClickEv implements Listener{
	
	Main main;
	
	public InventoryClickEv(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onClickEv(InventoryClickEvent ev) {
		Player player = (Player) ev.getWhoClicked();
		
		if(ev.getClickedInventory() == null) return;
		
		if(ev.getCurrentItem() == null || ev.getCurrentItem().getType() == Material.AIR) return;
		
		ItemStack item = ev.getCurrentItem();
		
		String startInvName = Colors.color(main.getConfig().getString("enderChestStartInventoryName"));
		
		EnderChestVaultInventory ecvi = new EnderChestVaultInventory();
		Utils ut = new Utils();
		
		if(ev.getInventory().getTitle().equals(startInvName)) {
			if(ev.getRawSlot() != 22) {ev.setCancelled(true); return;}
			
			ev.setCancelled(true);
			
			if(ut.hasPerms(player).size() <= 0) {
				String noPages = Colors.color(main.getConfig().getString("noPages"));
				player.sendMessage(noPages);
				player.closeInventory();
				return;
			}
			
			player.closeInventory();
			player.openInventory(ecvi.EnderChestVaultInv(player));
		}
		
		int currentLevel = Maps.getCurrentOpenLevel().get(player.getUniqueId());
		String vaultInvName = Colors.color(main.getConfig().getString("enderChestVaultInventoryName").replace("{currentPage}", String.valueOf(currentLevel)));
		
		if(ev.getInventory().getTitle().equals(vaultInvName)) {
			if(ev.getRawSlot() >= 45 && ev.getRawSlot() <= 47 || ev.getRawSlot() == 49 || ev.getRawSlot() >= 51 && ev.getRawSlot() <= 53) {ev.setCancelled(true); return;}
			
			if(ev.getRawSlot() == 48) {
				ev.setCancelled(true);
				if(item.getType() != Material.ARROW) return;
				player.closeInventory();
				Maps.getCurrentOpenLevel().put(player.getUniqueId(), currentLevel - 1);
				player.openInventory(ecvi.EnderChestVaultInv(player));
			}
			
			if(ev.getRawSlot() == 50) {
				ev.setCancelled(true);
				if(item.getType() != Material.ARROW) return;
				player.closeInventory();
				Maps.getCurrentOpenLevel().put(player.getUniqueId(), currentLevel + 1);
				player.openInventory(ecvi.EnderChestVaultInv(player));
			}
		}	
		
	}
	
	@EventHandler
	public void onCloseEv(InventoryCloseEvent ev) {
		Player player = (Player) ev.getPlayer();
		
		if(ev.getInventory() == null) return;
		
		Inventory inv = ev.getInventory();
		
		int currentLevel = Maps.getCurrentOpenLevel().get(player.getUniqueId());
		String vaultInvName = Colors.color(main.getConfig().getString("enderChestVaultInventoryName").replace("{currentPage}", String.valueOf(currentLevel)));
		Utils ut = new Utils();
		
		if(!ev.getInventory().getTitle().equals(vaultInvName)) return;
		ut.saveInv(inv, player, currentLevel);
		ev.getInventory().clear();
	}

}