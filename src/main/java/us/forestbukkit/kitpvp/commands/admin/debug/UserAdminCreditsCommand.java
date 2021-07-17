package us.forestbukkit.kitpvp.commands.admin.debug;

import us.forestbukkit.kitpvp.KitPvP;
import us.forestbukkit.kitpvp.user.User;
import us.forestbukkit.kitpvp.utils.CC;
import us.forestbukkit.kitpvp.utils.command.BaseCommand;
import us.forestbukkit.kitpvp.utils.command.Command;
import us.forestbukkit.kitpvp.utils.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class UserAdminCreditsCommand extends BaseCommand {

    @Override @Command(name = "useradmin.setcredits")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length > 2) {
            player.sendMessage(CC.translate("&cUsage: /useradmin setcredits <player> <credits>"));
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(CC.translate("&cThere are no players named '" + args[0] + "' online."));
            return;
        }

        User targetUser = KitPvP.getInstance().getUserManager().getByUuid(target.getUniqueId());
        if (targetUser == null) {
            player.sendMessage(CC.translate("&c" + target.getName() + " doesn't have data stored."));
            return;
        }

        targetUser.setCredits(Integer.parseInt(args[1]));
        player.sendMessage(CC.translate("&aSuccessfully modified " + target.getName() + "'s credits amount to " + args[1] + "."));
    }
}
