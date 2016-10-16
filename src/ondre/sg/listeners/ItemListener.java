package ondre.sg.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import ondre.sg.managers.GameManager;
import ondre.sg.utils.GameState;

public class ItemListener implements Listener {
	
	@EventHandler
	public void onPickUp(PlayerPickupItemEvent event) {
		Player player = event.getPlayer();
		
		if(GameManager.state == GameState.PREGAME || GameManager.state == GameState.RESTART) {
			if(player != GameManager.alive) {
				event.setCancelled(true);
			}
		}
	}
}
