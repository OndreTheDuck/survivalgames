package ondre.sg.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ondre.sg.countdowns.LobbyCountdown;
import ondre.sg.managers.GameManager;
import ondre.sg.managers.LobbyManager;
import ondre.sg.utils.GameState;
import ondre.sg.utils.GameUtils;

public class JoinListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		event.setJoinMessage(null);
		
	if(GameManager.state == GameState.LOBBY && Bukkit.getOnlinePlayers().size() == GameManager.minPlayers) {
		LobbyCountdown.startLobbyTimer();	
		
	}
		
	if(GameManager.state == GameState.LOBBY) {
		GameManager.alive.add(player);
		GameUtils.setAlive();
		
		player.sendMessage(ChatColor.AQUA + player.getDisplayName() + ChatColor.YELLOW + " has joined the fight!");
		
	}
		
	if(GameManager.state == GameState.PREGAME) {
		event.getPlayer().kickPlayer(GameManager.prefix + "You can not join during pregame!");
		
	}
	
	if(GameManager.state == GameState.INGAME) {
		event.getPlayer().teleport(LobbyManager.getLocation("lobby"));
			
				GameManager.alive.remove(player);
				player.setGameMode(GameMode.SPECTATOR);
				
		for(Player alive : GameManager.alive) {
			alive.hidePlayer(player);
			
				}		
			}
		player.teleport(LobbyManager.getLocation("lobby"));
		}
	}
