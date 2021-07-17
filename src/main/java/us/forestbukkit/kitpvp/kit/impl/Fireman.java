package us.forestbukkit.kitpvp.kit.impl;

import org.bukkit.Color;
import us.forestbukkit.kitpvp.kit.Kit;
import us.forestbukkit.kitpvp.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Fireman extends Kit {

    public Fireman() {
        super("Fireman", Material.WATER_BUCKET, new String[]{
                "&7As a fireman you take",
                "&7no damage from fires"
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
                new ItemBuilder(Material.LEATHER_BOOTS).color(Color.RED).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.DURABILITY, 3).build(),
                new ItemBuilder(Material.LEATHER_LEGGINGS).color(Color.RED).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.DURABILITY, 3).build(),
                new ItemBuilder(Material.LEATHER_CHESTPLATE).color(Color.RED).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.DURABILITY, 3).build(),
                new ItemBuilder(Material.LEATHER_HELMET).color(Color.RED).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.DURABILITY, 3).build(),
        };
    }

    @Override
    public ItemStack getAbilityItem() {
        return null;
    }

    @Override
    public PotionEffect[] getPotionEffects() {
        return new PotionEffect[]{
                new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1),
                new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0)
        };
    }
}
