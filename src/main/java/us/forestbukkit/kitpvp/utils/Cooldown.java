package us.forestbukkit.kitpvp.utils;

import lombok.Getter;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Cooldown {

    // lol i stole gamer's code gg!!

    @Getter
    private Map<UUID, Long> cooldownMap = new HashMap<>();

    public void applyCooldown(Player player, long cooldown) {
        cooldownMap.put(player.getUniqueId(), System.currentTimeMillis() + (cooldown * 1000));
    }

    public boolean onCooldown(Player player) {
        return cooldownMap.containsKey(player.getUniqueId()) && (cooldownMap.get(player.getUniqueId()) >= System.currentTimeMillis());
    }

    public String getRemaining(Player player) {
        long l = cooldownMap.get(player.getUniqueId()) - System.currentTimeMillis();
        if (l > TimeUnit.SECONDS.toMillis(60)) {
            return DurationFormatUtils.formatDuration(l, "m:ss");
        }
        String llll = DurationFormatUtils.formatDuration(l, "s.S");
        return llll.substring(0, llll.length() - 2);
    }

    public void removeCooldown(Player player) {
        cooldownMap.remove(player.getUniqueId());
    }
}
