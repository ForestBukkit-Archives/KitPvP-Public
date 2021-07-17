package us.forestbukkit.kitpvp.listeners;

import org.bukkit.inventory.ItemStack;
import us.forestbukkit.kitpvp.KitPvP;
import us.forestbukkit.kitpvp.kit.Rewards;
import us.forestbukkit.kitpvp.user.User;
import us.forestbukkit.kitpvp.user.ui.kit.KitSelectionMenu;
import us.forestbukkit.kitpvp.utils.CC;
import us.forestbukkit.kitpvp.utils.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import java.util.Locale;

public class GameListener implements Listener {

    private final KitPvP plugin = KitPvP.getInstance();

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        User user = KitPvP.getInstance().getUserManager().getOrCreate(player.getUniqueId());
        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (!player.getItemInHand().hasItemMeta()) return;
            if (player.getItemInHand().isSimilar(PlayerUtil.KIT_SELECTOR)) new KitSelectionMenu().openMenu(event.getPlayer());
            else if (player.getItemInHand().toString().contains("&cYou haven't used a kit yet!")) return;
            else if (player.getItemInHand().isSimilar(PlayerUtil.getPreviousKitItem(event.getPlayer()))) KitPvP.getInstance().getKitManager().getKitByName(user.getLastKitName()).equipKit(player);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        PlayerUtil.resetPlayer(player);
        PlayerUtil.resetHotbar(player);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);

        Player player = event.getEntity();
        Bukkit.getScheduler().runTaskLater(KitPvP.getInstance(), () -> player.spigot().respawn(), 1L);

        if (event.getEntity().getKiller() != null) {
            User kUser = this.plugin.getUserManager().getByUuid(event.getEntity().getKiller().getUniqueId());
            User dUser = this.plugin.getUserManager().getByUuid(player.getUniqueId());
            kUser.setCurrentKillstreak(kUser.getCurrentKillstreak() + 1);

            event.getEntity().sendMessage(CC.translate("&cYou have been killed by &r" + event.getEntity().getKiller().getDisplayName() + "&c."));
            if(kUser.getCurrentKillstreak() > kUser.getHighestKillstreak()) event.getEntity().getKiller().sendMessage(CC.translate("&a&lNew record! &eYou're on a killstreak of&d " + kUser.getCurrentKillstreak() + ChatColor.YELLOW + "!"));
            if(dUser.getCurrentKillstreak() > 0)CC.broadcast(CC.translate(event.getEntity().getKiller().getDisplayName() + " &ehas ended &r" + event.getEntity().getDisplayName() + "&e's killstreak of &d" + dUser.getCurrentKillstreak() + "&e!"));
            event.getEntity().getKiller().sendMessage(CC.translate("&9You have killed &r" + player.getDisplayName() + " &9for &a" + 5 + " credits."));
            kUser.setCredits(kUser.getCredits() + 5);

            kUser.setHighestKillstreak(kUser.getCurrentKillstreak());
            kUser.setKills(kUser.getKills() + 1);
            dUser.setDeaths(dUser.getDeaths() + 1);
            Rewards.giveDeathReward(event.getEntity().getKiller());

        } else {
            event.getEntity().sendMessage(CC.translate("&cYou have died."));

            User dUser = this.plugin.getUserManager().getByUuid(player.getUniqueId());
            dUser.setDeaths(dUser.getDeaths() + 1);
        }
    }

    @EventHandler
    public void onSoupConsumption(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (player.getItemInHand().getType() == Material.MUSHROOM_SOUP && player.getHealth() < 19.5) {
            player.setHealth(Math.min(player.getHealth() + 7.0, 20.0));
            player.getItemInHand().setType(Material.BOWL);
            player.updateInventory();
        }
    }

    @EventHandler
    public void onBowlDrop(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack().getType() == Material.BOWL) {
            event.getItemDrop().remove();
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event){
        Player player = event.getPlayer();
        if(!(event.getItem().getItemStack().getType() == Material.MUSHROOM_SOUP)){
            event.setCancelled(true);
        }
    }

}
