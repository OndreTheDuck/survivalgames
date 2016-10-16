package ondre.sg.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import ondre.sg.managers.GameManager;
import ondre.sg.utils.GameState;

public class FoodListener implements Listener{
	
	@EventHandler
	public void onHunger(FoodLevelChangeEvent event) {
		Player player = (Player) event.getEntity();
		
		if (GameManager.state == GameState.LOBBY || GameManager.state == GameState.PREGAME || GameManager.state == GameState.RESTART)  {	
			event.setCancelled(true);
			
		} 
		
		if(GameManager.state == GameState.INGAME) {
			event.setCancelled(false);
		}
		
		if(GameManager.state == GameState.INGAME || GameManager.state == GameState.RESTART) {
			if(player != GameManager.alive) {
				event.setCancelled(true);
			}
		}
	}
}
