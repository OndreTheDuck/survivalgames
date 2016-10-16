package ondre.sg.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import ondre.sg.countdowns.RestartCountdown;
import ondre.sg.managers.GameManager;
import ondre.sg.utils.GameState;

public class DeathListener implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		
		Player dead = event.getEntity().getPlayer();
		
		GameManager.alive.remove(dead);
		GameManager.dead.add(dead);
		
		event.setDeathMessage(ChatColor.RED + dead.getDisplayName() + ChatColor.YELLOW + " has been eliminated from the match!");
		
		if (GameManager.state == GameState.INGAME && GameManager.alive.size() == 1) {
			Player winner = GameManager.alive.get(0);
			Bukkit.broadcastMessage(ChatColor.GREEN + winner.getDisplayName().toUpperCase() + ChatColor.YELLOW + " has won the Survival Games!");

			GameManager.state = GameState.RESTART;
			RestartCountdown.startrestartTimer();

		}
		
		for(Player player : Bukkit.getOnlinePlayers()) {
			Location location = event.getEntity().getLocation();
			player.playSound(location, Sound.ENTITY_LIGHTNING_THUNDER, 5L, 5L);
		}
	}
}
