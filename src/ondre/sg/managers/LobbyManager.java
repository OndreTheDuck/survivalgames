package ondre.sg.managers;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import ondre.sg.Main;

public class LobbyManager {
	
	public static File file = new File("plugins/" + Main.main.getName(), "lobby.yml");
	public static FileConfiguration config = YamlConfiguration.loadConfiguration(file);
	
	public static void setLocation(String name, Location loc) {
		config.set(name + ".world", loc.getWorld().getName());
		config.set(name + ".x", loc.getX());
		config.set(name + ".y", loc.getY());
		config.set(name + ".z", loc.getZ());
		config.set(name + ".yaw", loc.getYaw());
		config.set(name + ".pitch", loc.getPitch());
		saveConfig();
		
	}
	
	public static Location getLocation(String name) {
		Location loc;
		try {
		World w = Bukkit.getWorld(config.getString(name + ".world"));
		double x = config.getDouble(name + ".x");
		double y = config.getDouble(name + ".y");
		double z = config.getDouble(name + ".z");
		
		loc = new Location(w, x, y, z);
		loc.setYaw(config.getInt(name + ".yaw"));
		loc.setPitch(config.getInt(name + ".pitch"));
		
		}catch(Exception ex) {
			loc = new Location(Bukkit.getWorlds().get(0), 0, 0, 0);
			
		}
		
		return loc;
		
	}
	
	public static void saveConfig() {
		
		try {
			config.save(file);
		} catch (IOException e) {
			
		}
	}
}
