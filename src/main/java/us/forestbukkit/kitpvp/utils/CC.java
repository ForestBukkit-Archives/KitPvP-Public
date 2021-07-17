package us.forestbukkit.kitpvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CC {

    public final static String MENU_BAR = ChatColor.DARK_GRAY.toString() + ChatColor.STRIKETHROUGH.toString() + "------------------------";

	public static String translate(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public static List<String> translate(List<String> lines) {
		List<String> strings = new ArrayList<>();
		for (String line : lines) {
			strings.add(ChatColor.translateAlternateColorCodes('&', line));
		}

		return strings;
	}

	public static List<String> translate(String[] lines) {
		List<String> strings = new ArrayList<>();
		for (String line : lines) {
			if (line != null) {
				strings.add(ChatColor.translateAlternateColorCodes('&', line));
			}
		}

		return strings;
	}

	public static void broadcast(String string){
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(string);
        }
    }
}
