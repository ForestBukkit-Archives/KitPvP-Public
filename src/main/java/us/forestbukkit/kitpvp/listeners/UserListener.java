package us.forestbukkit.kitpvp.listeners;

import us.forestbukkit.kitpvp.KitPvP;
import us.forestbukkit.kitpvp.user.User;
import us.forestbukkit.kitpvp.utils.CC;
import us.forestbukkit.kitpvp.utils.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import java.util.Date;

public class UserListener implements Listener {

    private final KitPvP plugin = KitPvP.getInstance();

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        Player player = Bukkit.getPlayer(event.getUniqueId());
        if (player != null && player.isOnline()) {
            event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
            event.setKickMessage(CC.translate("&cYou tried to login too quickly after disconnecting.\n&cTry again in a few seconds."));

            this.plugin.getServer().getScheduler().runTask(this.plugin, () -> player.kickPlayer(CC.translate("&cDuplicate login kick")));
            return;
        }

        User user = this.plugin.getUserManager().getOrCreate(event.getUniqueId());
        user.save();
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerLoginEvent(PlayerLoginEvent event) {
        User user = this.plugin.getUserManager().getOrCreate(event.getPlayer().getUniqueId());

        if (user == null) {
            event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            event.setKickMessage("§cAn error has ocurred while loading your profile. Please reconnect.");
            return;
        }

        if (!user.isLoaded()) {
            user.save();
            event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            event.setKickMessage("§cAn error has ocurred while loading your profile. Please reconnect.");
        }
    }

    @EventHandler
    public void onFirstJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.sendMessage(CC.MENU_BAR);
        player.sendMessage(CC.translate("&7Welcome to &5Destonic &dKitPvP!"));
        player.sendMessage("");
        player.sendMessage(CC.translate("&7Website: &fwww.destonic.cc"));
        player.sendMessage(CC.translate("&7Store: &fdonate.destonic.cc"));
        player.sendMessage(CC.translate("&7Rules: &f/rules"));
        player.sendMessage("");
        player.sendMessage(CC.translate("&5KitPvP &7is currently in &cbeta..."));
        player.sendMessage(CC.MENU_BAR);
        player.teleport(Bukkit.getWorld("world").getSpawnLocation());
        User user = this.plugin.getUserManager().getOrCreate(event.getPlayer().getUniqueId());
        user.save();
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        Player player = event.getPlayer();
        User user = this.plugin.getUserManager().getOrCreate(event.getPlayer().getUniqueId());
        PlayerUtil.resetHotbar(player);
        user.setCurrentKillstreak(0);
        KitPvP.pvptag.removeCooldown(player);
        KitPvP.pearlcooldown.removeCooldown(player);
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        User user = this.plugin.getUserManager().getOrCreate(event.getPlayer().getUniqueId());
        user.save();
    }

    @EventHandler
    public void onPlayerKickEvent(PlayerKickEvent event) {
        User user = this.plugin.getUserManager().getOrCreate(event.getPlayer().getUniqueId());
        user.save();
    }
}
