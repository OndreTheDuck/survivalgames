package ondre.sg.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import ondre.sg.managers.GameManager;
import ondre.sg.utils.GameState;

public class FreezeListener implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		if (GameManager.state == GameState.PREGAME) {
			if (((event.getTo().getX() != event.getFrom().getX()) || (event.getTo().getZ() != event.getFrom().getZ()))) {
				event.setTo(event.getFrom());
			}
		}
	}
}
