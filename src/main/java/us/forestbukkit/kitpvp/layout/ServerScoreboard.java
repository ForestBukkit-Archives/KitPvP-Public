package us.forestbukkit.kitpvp.layout;

import org.bukkit.ChatColor;
import us.forestbukkit.kitpvp.KitPvP;
import us.forestbukkit.kitpvp.user.User;
import us.forestbukkit.kitpvp.utils.scoreboard.BoardAdapter;
import us.forestbukkit.kitpvp.utils.scoreboard.BoardStyle;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static us.forestbukkit.kitpvp.utils.CC.translate;

public class ServerScoreboard implements BoardAdapter {

    @Override
    public String getTitle(Player player) {
        return translate("&3&lF&a&lB &7❘ &fKitPvP");
    }

    @Override
    public List<String> getLines(Player player) {
        User user = KitPvP.getInstance().getUserManager().getByUuid(player.getUniqueId());
        double KDR = 0;
        if(!(user.getDeaths()==0)){
        int KDRunrounded = user.getKills() / user.getDeaths();
        KDR = (double)Math.round(KDRunrounded * 10) / 10;} else {
        }


        ArrayList list = new ArrayList();
        list.add("&7&m----------------------");
        list.add("&dKills: &e" + user.getKills());
        list.add("&dDeaths: &e" + user.getDeaths());
        list.add("&dKillstreak: &e" + user.getCurrentKillstreak());
        list.add("&dHighest Killstreak: &e" + user.getHighestKillstreak());
        list.add("&dCredits: &e" + user.getCredits());
        if(!(KDR == 0)){
        list.add("&dKDR: &e" + KDR);} else{
            list.add("&dKDR: &cNothing...");
        }
        if(KitPvP.pearlcooldown.onCooldown(player)){
            list.add("&dEnder Pearl: &e" + KitPvP.pearlcooldown.getRemaining(player));
        }
        if(KitPvP.pvptag.onCooldown(player)){
            list.add("&dPvP Tag: &e" + KitPvP.pvptag.getRemaining(player));
        }
        if(KitPvP.freekitmode){ list.add("&a&lfree-kit-mode");}
        list.add("&7&m----------------------");

        ArrayList arcane = new ArrayList();
        arcane.add("&7&m----------------------");
        arcane.add("&aKills: &f" + user.getKills());
        arcane.add("&aDeaths: &f" + user.getDeaths());
        if(KitPvP.pearlcooldown.onCooldown(player)){
            arcane.add("&3Ender Pearl: &f" + KitPvP.pearlcooldown.getRemaining(player));
        }
        if(KitPvP.pvptag.onCooldown(player)){
            arcane.add("&4PvP Tag: &f" + KitPvP.pvptag.getRemaining(player));
        }
        arcane.add("");
        arcane.add("&2www.&3forest&abukkit&2.com");
        arcane.add("&7&m----------------------");

        return arcane;
    }

    @Override
    public BoardStyle getBoardStyle(Player player) {
        return BoardStyle.MODERN;
    }
}
