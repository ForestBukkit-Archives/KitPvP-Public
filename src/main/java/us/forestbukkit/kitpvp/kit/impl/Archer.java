package us.forestbukkit.kitpvp.kit.impl;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import us.forestbukkit.kitpvp.kit.Kit;
import us.forestbukkit.kitpvp.utils.ItemBuilder;

public class Archer extends Kit {

    public Archer() {
        super("Archer", Material.BOW, new String[]{
            "&7Special bow that deals scaling",
            "&7damage based on distance!",
            "&7Gain +10 armor durability per kill!"
        }, 0);
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
            new ItemBuilder(Material.LEATHER_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.DURABILITY, 3).build(),
            new ItemBuilder(Material.LEATHER_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.DURABILITY, 3).build(),
            new ItemBuilder(Material.LEATHER_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.DURABILITY, 3).build(),
            new ItemBuilder(Material.LEATHER_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.DURABILITY, 3).build(),
        };
    }

    @Override
    public ItemStack[] getItems() {
        return new ItemStack[]{
            new ItemBuilder(Material.DIAMOND_SWORD)
            .enchantment(Enchantment.DAMAGE_ALL, 1)
            .enchantment(Enchantment.DURABILITY, 3)
            .build(),
            new ItemBuilder(Material.BOW)
            .enchantment(Enchantment.ARROW_DAMAGE, 3)
            .enchantment(Enchantment.DURABILITY, 3)
            .enchantment(Enchantment.ARROW_INFINITE, 1)
            .build(),
            new ItemStack(Material.ENDER_PEARL, 3),
            new ItemStack(Material.ARROW, 1)
        };
    }

    @Override
    public ItemStack getAbilityItem() {
        return null;
    }

    @Override
    public PotionEffect[] getPotionEffects() {
        return new PotionEffect[]{
            new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2),
            new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0)
        };
    }
}
