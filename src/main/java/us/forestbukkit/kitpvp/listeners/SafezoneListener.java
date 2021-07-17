package us.forestbukkit.kitpvp.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerAttackEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import us.forestbukkit.kitpvp.utils.PlayerUtil;

public class SafezoneListener implements Listener {
    @EventHandler
    public void onPickup(PlayerPickupItemEvent event){
        Player player = event.getPlayer();
        if(player.getInventory().contains(PlayerUtil.KIT_SELECTOR)){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onDamage(final EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && !(event.getEntity() instanceof Player)) {
            Player player = (Player) event.getEntity();
            Player damager = (Player) event.getDamager();
            if(damager.getInventory().contains(PlayerUtil.KIT_SELECTOR) || player.getInventory().contains(PlayerUtil.KIT_SELECTOR)){
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onTakeDamage(EntityDamageEvent event){
        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            if(player.getInventory().contains(PlayerUtil.KIT_SELECTOR)) {
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        Player player = event.getPlayer();
        if(event.getItemDrop().getItemStack() == PlayerUtil.KIT_SELECTOR) {event.setCancelled(true);}
        if(event.getItemDrop().getItemStack() == PlayerUtil.getPreviousKitItem(player)){ event.setCancelled(true);}
        if(event.getItemDrop().getItemStack() == PlayerUtil.STATS_ITEM){ event.setCancelled(true);}
        //Doesn't go with the class but might aswell adress it
        if(event.getItemDrop().getItemStack().getData().getItemType() == (Material.DIAMOND_SWORD) || event.getItemDrop().getItemStack().getData().getItemType() == (Material.IRON_SWORD) || event.getItemDrop().getItemStack().getData().getItemType() == (Material.STONE_SWORD) ) {player.sendMessage(ChatColor.RED + "You can't drop your weapon!"); event.setCancelled(true);}
    }
}
