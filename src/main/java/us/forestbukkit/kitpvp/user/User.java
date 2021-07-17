package us.forestbukkit.kitpvp.user;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import lombok.Getter;
import lombok.Setter;
import us.forestbukkit.kitpvp.KitPvP;
import us.forestbukkit.kitpvp.database.MongoSrv;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class User {

	private final UserManager userManager = KitPvP.getInstance().getUserManager();

	private final UUID uniqueId;

	private boolean loaded;

    private String currentKitName = "Default";
    private String lastKitName = "PvP";
    private List<String> unlockedKits = new ArrayList<>();

	private int credits;
	private int kills;
	private int deaths;
	private int currentKillstreak;
	private int highestKillstreak;

    public User(UUID uuid) {
        this.uniqueId = uuid;
        this.loaded = false;
        this.unlockedKits.add("Default");

        load();
    }

	public void load() {
		Document document = MongoSrv.getInstance().getUsers().find(Filters.eq("uniqueId", uniqueId.toString())).first();
		if (document != null) {
            this.currentKitName = document.getString("currentKitName");
			this.lastKitName = document.getString("lastKitName");
            this.unlockedKits = (List<String>) document.get("unlockedKits");

			this.kills = document.getInteger("kills");
			this.deaths = document.getInteger("deaths");
			this.credits = document.getInteger("credits");
			this.currentKillstreak = document.getInteger("currentKillstreak");
			this.highestKillstreak = document.getInteger("highestKillstreak");
		}

		this.loaded = true;
	}

	public void save() {
		Document document = new Document();
		document.put("uniqueId", this.uniqueId.toString());

        document.put("currentKitName", this.currentKitName);
        document.put("lastKitName", this.lastKitName);
        document.put("unlockedKits", this.unlockedKits);

		document.put("kills", this.kills);
		document.put("deaths", this.deaths);
		document.put("credits", this.credits);
		document.put("currentKillstreak", this.currentKillstreak);
		document.put("highestKillstreak", this.highestKillstreak);

		MongoSrv.getInstance().getUsers().replaceOne(Filters.eq("uniqueId", uniqueId.toString()), document, new UpdateOptions().upsert(true));
	}

	public void delete() {
		this.save();

		userManager.getUsers().remove(this.uniqueId);
	}
}
