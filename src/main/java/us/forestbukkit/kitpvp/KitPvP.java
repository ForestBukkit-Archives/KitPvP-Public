package us.forestbukkit.kitpvp;

import com.google.common.base.Stopwatch;
import lombok.Getter;
import org.bukkit.Location;
import us.forestbukkit.kitpvp.controller.ClassRegistrationController;
import us.forestbukkit.kitpvp.database.MongoSrv;
import us.forestbukkit.kitpvp.kit.KitManager;
import us.forestbukkit.kitpvp.layout.ServerScoreboard;
import us.forestbukkit.kitpvp.listeners.*;
import us.forestbukkit.kitpvp.listeners.kitlisteners.ArcherKitListener;
import us.forestbukkit.kitpvp.user.User;
import us.forestbukkit.kitpvp.user.UserManager;
import us.forestbukkit.kitpvp.utils.CC;
import us.forestbukkit.kitpvp.utils.Cooldown;
import us.forestbukkit.kitpvp.utils.command.CommandFramework;
import us.forestbukkit.kitpvp.utils.scoreboard.BoardManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

@Getter
public final class KitPvP extends JavaPlugin {

	@Getter private static KitPvP instance;
	private final CommandFramework commandFramework = new CommandFramework(this);
	private final ClassRegistrationController crc = new ClassRegistrationController();

	public static Cooldown pearlcooldown = new Cooldown();
	public static Cooldown pvptag = new Cooldown();

	private Location safezonemax = new Location(Bukkit.getWorld("world"), 0, 56, 0);
	private Location safezonemin = new Location(Bukkit.getWorld("world"), 0, 56, 0);

	private KitManager kitManager;
	private UserManager userManager;
	public static boolean freekitmode = false;

	@Override
	public void onEnable() {
		instance = this;
		this.saveDefaultConfig();
		this.saveRunnable();

		new MongoSrv();

		this.loadManagers();
		registerListeners();
		registerKitListeners();
		crc.loadCommands("us.forestbukkit.kitpvp.commands");

		new BoardManager(new ServerScoreboard(), 1);
	}
	public static void toggleKitMode(){
		if (!freekitmode){
			freekitmode = true;
			CC.broadcast(CC.translate("&dFree Kit Mode &7has been &aactivated!"));
			return;
		} else {
			freekitmode = false;
			CC.broadcast(CC.translate("&dFree Kit Mode &7has been &cdeactivated!"));
			return;
		}
	}

	private void registerListeners(){
	    Bukkit.getPluginManager().registerEvents(new ButtonListener(), this);
        Bukkit.getPluginManager().registerEvents(new GameListener(), this);
        Bukkit.getPluginManager().registerEvents(new ServerListener(), this);
        Bukkit.getPluginManager().registerEvents(new UserListener(), this);
        Bukkit.getPluginManager().registerEvents(new BowListener(), this);
        Bukkit.getPluginManager().registerEvents(new SafezoneListener(), this);
        Bukkit.getPluginManager().registerEvents(new SignListener(), this);
        Bukkit.getPluginManager().registerEvents(new TagListener(), this);
    }

    private void registerKitListeners(){
		Bukkit.getPluginManager().registerEvents(new ArcherKitListener(), this);
	}

	public void saveRunnable(){
        new BukkitRunnable() {

            int i = 0;

            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()) {
					Stopwatch stopwatch = new Stopwatch();
					stopwatch.start();
                    User user = KitPvP.getInstance().getUserManager().getOrCreate(p.getUniqueId());
                    user.save();
                    stopwatch.stop();
                    if(p.hasPermission("kitpvp.stats")){
                        p.sendMessage(CC.translate("&5[&d" + getInstance().getName() + "&5] &r&eSaved &d" + Bukkit.getOnlinePlayers().size() + " &estats to mongo database in &d" + stopwatch.elapsedMillis() + "ms&e!"));
                    }
                }
            }

        }.runTaskTimer(this, 60 * 20L, 5 * 60 * 20L);
    }

	@Override
	public void onDisable() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            User user = this.getUserManager().getOrCreate(p.getUniqueId());
            user.save();
        }
	}

	private void loadManagers() {
		this.kitManager = new KitManager();
		this.userManager = new UserManager();
	}
}
