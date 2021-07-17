package us.forestbukkit.kitpvp.kit.impl;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import us.forestbukkit.kitpvp.kit.Kit;
import us.forestbukkit.kitpvp.utils.ItemBuilder;

public class Astronaut extends Kit {

    public Astronaut() {
        super("Astronaut", Material.ENDER_STONE, new String[]{
                "&7Being an Astronaut",
                "&7you live with zero gravity."
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
                new ItemBuilder(Material.LEATHER_BOOTS).color(Color.BLUE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.DURABILITY, 3).build(),
                new ItemBuilder(Material.IRON_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.DURABILITY, 3).build(),
                new ItemBuilder(Material.DIAMOND_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.DURABILITY, 3).build(),
                new ItemBuilder(Material.GLASS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.DURABILITY, 3).build(),
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
                new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 4)
        };
    }
}
