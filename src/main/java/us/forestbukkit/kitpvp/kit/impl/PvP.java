package us.forestbukkit.kitpvp.kit.impl;

import us.forestbukkit.kitpvp.kit.Kit;
import us.forestbukkit.kitpvp.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PvP extends Kit {

    public PvP() {
        super("PvP", Material.DIAMOND_SWORD, new String[]{
            "&7Basic PvP class."
        }, 0);
    }

        @Override
        public ItemStack[] getItems() {
            return new ItemStack[]{
                new ItemBuilder(Material.DIAMOND_SWORD)
                .enchantment(Enchantment.DAMAGE_ALL, 1)
                .enchantment(Enchantment.DURABILITY, 3)
                .build(),
            };
        }

	@Override
	public ItemStack[] getArmor() {
		return new ItemStack[]{
				new ItemBuilder(Material.IRON_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.DURABILITY, 3).build(),
				new ItemBuilder(Material.IRON_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.DURABILITY, 3).build(),
				new ItemBuilder(Material.IRON_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.DURABILITY, 3).build(),
				new ItemBuilder(Material.IRON_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.DURABILITY, 3).build()
		};
	}

	@Override
	public ItemStack getAbilityItem() {
		return null;
	}

	@Override
	public PotionEffect[] getPotionEffects() {
		return new PotionEffect[]{
				new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0)
		};
	}
}
