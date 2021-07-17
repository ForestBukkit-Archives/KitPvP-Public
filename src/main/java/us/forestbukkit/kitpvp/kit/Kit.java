package us.forestbukkit.kitpvp.kit;

import lombok.Getter;
import us.forestbukkit.kitpvp.KitPvP;
import us.forestbukkit.kitpvp.user.User;
import us.forestbukkit.kitpvp.utils.CC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;

@Getter
public abstract class Kit {

	private final String name;
	private final Material icon;
	private final String[] desc;
	private final int price;

	public Kit(String name, Material icon, String[] desc, int price) {
		this.name = name;
        this.icon = icon;
        this.desc = desc;
		this.price = price;
	}

	public void equipKit(Player player) {
		player.getInventory().clear();
		for (PotionEffect effect : player.getActivePotionEffects())
			player.removePotionEffect(effect.getType());
		Arrays.stream(this.getPotionEffects()).forEach(player::addPotionEffect);
		player.getInventory().setArmorContents(null);
		player.getInventory().setArmorContents(this.getArmor());
		player.getInventory().addItem(getItems());
		if(!(this.getAbilityItem()==null)){
		player.getInventory().addItem(getAbilityItem());}
		this.giveSoups(player);

		player.updateInventory();
        player.sendMessage(CC.translate("&eYou have chosen the &d" + this.getName() + "&e kit."));

		User user = KitPvP.getInstance().getUserManager().getOrCreate(player.getUniqueId());
		user.setLastKitName(this.getName());
	}

	public abstract PotionEffect[] getPotionEffects();

	public abstract ItemStack[] getArmor();

	public abstract ItemStack[] getItems();

	public abstract ItemStack getAbilityItem();

	private void giveSoups(Player player) {
		final ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
		for (ItemStack inv : player.getInventory().getContents()) {
			if (inv == null) {
				player.getInventory().addItem(soup);
			}
		}
	}
}
