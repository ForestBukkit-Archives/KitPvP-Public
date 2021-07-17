package us.forestbukkit.kitpvp.commands.admin.debug;

import org.bukkit.entity.Player;
import us.forestbukkit.kitpvp.KitPvP;
import us.forestbukkit.kitpvp.utils.CC;
import us.forestbukkit.kitpvp.utils.command.BaseCommand;
import us.forestbukkit.kitpvp.utils.command.Command;
import us.forestbukkit.kitpvp.utils.command.CommandArgs;

public class FreeKitModeCommand extends BaseCommand {

    private final KitPvP plugin = KitPvP.getInstance();

    @Override @Command(name = "freekitadmin")
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();
        KitPvP.toggleKitMode();
    }
}
