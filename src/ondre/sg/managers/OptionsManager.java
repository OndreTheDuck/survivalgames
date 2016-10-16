package ondre.sg.managers;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import ondre.sg.Main;

public class OptionsManager {

	public File file = new File("plugins/" + Main.main.getName(), "config.yml");
	public FileConfiguration config = YamlConfiguration.loadConfiguration(file);
	
	public void register() {
		config.options().copyDefaults(true);
		config.addDefault("MinPlayers", GameManager.minPlayers);
		config.addDefault("MaxPlayers", GameManager.maxPlayers);
		saveConfig();
		
		GameManager.minPlayers = config.getInt("MinPlayers");
		GameManager.maxPlayers = config.getInt("MaxPlayers");
		
	}
	
	public void saveConfig() {
		try {
		config.save(file);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
