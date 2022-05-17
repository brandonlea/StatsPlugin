package dev.sha256.stats.user;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class StatsUser {

    private final Player player;
    private final HashMap<String, Integer> stats;

    public StatsUser(Player player) {
        this.player = player;

        stats = new HashMap<>();
    }

    public void addValue(String name, int value) {
        this.stats.put(name, this.stats.get(name) + value);
    }

    public void addStat(String name, int value) {
        this.stats.put(name, value);
    }

    public Integer getStat(String name) {
        return this.stats.get(name);
    }

    public UUID getUUID() {
        return player.getUniqueId();
    }
}
