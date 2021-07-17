package us.forestbukkit.kitpvp.commands.user;

import us.forestbukkit.kitpvp.KitPvP;
import us.forestbukkit.kitpvp.user.User;
import us.forestbukkit.kitpvp.utils.command.BaseCommand;
import us.forestbukkit.kitpvp.utils.command.Command;
import us.forestbukkit.kitpvp.utils.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import us.forestbukkit.kitpvp.utils.CC;

import static us.forestbukkit.kitpvp.utils.CC.translate;

public class PayCommand extends BaseCommand {

    private final KitPvP plugin = KitPvP.getInstance();

    @Override @Command(name = "pay", aliases = {"p2p"})
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length < 2) {
            player.sendMessage(CC.translate("&cUsage: /pay <player> <amount>"));
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

        User user = this.plugin.getUserManager().getByUuid(player.getUniqueId());

        user.setCredits(user.getCredits() - Integer.parseInt(args[1]));
        targetUser.setCredits(targetUser.getCredits() + Integer.parseInt(args[1]));

        String credits = (Integer.parseInt(args[1]) > 1 ? "credits" : "credit");
        player.sendMessage(CC.translate("&eYou sent &d" + args[1] + "&e " + credits + " to &d" + target.getName() + "&e."));
        target.sendMessage(CC.translate("&d" + player.getName() + "&e sent you &d" + args[1] + "&e " + credits + "."));
    }
}
