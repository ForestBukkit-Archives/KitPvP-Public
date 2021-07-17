package us.forestbukkit.kitpvp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SignListener implements Listener {
    @EventHandler
    public void onSignCreate(SignChangeEvent e) {
        if (e.getLine(0).equals("[Soup]")) {
            e.setLine(0, ChatColor.AQUA + "[Soup]");
            e.setLine(1, "Right click");
            e.setLine(2, "to get soup!");
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock() != null) {
            Player player = event.getPlayer();
            Block block = event.getClickedBlock();
            if (block.getType() == Material.SIGN || block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN) {
                Sign sign = (Sign) block.getState();
                if (sign.getLine(0).equalsIgnoreCase(ChatColor.AQUA + "[Soup]")) {
                    Inventory soupventory = Bukkit.createInventory(null, 36, "Free Soup");
                    final ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
                    for (ItemStack inv : soupventory.getContents()) {
                        if (inv == null) {
                            soupventory.addItem(soup);
                        }
                    }
                    player.openInventory(soupventory);
                }
            }
        }
    }
}
