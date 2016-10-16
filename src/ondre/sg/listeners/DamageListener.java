package ondre.sg.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import ondre.sg.managers.GameManager;
import ondre.sg.utils.GameState;

public class DamageListener implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		if (GameManager.state == GameState.LOBBY || GameManager.state == GameState.RESTART) {
			event.setCancelled(true);
		} else {
			event.setCancelled(false);
		}
	}

	@EventHandler
	public void onFall(EntityDamageEvent event) {
		if (GameManager.state == GameState.LOBBY || GameManager.state == GameState.RESTART) {
			event.setCancelled(true);
		} else {
			event.setCancelled(false);
		}
	}
}