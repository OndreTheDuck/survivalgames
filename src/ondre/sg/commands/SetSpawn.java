package ondre.sg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ondre.sg.managers.ArenaManager;
import ondre.sg.managers.GameManager;

public class SetSpawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			if(cmd.getName().equalsIgnoreCase("setspawn")) {
			Player player = (Player) sender;
			
		if(args.length != 2) {
			player.sendMessage(GameManager.prefix + "Too many arguments. /setspawn <arena> <number>");
			return true;

			}

			try {
				
				int number = Integer.parseInt(args[1]);
				ArenaManager.setSpawn(args[0], number, player.getLocation());
				player.sendMessage(GameManager.prefix + "You have added spawnpoint " + number + " for " + args[0].toUpperCase());

			} catch (NumberFormatException ex) {
				
				player.sendMessage(GameManager.prefix + "You need to identify a number!");

				}
			}
		}
		return true;
	}
}

