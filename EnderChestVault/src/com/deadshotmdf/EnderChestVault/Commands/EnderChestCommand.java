package com.deadshotmdf.EnderChestVault.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.deadshotmdf.EnderChestVault.Main;
import com.deadshotmdf.EnderChestVault.Inventories.EnderChestStartInventory;
import com.deadshotmdf.EnderChestVault.Utils.Colors;

public class EnderChestCommand implements CommandExecutor{
	
	Main main;
	
	public EnderChestCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(cmd.getName().equalsIgnoreCase("enderchest")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Only a player may execute this command.");
				return true;
			}
			Player player = (Player)sender;
			
			boolean isActive = main.getConfig().getBoolean("vaultEnderChest");
			
			if(!player.hasPermission("enderchestvault.use")) {
				String noPermission = Colors.color(main.getConfig().getString("noPermission"));
				player.sendMessage(noPermission);
				return true;
			}
			
			if(!isActive) {
				String enderchestVaultDisabled = Colors.color(main.getConfig().getString("enderchestVaultDisabled"));
				player.sendMessage(enderchestVaultDisabled);
				return true;
			}
			
			EnderChestStartInventory ecsi = new EnderChestStartInventory();
			
			player.openInventory(ecsi.startInv());
		}
		
		return true;
	}

}
