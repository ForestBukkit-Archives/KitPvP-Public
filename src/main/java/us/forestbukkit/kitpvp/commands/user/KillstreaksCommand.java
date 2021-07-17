package us.forestbukkit.kitpvp.commands.user;

import us.forestbukkit.kitpvp.KitPvP;
import us.forestbukkit.kitpvp.utils.command.BaseCommand;
import us.forestbukkit.kitpvp.utils.command.Command;
import us.forestbukkit.kitpvp.utils.command.CommandArgs;
import org.bukkit.entity.Player;

public class KillstreaksCommand extends BaseCommand {

    private final KitPvP plugin = KitPvP.getInstance();

    @Override @Command(name = "killstreak", aliases = {"ks"})
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        // Open Killstreaks menu to player
    }
}
