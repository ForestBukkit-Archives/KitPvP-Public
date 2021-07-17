package us.forestbukkit.kitpvp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import us.forestbukkit.kitpvp.KitPvP;
import us.forestbukkit.kitpvp.user.User;
import us.forestbukkit.kitpvp.utils.CC;

public class BowListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
    if (event.getEntityType() != EntityType.PLAYER || !(event.getDamager() instanceof Arrow)) {
        return;
    }

    Player hit = (Player) event.getEntity();
    Player damager = (Player) ((Arrow) event.getDamager()).getShooter();

            int outOf20 = (int) Math.ceil(hit.getHealth());
            int damage = (int) Math.ceil(event.getDamage());
            // we specifically divide by 2.0 (not 2) so that we do floating point math
            // as integer math will just round away the .5
            damager.sendMessage(CC.translate("&6" + hit.getName() + "'s health: &c" + (outOf20 / 2.0) + "&4" + "❤" + "&7(&eYou did &c" + (damage / 2.0) + "&4" + "❤" + "&7)"));

        if(!KitPvP.pvptag.onCooldown(damager)){
            damager.sendMessage(CC.translate("&cYou have been &ecombat tagged!"));}
        if(!KitPvP.pvptag.onCooldown(hit)){
            hit.sendMessage(CC.translate("&cYou have been &ecombat tagged!"));}

        KitPvP.pvptag.removeCooldown(damager);
        KitPvP.pvptag.removeCooldown(hit);

        KitPvP.pvptag.applyCooldown(damager, 30l);
        KitPvP.pvptag.applyCooldown(hit, 30l);
    }
}
