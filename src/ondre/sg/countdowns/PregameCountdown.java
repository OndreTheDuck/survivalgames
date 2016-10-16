package ondre.sg.countdowns;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import ondre.sg.Main;
import ondre.sg.managers.GameManager;
import ondre.sg.managers.TitleManager;
import ondre.sg.utils.GameState;

public class PregameCountdown {

public static int pregame = GameManager.pregameTime;
	
	public static void startpregameTimer() {
		new Runnable() {
		public int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, this, 0L, 20L);	
			@Override
			public void run() {
				if (pregame > 0) {
					pregame--;
					
					if(pregame <= 15 || pregame > 0) {
						for(Player alive : GameManager.alive) {
						alive.setLevel(pregame);
						
						}
					}
					
					if(pregame <= 5 && pregame > 0) {
						for(Player alive : GameManager.alive) {
						TitleManager title = new TitleManager(ChatColor.RED + "" + pregame);
						title.send(alive);
						
						Location location = alive.getLocation();
						alive.playSound(location, Sound.BLOCK_NOTE_PLING, 1, 1);
						
						}
					}
					
					if(pregame == 0) {
						for(Player alive : GameManager.alive) {
							TitleManager title = new TitleManager(ChatColor.RED + "FIGHT!", "", 0, 2, 0);
							title.send(alive);
							
							Location location = alive.getLocation();
							alive.playSound(location, Sound.BLOCK_NOTE_PLING, 5, 5);
							
						}	
					}
					
					if(pregame == 0) {
						GameManager.state = GameState.INGAME;
						GameCountdown.startgameTimer();
						
						} 
					
					} else {
						
						Bukkit.getScheduler().cancelTask(taskID);
				}
			}
		};
	}
}