package us.forestbukkit.kitpvp.commands.admin.debug;

import us.forestbukkit.kitpvp.KitPvP;
import us.forestbukkit.kitpvp.utils.CC;
import us.forestbukkit.kitpvp.utils.command.BaseCommand;
import us.forestbukkit.kitpvp.utils.command.Command;
import us.forestbukkit.kitpvp.utils.command.CommandArgs;
import org.bukkit.entity.Player;

public class KitAdminGetCommand extends BaseCommand {

    private final KitPvP plugin = KitPvP.getInstance();

    @Override @Command(name = "kitadmin.get")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length == 0) {
            player.sendMessage(CC.translate("&cUsage: /kitadmin get <kitName>"));
            return;
        }

        if (this.plugin.getKitManager().getKitByName(args[0]) != null) {
            this.plugin.getKitManager().getKitByName(args[0]).equipKit(player);
        } else {
            player.sendMessage(CC.translate("&cThis kit doesnt exist"));
        }
    }
}
