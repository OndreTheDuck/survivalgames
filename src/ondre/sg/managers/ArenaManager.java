package ondre.sg.managers;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import ondre.sg.Main;

public class ArenaManager {
	
	
	public static File file = new File("plugins/" + Main.main.getName(), "arena.yml");
	public static FileConfiguration config = YamlConfiguration.loadConfiguration(file);
	
	public static void setSpawn(String arenaName, int number, Location loc) {
		config.set("arenas." + arenaName + "." + number + ".world", loc.getWorld().getName());
		config.set("arenas." + arenaName + "." + number + ".x", loc.getX());
		config.set("arenas." + arenaName + "." + number + ".y", loc.getY());
		config.set("arenas." + arenaName + "." + number + ".z", loc.getZ());
		config.set("arenas." + arenaName + "." + number + ".yaw", loc.getYaw());
		config.set("arenas." + arenaName + "." + number + ".pitch", loc.getPitch());
		saveConfig();
		
	}
	
	public static Location getSpawn(String arenaName, int number) {
		Location loc;
		try {
		World w = Bukkit.getWorld(config.getString("arenas." + arenaName + "." + number + ".world"));
		double x = config.getDouble("arenas." + arenaName + "." + number + ".x");
		double y = config.getDouble("arenas." + arenaName + "." + number + ".y");
		double z = config.getDouble("arenas." + arenaName + "." + number + ".z");
		
		loc = new Location(w, x, y, z);
		loc.setYaw(config.getInt("arenas." + arenaName + "." + number + ".yaw"));
		loc.setPitch(config.getInt("arenas." + arenaName + "." + number + ".pitch"));
		
		} catch(Exception ex) {
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
	
	public static void mapTeleport() {
		int count = 1;
		
		for(String arena : config.getConfigurationSection("arenas").getKeys(false)) {
		Random random = new Random();
		int index = 0;
		index = random.nextInt(arena.length() % arena.length() + 1);
		if(index < 1) {
		
			Bukkit.broadcastMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "You are currently playing on the map " + ChatColor.AQUA + "" + ChatColor.BOLD + arena.toUpperCase() + ChatColor.YELLOW + "" + ChatColor.BOLD + "!");
			for(Player alive : GameManager.alive) {			
				alive.teleport(getSpawn(arena, count));
				count++;
					}
				}
			}
		}
	}

