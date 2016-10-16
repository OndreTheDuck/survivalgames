package ondre.sg.countdowns;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ondre.sg.Main;
import ondre.sg.managers.ActionManager;
import ondre.sg.managers.GameManager;
import ondre.sg.utils.GameState;

public class GameCountdown {

public static int game = GameManager.gameTime;
	
	public static void startgameTimer() {
		new Runnable() {
		public int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, this, 0L, 20L);	
			@Override
			public void run() {
				if (game > 0) {
					game--;
					
					if(game == 600 || game == 300) {
						for(Player alive : GameManager.alive) {
							ActionManager actionBar = new ActionManager(ChatColor.YELLOW + "The game ends in " + ChatColor.GREEN + game/60 + ChatColor.YELLOW + " minutes!");
							actionBar.sendToPlayer(alive);
						
						} 
					}
							
					if(game == 60) {
						for(Player alive : GameManager.alive) {
							ActionManager actionBar = new ActionManager(ChatColor.YELLOW + "The game ends in  " + ChatColor.GREEN + game + ChatColor.YELLOW + " seconds!");
							actionBar.sendToPlayer(alive);
							
							}
						}
					
					if(game <= 30) {
						for(Player alive : GameManager.alive) {
							ActionManager actionBar = new ActionManager(ChatColor.YELLOW + "The game ends in " + ChatColor.GREEN + game + ChatColor.YELLOW + " seconds!");
							actionBar.sendToPlayer(alive);
							
							}
						}
					
					if(game <= 600 && game > 0) {
						
					}
					
					if(game == 0) {						
						GameManager.state = GameState.RESTART;
						RestartCountdown.startrestartTimer();
						
						}
					
					}else {
						
						Bukkit.getScheduler().cancelTask(taskID);
					}			
				}
			};
		}
	}
