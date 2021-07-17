package us.forestbukkit.kitpvp.kit.impl;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import us.forestbukkit.kitpvp.kit.Kit;
import us.forestbukkit.kitpvp.utils.ItemBuilder;

public class Chemist extends Kit{

    public Chemist() {
        super("Chemist", Material.POTION, new String[]{
                "&7Slow down your enemies with poison or",
                "&7take them down with damage pots that",
                "&7refill upon kills."
        }, 250);
    }
    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[]{
                new ItemBuilder(Material.CHAINMAIL_BOOTS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.DURABILITY, 3).build(),
                new ItemBuilder(Material.CHAINMAIL_LEGGINGS).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.DURABILITY, 3).build(),
                new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.DURABILITY, 3).build(),
                new ItemBuilder(Material.CHAINMAIL_HELMET).enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.DURABILITY, 3).build(),
        };
    }

    @Override
    public ItemStack[] getItems() {
        return new ItemStack[]{
                new ItemBuilder(Material.DIAMOND_SWORD)
                        .enchantment(Enchantment.DAMAGE_ALL, 1)
                        .enchantment(Enchantment.DURABILITY, 3)
                        .build(),
                new ItemStack(Material.POTION, 4, (short) 16428),
                new ItemStack(Material.POTION, 2,(short) 16388)
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
        };
    }
}
