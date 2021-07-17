package us.forestbukkit.kitpvp.commands.user;

import us.forestbukkit.kitpvp.KitPvP;
import us.forestbukkit.kitpvp.user.User;
import us.forestbukkit.kitpvp.utils.CC;
import us.forestbukkit.kitpvp.utils.command.BaseCommand;
import us.forestbukkit.kitpvp.utils.command.Command;
import us.forestbukkit.kitpvp.utils.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BalanceCommand extends BaseCommand {

    private final KitPvP plugin = KitPvP.getInstance();

    @Override @Command(name = "balance", aliases = {"bal", "credits", "creds"})
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length == 0) {
            player.sendMessage(CC.translate("&eBalance: &a" + this.plugin.getUserManager().getByUuid(player.getUniqueId()).getCredits()));
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

        player.sendMessage(CC.translate("&e" + target.getName() + "'s Balance: &a" + targetUser.getCredits()));
    }
}
