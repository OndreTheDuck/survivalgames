package ondre.sg;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import ondre.sg.commands.SetLobby;
import ondre.sg.commands.SetSpawn;
import ondre.sg.events.DeathEvent;
import ondre.sg.listeners.BlockListener;
import ondre.sg.listeners.ChestListener;
import ondre.sg.listeners.DamageListener;
import ondre.sg.listeners.DeathListener;
import ondre.sg.listeners.FoodListener;
import ondre.sg.listeners.FreezeListener;
import ondre.sg.listeners.ItemListener;
import ondre.sg.listeners.JoinListener;
import ondre.sg.listeners.LeaveListener;
import ondre.sg.managers.GameManager;
import ondre.sg.utils.GameState;

public class Main extends JavaPlugin {
	
	public static Main main;
	
	public void onEnable() {
		main = this;
		GameManager.state = GameState.LOBBY;
		
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new JoinListener(), this);
		pm.registerEvents(new DamageListener(), this);
		pm.registerEvents(new BlockListener(), this);
		pm.registerEvents(new DeathListener(), this);
		pm.registerEvents(new ChestListener(), this);
		pm.registerEvents(new LeaveListener(), this);
		pm.registerEvents(new FreezeListener(), this);
		pm.registerEvents(new FoodListener(), this);
		pm.registerEvents(new ItemListener(), this);

		pm.registerEvents(new DeathEvent(), this);

		getCommand("setlobby").setExecutor(new SetLobby());
		getCommand("setspawn").setExecutor(new SetSpawn());
		
 		}
	}

