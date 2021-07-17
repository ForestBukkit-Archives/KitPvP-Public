package us.forestbukkit.kitpvp.kit.impl;

import us.forestbukkit.kitpvp.kit.Kit;
import us.forestbukkit.kitpvp.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Sonic extends Kit {

    public Sonic() {
        super("Sonic", Material.SUGAR, new String[]{
                "&7With the boost of speed 3",
                "&7you can travel anywhere in minutes"
        }, 500);
    }

    @Override
    public ItemStack[] getItems() {
        return new ItemStack[]{
                new ItemBuilder(Material.IRON_SWORD)
                        .enchantment(Enchantment.DAMAGE_ALL, 1)
                        .enchantment(Enchantment.DURABILITY, 3)
                        .build(),
        };
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.CHAINMAIL_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.DURABILITY, 3).build(),
                new ItemBuilder(Material.CHAINMAIL_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.DURABILITY, 3).build(),
        };
    }

    @Override
    public ItemStack getAbilityItem() {
        return null;
    }

    @Override
    public PotionEffect[] getPotionEffects() {
        return new PotionEffect[]{
                new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 7)
        };
    }
}
