package ondre.sg.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import ondre.sg.Main;
import ondre.sg.countdowns.RestartCountdown;
import ondre.sg.managers.GameManager;
import ondre.sg.utils.GameState;

public class LeaveListener implements Listener {
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {

		Player player = event.getPlayer();
		Location location = player.getLocation();
		Inventory inventory = player.getInventory();

		event.setQuitMessage(null);
		GameManager.alive.remove(player);

		if(player == GameManager.alive) {
		for (ItemStack items : inventory.getContents()) {
			if (items != null) {
				location.getWorld().dropItemNaturally(location, items.clone());
			}
			inventory.clear();
		}
	}

		if (GameManager.state == GameState.LOBBY && GameManager.alive.size() < GameManager.minPlayers) {
			Bukkit.getScheduler().cancelTasks(Main.main);

		}

		if (GameManager.state == GameState.PREGAME && GameManager.alive.size() == 0) {
			RestartCountdown.startrestartTimer();

		}

		if (GameManager.state == GameState.PREGAME && GameManager.alive.size() == 1) {
			Player winner = GameManager.alive.get(0);
			Bukkit.broadcastMessage(GameManager.prefix + ChatColor.GREEN + winner.getDisplayName().toUpperCase() + ChatColor.WHITE + " has won the Survival Games!");

			GameManager.state = GameState.RESTART;
			RestartCountdown.startrestartTimer();

		}

		if (GameManager.state == GameState.INGAME && GameManager.alive.size() == 0) {
			RestartCountdown.startrestartTimer();

		}

		if (GameManager.state == GameState.INGAME && GameManager.alive.size() == 1) {
			Player winner = GameManager.alive.get(0);
			Bukkit.broadcastMessage(GameManager.prefix + ChatColor.GREEN + winner.getDisplayName().toUpperCase() + ChatColor.WHITE + " has won the Survival Games!");

			GameManager.state = GameState.RESTART;
			RestartCountdown.startrestartTimer();
		}
	}
}
