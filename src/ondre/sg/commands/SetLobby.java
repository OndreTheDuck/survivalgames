package ondre.sg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ondre.sg.managers.GameManager;
import ondre.sg.managers.LobbyManager;

public class SetLobby implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
		if(args.length != 0) {
			player.sendMessage(GameManager.prefix + "Wrong usage: /setlobby");
			return true;
			
			}
		
		LobbyManager.setLocation("lobby", player.getLocation());
		player.sendMessage(GameManager.prefix + "You have added a lobby spawn!");
		
		}
		
		return true;
	}
}