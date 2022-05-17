package dev.sha256.stats.Event;

import dev.sha256.stats.Stats;
import dev.sha256.stats.user.StatsUser;
import dev.sha256.stats.user.Types;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

public class StatEvents implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        StatsUser user = Stats.USERS.get(player.getUniqueId());


        user.addValue(Types.BLOCK_BREAKING.getId(), 1);

        if(event.getBlock().getType() == Material.IRON_ORE
                || event.getBlock().getType() == Material.DIAMOND_ORE
                || event.getBlock().getType() == Material.EMERALD_ORE
                || event.getBlock().getType() == Material.COAL_ORE) {
            user.addValue(Types.OBTAINING_ORE.getId(), 1);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        StatsUser user = Stats.USERS.get(player.getUniqueId());

        user.addValue(Types.BLOCK_PLACE.getId(), 1);
    }


    @EventHandler
    public void onKill(EntityDeathEvent event) {
        Player player = event.getEntity().getKiller();

        StatsUser user = Stats.USERS.get(player.getUniqueId());

        if(event.getEntity() instanceof Player) {
            user.addValue(Types.PLAYERS_KILLED.getId(), 1);
            user.addValue(Types.AMOUNT_KILLED.getId(), 1);
        }

        if(event.getEntity() instanceof Zombie) {
            user.addValue(Types.KILLING_OFFENSIVE_CREATURE.getId(), 1);
            user.addValue(Types.AMOUNT_KILLED.getId(), 1);
        }

        if(event.getEntity() instanceof Pig) {
            user.addValue(Types.PASSIVE_MOB_KILLING.getId(), 1);
            user.addValue(Types.AMOUNT_KILLED.getId(), 1);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getView().getTitle().contains("Stats")) {
            event.setCancelled(true);
        }
    }

}
