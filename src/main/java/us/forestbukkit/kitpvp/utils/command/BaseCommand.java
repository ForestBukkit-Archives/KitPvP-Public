package us.forestbukkit.kitpvp.utils.command;

import us.forestbukkit.kitpvp.KitPvP;

public abstract class BaseCommand {

	public BaseCommand() {
		KitPvP.getInstance().getCommandFramework().registerCommands(this, null);
	}

	public abstract void onCommand(CommandArgs command);
}
