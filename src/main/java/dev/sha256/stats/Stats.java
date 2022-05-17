package dev.sha256.stats;

import dev.sha256.stats.Event.StatEvents;
import dev.sha256.stats.Event.UserEvents;
import dev.sha256.stats.command.StatsCommand;
import dev.sha256.stats.manager.ConfigManager;
import dev.sha256.stats.manager.DatabaseManager;
import dev.sha256.stats.user.StatsUser;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Stats extends JavaPlugin {

    private ConfigManager config;

    private DatabaseManager databaseManager;

    public static final HashMap<UUID, StatsUser> USERS = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        config = new ConfigManager("config", null, this);

        config.createCustomConfig();

        databaseManager = new DatabaseManager(this);

        Bukkit.getPluginManager().registerEvents(new UserEvents(this), this);
        Bukkit.getPluginManager().registerEvents(new StatEvents(), this);

        getCommand("stats").setExecutor(new StatsCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public ConfigManager getSettings() {
        return config;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
