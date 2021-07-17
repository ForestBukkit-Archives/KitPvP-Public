package us.forestbukkit.kitpvp.kit;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class Ability {

	public abstract String getAbilityName();

	public abstract String[] getAbilityDescription();

	public abstract ItemStack getAbilityItem();

	public abstract long getAbilityCooldown();

	public abstract AbilityCallable getAbilityCallable();

	public interface AbilityCallable {
		void execute(Player player);
	}
}
