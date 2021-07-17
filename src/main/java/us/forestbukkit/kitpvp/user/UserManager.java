package us.forestbukkit.kitpvp.user;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManager {

	@Getter private final Map<UUID, User> users = new HashMap<>();

	public User getOrCreate(UUID uuid) {
		return users.computeIfAbsent(uuid, User::new);
	}

	public User getByUuid(UUID uuid) {
        return users.getOrDefault(uuid,new User(uuid));
	}
}
