package ondre.sg.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ondre.sg.managers.GameManager;

public class GameUtils {
	
	public static Date date;
	public static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	public static ItemStack create(Material mat, int amount) {
		return new ItemStack(mat, amount);
		
	}
	
	public static ItemStack createCustom(Material mat, int amount, short id, String display) {
		ItemStack it = new ItemStack(mat, amount, id);
		ItemMeta meta = it.getItemMeta();
		meta.setDisplayName(display);
		it.setItemMeta(meta);
		return it;
	}

	public static int randomInt(int min, int max) {
		Random random = new Random();
		int i = random.nextInt((max - min) + 1) + min;
		return i;	
		
		}	
	
	public static void setAlive() {
		for(Player alive : GameManager.alive) {
			alive.getInventory().clear();
			alive.setFoodLevel(20);
			alive.setExp(0);
			alive.setHealth(20);
			alive.setAllowFlight(false);
			alive.setInvulnerable(false);
			alive.setLevel(0);
			alive.getActivePotionEffects().clear();
			alive.setWalkSpeed(0.2F);
			alive.setGameMode(GameMode.SURVIVAL);
		}
	}
	public static void setSpectator() {
		for(Player dead : GameManager.dead) {
			dead.getInventory().clear();
			dead.setFoodLevel(20);
			dead.setHealth(20);
			dead.setInvulnerable(true);
			dead.setLevel(0);
			dead.getActivePotionEffects().clear();
			dead.setWalkSpeed(0.2F);
			dead.setGameMode(GameMode.ADVENTURE);
			dead.setAllowFlight(!dead.getAllowFlight());
			dead.setFlying(dead.getAllowFlight());
			
			}	
		}
	
	public static void giveSpectatorTools() {
		for(Player dead : GameManager.dead) {
			ItemStack compass = new ItemStack(Material.COMPASS);
			ItemMeta im = compass.getItemMeta();
			im.setDisplayName(ChatColor.GREEN + "Click to Spectate!");
			compass.setItemMeta(im);
			
			dead.getInventory().addItem(compass);
		}
	}
}