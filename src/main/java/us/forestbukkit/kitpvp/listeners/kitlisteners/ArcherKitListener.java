package us.forestbukkit.kitpvp.listeners.kitlisteners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import us.forestbukkit.kitpvp.KitPvP;
import us.forestbukkit.kitpvp.user.User;

public class ArcherKitListener implements Listener {
    @EventHandler
    public void onKill(PlayerDeathEvent event){
        if(event.getEntity().getLastDamageCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)){
        Player player = event.getEntity().getKiller();
        User user = KitPvP.getInstance().getUserManager().getOrCreate(player.getUniqueId());

        if(user.getCurrentKitName().equalsIgnoreCase("Archer")){
            ItemStack[] armor = player.getInventory().getArmorContents();

            for(int i = 0; i < armor.length; i++)
            {
                if(armor[i] != null)
                    armor[i].setDurability((short)(armor[i].getDurability() - 10));
            }

                player.getInventory().setArmorContents(armor);
            }
        } else return;
    }
}

