package us.forestbukkit.kitpvp.commands.admin.debug;

import us.forestbukkit.kitpvp.KitPvP;
import us.forestbukkit.kitpvp.kit.Kit;
import us.forestbukkit.kitpvp.utils.CC;
import us.forestbukkit.kitpvp.utils.command.BaseCommand;
import us.forestbukkit.kitpvp.utils.command.Command;
import us.forestbukkit.kitpvp.utils.command.CommandArgs;
import org.bukkit.entity.Player;

public class KitAdminListCommand extends BaseCommand {

    private final KitPvP plugin = KitPvP.getInstance();

    @Override @Command(name = "kitadmin.list")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        player.sendMessage(CC.translate("&aTotal kits: " + KitPvP.getInstance().getKitManager().getKits().size()));
        for (Kit kits : this.plugin.getKitManager().getKits()) {
            player.sendMessage(CC.translate("&a * " + kits.getName()));
        }
    }
}
