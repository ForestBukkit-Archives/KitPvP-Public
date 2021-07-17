package us.forestbukkit.kitpvp.kit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import us.forestbukkit.kitpvp.KitPvP;
import us.forestbukkit.kitpvp.user.User;
import us.forestbukkit.kitpvp.utils.CC;

import java.util.Locale;

public class Rewards {
    public static void giveDeathReward(Player player){
        User kUser = KitPvP.getInstance().getUserManager().getByUuid(player.getUniqueId());

        switch(kUser.getCurrentKitName().toLowerCase()){
            case "chemist":
                player.getInventory().addItem(new ItemStack(Material.POTION, 2, (short) 16428));
            default:
                return;
        }
    }
}
