package ondre.sg.listeners;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import ondre.sg.managers.GameManager;
import ondre.sg.utils.GameState;
import ondre.sg.utils.GameUtils;

public class ChestListener implements Listener {
	
	private HashMap<Location, Inventory> chests;
	private ArrayList<ItemStack> loot;
	
	public ChestListener() {
		chests = new HashMap<Location, Inventory>();
		loot = new ArrayList<ItemStack>();
	}

	@EventHandler
	public void onChestOpen(PlayerInteractEvent event) {
		Player player = event.getPlayer();

		if (event.getClickedBlock() != null) {
			if (event.getClickedBlock().getType() == Material.CHEST) {
				event.setCancelled(true);

				if (GameManager.alive.contains(player) && GameManager.state == GameState.INGAME) {
					if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
						if (!chests.containsKey(event.getClickedBlock().getLocation())) {

							registerLoot();
							Inventory inv = Bukkit.createInventory(null, 9 * 3, "Crate");
							for (int i = 0; i < GameUtils.randomInt(6, 12); i++) {
								inv.setItem(GameUtils.randomInt(0, inv.getSize() - 1),
										loot.get(GameUtils.randomInt(0, loot.size() - 1)));
							}
							chests.put(event.getClickedBlock().getLocation(), inv);
						}
						event.getPlayer().openInventory(chests.get(event.getClickedBlock().getLocation()));
					}
				}
			}
		}
	}

	public void registerLoot() {
		loot.clear();
		loot.add(GameUtils.create(Material.WOOD_SWORD, 1));
		loot.add(GameUtils.create(Material.COOKED_CHICKEN, 2));
		loot.add(GameUtils.create(Material.GOLD_PICKAXE, 1));
		loot.add(GameUtils.create(Material.COOKED_FISH, 2));
		loot.add(GameUtils.create(Material.BOW, 1));
		loot.add(GameUtils.create(Material.ARROW, 3));
		loot.add(GameUtils.create(Material.IRON_CHESTPLATE, 1));
		loot.add(GameUtils.create(Material.IRON_LEGGINGS, 1));
		loot.add(GameUtils.create(Material.IRON_HELMET, 1));
		loot.add(GameUtils.create(Material.IRON_BOOTS, 1));
		loot.add(GameUtils.create(Material.CHAINMAIL_BOOTS, 1));
		loot.add(GameUtils.create(Material.CHAINMAIL_CHESTPLATE, 1));
		loot.add(GameUtils.create(Material.CHAINMAIL_LEGGINGS, 1));
		loot.add(GameUtils.create(Material.CHAINMAIL_HELMET, 1));
		loot.add(GameUtils.create(Material.GOLDEN_APPLE, 1));
		loot.add(GameUtils.create(Material.ELYTRA, 1));
		loot.add(GameUtils.create(Material.SHIELD, 1));
		loot.add(GameUtils.create(Material.APPLE, 1));
		loot.add(GameUtils.create(Material.STONE_SWORD, 1));
		loot.add(GameUtils.create(Material.COOKED_FISH, 2));
		loot.add(GameUtils.create(Material.COOKED_MUTTON, 1));
		loot.add(GameUtils.create(Material.COOKED_RABBIT, 3));
		loot.add(GameUtils.create(Material.APPLE, 2));
		loot.add(GameUtils.create(Material.GRILLED_PORK, 2));

	}
}
