package ondre.sg.countdowns;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ondre.sg.Main;
import ondre.sg.managers.ActionManager;
import ondre.sg.managers.GameManager;
import ondre.sg.utils.GameState;

public class RestartCountdown {
	
public static int restart = GameManager.restartTime;
	
	public static void startrestartTimer() {
		new Runnable() {
			public int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.main, this, 0L, 20L);

			@Override
			public void run() {
				if (restart > 0) {
					restart--;

					if (restart <= 10) {
						for (Player alive : GameManager.alive) {
							ActionManager actionBar = new ActionManager(ChatColor.YELLOW + "Time until restart: " + ChatColor.GREEN + restart);
							actionBar.sendToPlayer(alive);
						}
					}

					if (restart == 1) {
						GameManager.state = GameState.LOBBY;
					}

					if (restart == 0) {
						Bukkit.shutdown();
					}

				} else {

					Bukkit.getScheduler().cancelTask(taskID);

				}
			}
		};
	}
}
