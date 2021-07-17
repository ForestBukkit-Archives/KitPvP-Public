package us.forestbukkit.kitpvp.utils;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import us.forestbukkit.kitpvp.KitPvP;
import us.forestbukkit.kitpvp.user.User;

public class PlayerUtil {
	static User user = null;
	public static final ItemStack KIT_SELECTOR = new ItemBuilder(Material.ENCHANTED_BOOK).name("&eKit Selector").build();
	public static final ItemStack STATS_ITEM = new ItemBuilder(Material.SKULL_ITEM).durability(3).name("&aYour Stats").build();

    public static ItemStack getPreviousKitItem(Player player){
		User user = KitPvP.getInstance().getUserManager().getOrCreate(player.getUniqueId());
		if(user.getLastKitName() == null) {
			final ItemStack PREVIOUS_KIT = new ItemBuilder(Material.WATCH).name("&cYou haven't used a kit yet!").build();
			return PREVIOUS_KIT;
		} else { final ItemStack PREVIOUS_KIT = new ItemBuilder(Material.WATCH).name("&eSelect Previous Kit: &d" + user.getLastKitName()).build();
    	return PREVIOUS_KIT;
	}}

	public static void resetPlayer(Player player) {

		player.setGameMode(GameMode.SURVIVAL);

		player.closeInventory();
		player.getInventory().clear();
		player.getInventory().setHeldItemSlot(0);
		player.getInventory().setArmorContents(null);
		player.updateInventory();

		player.setHealth(20.0D);
		player.setFoodLevel(20);
		player.setSaturation(12.8F);
		player.setMaximumNoDamageTicks(20);
		player.setFireTicks(0);
		player.setFallDistance(0.0F);

		player.setLevel(0);
		player.setExp(0.0F);

		player.setWalkSpeed(0.2F);
		player.setFlySpeed(0.2F);
		player.setAllowFlight(false);

		player.getActivePotionEffects().stream().map(PotionEffect::getType).forEach(player::removePotionEffect);
		((CraftPlayer) player).getHandle().getDataWatcher().watch(9, (byte) 0);
	}

	public static void resetHotbar(Player player) {
		User user = KitPvP.getInstance().getUserManager().getOrCreate(player.getUniqueId());

		player.getInventory().setItem(0, KIT_SELECTOR);
		player.getInventory().setItem(1, getPreviousKitItem(player));
		player.getInventory().setItem(4, STATS_ITEM);

		player.updateInventory();
	}
}
