package us.forestbukkit.kitpvp.kit.impl;

import us.forestbukkit.kitpvp.kit.Kit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class Pro extends Kit {

    public Pro() {
        super("Pro", Material.IRON_CHESTPLATE, new String[]{
            "&7Made only for the best",
            "&7players out there, this",
            "&7kit features no enchantments."
        }, 250);
    }

	@Override
	public ItemStack[] getArmor() {
		return new ItemStack[]{
                new ItemStack(Material.IRON_BOOTS),
                new ItemStack(Material.IRON_LEGGINGS),
                new ItemStack(Material.IRON_CHESTPLATE),
				new ItemStack(Material.IRON_HELMET),
		};
	}

	@Override
	public ItemStack[] getItems() {
		return new ItemStack[]{
		    new ItemStack(Material.DIAMOND_SWORD)
        };
	}

	@Override
	public ItemStack getAbilityItem() {
		return null;
	}

	@Override
	public PotionEffect[] getPotionEffects() {
		return new PotionEffect[0];
	}
}
