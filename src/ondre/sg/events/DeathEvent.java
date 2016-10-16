package ondre.sg.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import ondre.sg.managers.ActionManager;
import ondre.sg.managers.GameManager;
import ondre.sg.managers.TitleManager;
import ondre.sg.utils.GameUtils;

public class DeathEvent implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {

		Player dead = event.getEntity().getPlayer();
		GameUtils.setSpectator();
		dead.setHealth(20);
		
		TitleManager title = new TitleManager(ChatColor.RED + "ELIMINATED!", ChatColor.GRAY + "You can now spectate!", 1, 5, 1);
		title.send(dead);
						
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.hidePlayer(dead);
			
			ActionManager actionBar = new ActionManager(ChatColor.YELLOW + "There are " + ChatColor.RED + GameManager.alive.size() + ChatColor.YELLOW + " tributes alive!");
			actionBar.sendToPlayer(player);
		
			}
		}
	}