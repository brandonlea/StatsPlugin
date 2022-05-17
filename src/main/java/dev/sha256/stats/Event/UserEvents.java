package dev.sha256.stats.Event;

import dev.sha256.stats.Stats;
import dev.sha256.stats.user.StatsUser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class UserEvents implements Listener {


    private Stats stats;

    public UserEvents(Stats stats) {
        this.stats = stats;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        StatsUser user = new StatsUser(player);



        stats.getDatabaseManager().loadStats(user);

        Stats.USERS.put(user.getUUID(), user);

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        StatsUser user = Stats.USERS.get(player.getUniqueId());

        stats.getDatabaseManager().saveUser(user);

        Stats.USERS.remove(player.getUniqueId());
    }

}
