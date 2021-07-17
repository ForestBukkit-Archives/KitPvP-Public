package us.forestbukkit.kitpvp.commands.user;

import us.forestbukkit.kitpvp.user.ui.kit.KitSelectionMenu;
import us.forestbukkit.kitpvp.utils.command.BaseCommand;
import us.forestbukkit.kitpvp.utils.command.Command;
import us.forestbukkit.kitpvp.utils.command.CommandArgs;

public class KitCommand extends BaseCommand {

    @Override @Command(name = "kit", aliases = {"kits"})
    public void onCommand(CommandArgs command) {
        new KitSelectionMenu().openMenu(command.getPlayer());
    }
}
