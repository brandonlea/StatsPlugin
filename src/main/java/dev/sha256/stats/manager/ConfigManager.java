package dev.sha256.stats.manager;

import dev.sha256.stats.Stats;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private final String name, folder;
    private final Stats stats;

    private File file;
    private YamlConfiguration configuration;

    public ConfigManager(String name, String folder, Stats stats) {
        this.name = name;
        this.folder = folder;
        this.stats = stats;
    }

    public void createCustomConfig() {
        String path = "";

        if(folder != null) {
            path = folder + File.separator;
        }


        file = new File(stats.getDataFolder(), path + name + ".yml");

        if(!file.exists()) {
            file.getParentFile().mkdirs();
            stats.saveResource(path + name + ".yml", false);
        }

        configuration = new YamlConfiguration();

        try {
            configuration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public YamlConfiguration getConfiguration() {
        return configuration;
    }
}
