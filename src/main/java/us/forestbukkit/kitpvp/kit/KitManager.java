package us.forestbukkit.kitpvp.kit;

import lombok.Getter;
import us.forestbukkit.kitpvp.kit.impl.*;

import java.util.ArrayList;
import java.util.List;

@Getter
public class KitManager {

    private final List<Kit> kits = new ArrayList<>();

    public KitManager() {
        kits.add(new PvP());
        kits.add(new Archer());
        kits.add(new Pro());
        kits.add(new Chemist());
        kits.add(new Sonic());
        kits.add(new Fireman());
        kits.add(new Astronaut());
    }

    public Kit getKitByName(String name) {
        for (Kit kit : kits) {
            if (name.equals(kit.getName())) {
                return kit;
            }
        }

        return null;
    }
}
