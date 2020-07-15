package com.deadshotmdf.EnderChestVault.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.deadshotmdf.EnderChestVault.Main;
import com.deadshotmdf.EnderChestVault.Utils.Colors;

public class ReloadConfigCommand implements CommandExecutor{
	
	Main main;
	
	public ReloadConfigCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(cmd.getName().equalsIgnoreCase("enderchestreload")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Only a player may execute this command.");
				return true;
			}
			Player player = (Player)sender;
			if(!player.hasPermission("enderchestvault.reloadconfig")) {
				String noPermission = Colors.color(main.getConfig().getString("noPermission"));
				player.sendMessage(noPermission);
				return true;
			}
			main.reloadConfig();
			main.saveDefaultConfig();
			String reloadConfig = Colors.color(main.getConfig().getString("reloadConfig"));
			player.sendMessage(reloadConfig);
			return true;
		}
		
		return true;
	}
}
