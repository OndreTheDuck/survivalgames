package ondre.sg.managers;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ondre.sg.utils.GameState;

public class GameManager {
	
	public static String prefix = ChatColor.YELLOW + "";
		
	public static GameState state;
	public static ArrayList<Player> alive = new ArrayList<>();
	public static ArrayList<Player> dead = new ArrayList<>();

	
	public static int minPlayers = 2;
	public static int maxPlayers = 24;
	
	public static int lobbyTime = 11; 
	public static int pregameTime = 16;
	public static int gameTime = 601;
	public static int restartTime = 11;
	
}

