package us.forestbukkit.kitpvp.listeners;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import us.forestbukkit.kitpvp.KitPvP;
import us.forestbukkit.kitpvp.utils.CC;

public class TagListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            ItemStack itemStack = player.getItemInHand();
                if (itemStack.getType() == Material.ENDER_PEARL) {
                    if(!KitPvP.pearlcooldown.onCooldown(player)) {
                    KitPvP.pearlcooldown.applyCooldown(player, 16l);
                    } else {
                        player.sendMessage(CC.translate("&cYou cannot use enderpearls for &e" + KitPvP.pearlcooldown.getRemaining(player) + " seconds!"));
                        event.setCancelled(true);
                    }
                }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(event.getDamager().getType() == EntityType.PLAYER && event.getEntity().getType() == EntityType.PLAYER){
            Player damager = (Player) event.getDamager();
            Player attacked = (Player) event.getEntity();
            if(!KitPvP.pvptag.onCooldown(damager)){
            damager.sendMessage(CC.translate("&cYou have been &ecombat tagged!"));}
            if(!KitPvP.pvptag.onCooldown(attacked)){
                attacked.sendMessage(CC.translate("&cYou have been &ecombat tagged!"));}

            KitPvP.pvptag.removeCooldown(damager);
            KitPvP.pvptag.removeCooldown(attacked);

            KitPvP.pvptag.applyCooldown(damager, 30l);
            KitPvP.pvptag.applyCooldown(attacked, 30l);
        }
    }
}
