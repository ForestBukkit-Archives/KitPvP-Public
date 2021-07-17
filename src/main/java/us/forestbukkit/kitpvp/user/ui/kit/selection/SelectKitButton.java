package us.forestbukkit.kitpvp.user.ui.kit.selection;

import lombok.AllArgsConstructor;
import us.forestbukkit.kitpvp.KitPvP;
import us.forestbukkit.kitpvp.kit.Kit;
import us.forestbukkit.kitpvp.user.User;
import us.forestbukkit.kitpvp.utils.CC;
import us.forestbukkit.kitpvp.utils.ItemBuilder;
import us.forestbukkit.kitpvp.utils.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class SelectKitButton extends Button {

    private final Kit kit;
    private final User user;

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lore = new ArrayList<>();

        lore.add(CC.MENU_BAR);
        lore.addAll(Arrays.asList(kit.getDesc()));
        lore.add(CC.MENU_BAR);
        if(kit.getPrice() != 0){
            if(!KitPvP.freekitmode){
        if (!user.getUnlockedKits().contains(kit.getName())) {
            lore.add(" ");
            lore.add("&ePrice: " + kit.getPrice() + "&e.");
            lore.add("&aClick here to purchase.");
        }} else {lore.add(" "); lore.add("&7Kits are free during &a&lFree Kit Mode!");}}

        return new ItemBuilder(kit.getIcon()).name(CC.translate("&a" + kit.getName())).lore(CC.translate(lore)).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if(kit.getPrice() != 0){
            if(!KitPvP.freekitmode){
        if (!user.getUnlockedKits().contains(kit.getName())) {
            if (user.getCredits() < kit.getPrice()) {
                player.sendMessage(CC.translate("&cNot enough credits!"));
            } else {
                user.getUnlockedKits().add(kit.getName());
                user.setCredits(user.getCredits() - kit.getPrice());
                player.sendMessage(CC.translate("&eYou have purchased the &d" + kit.getName() + "&e kit."));
            }

            return;
        }}}

        playSuccess(player);
        player.closeInventory();
        user.setCurrentKitName(kit.getName());
        KitPvP.getInstance().getKitManager().getKitByName(kit.getName()).equipKit(player);
    }
}
