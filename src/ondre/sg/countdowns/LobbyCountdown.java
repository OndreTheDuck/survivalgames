package ondre.sg.countdowns;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ondre.sg.Main;
import ondre.sg.managers.ActionManager;
import ondre.sg.managers.ArenaManager;
import ondre.sg.managers.GameManager;
import ondre.sg.utils.GameState;

public class LobbyCountdown {
	
	public static int lobby = GameManager.lobbyTime;
	
	public static void startLobbyTimer() {
		Bukkit.broadcastMessage(GameManager.prefix + "Enough players. The game will start.");
		new Runnable() {
		public int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, this, 0L, 20L);	
			@Override
			public void run() {
				if (lobby > 0) {
					lobby--;
					
					if(lobby <= 16 && lobby > 0) {
						for(Player alive : GameManager.alive) {
							alive.setLevel(lobby);
							
						}
					}
					
					if(lobby == 0) {
						ArenaManager.mapTeleport();
						GameManager.state = GameState.PREGAME;
						PregameCountdown.startpregameTimer();
						
						for(Player alive : GameManager.alive) {
						ActionManager actionBar = new ActionManager(ChatColor.YELLOW + "Teleporting . . .");
						actionBar.sendToPlayer(alive);
						
						}
					}
					
				} else {
					
					Bukkit.getScheduler().cancelTask(taskID);
				}
			}
		};
	}
}
