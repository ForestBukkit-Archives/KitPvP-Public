package us.forestbukkit.kitpvp.user.ui.kit;

import org.bukkit.ChatColor;
import us.forestbukkit.kitpvp.KitPvP;
import us.forestbukkit.kitpvp.kit.Kit;
import us.forestbukkit.kitpvp.user.User;
import us.forestbukkit.kitpvp.user.ui.kit.selection.SelectKitButton;
import us.forestbukkit.kitpvp.utils.menu.Button;
import us.forestbukkit.kitpvp.utils.menu.Menu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class KitSelectionMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return ChatColor.LIGHT_PURPLE + "Choose Your Kit";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        User user = KitPvP.getInstance().getUserManager().getByUuid(player.getUniqueId());

        for (Kit kit : KitPvP.getInstance().getKitManager().getKits()) {
            buttons.put(buttons.size(), new SelectKitButton(kit, user));
        }
        
        return buttons;
    }
}
