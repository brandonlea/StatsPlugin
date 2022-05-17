package dev.sha256.stats.user;

import dev.sha256.stats.Stats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class StatsGUI {

    private Inventory gui;
    private Player player;
    private StatsUser user;

    public StatsGUI(Player player) {
        this.player = player;

        this.user = Stats.USERS.get(player.getUniqueId());
        this.gui = Bukkit.createInventory(null, 54, "Stats - " + player.getDisplayName());

        this.gui.setItem(19, BlockBreaking());
        this.gui.setItem(20, BlockPlacing());
        this.gui.setItem(21, ObtainingOre());
        this.gui.setItem(22, PassiveMobKilling());
        this.gui.setItem(23, OffensiveMobKilling());
        this.gui.setItem(24, AmountKilled());
        this.gui.setItem(25, PlayersKilled());

        for(int i = 0; i < gui.getSize(); i++) {
            if(gui.getItem(i) == null) {
                gui.setItem(i, itemBuilder("&f====", new ArrayList<>(), Material.BLACK_STAINED_GLASS));
            }
        }
    }


    private ItemStack BlockBreaking() {
        List<String> lore = new ArrayList<>();
        lore.add("&6=========");
        lore.add("");
        lore.add("&cBlocks: &f" + user.getStat(Types.BLOCK_BREAKING.getId()));
        lore.add("");
        lore.add("&6==========");

        return itemBuilder("&cAmount of Blocks broken", lore, Material.STONE);
    }

    private ItemStack BlockPlacing() {
        List<String> lore = new ArrayList<>();
        lore.add("&6=========");
        lore.add("");
        lore.add("&cPlaced Blocks: &f" + user.getStat(Types.BLOCK_PLACE.getId()));
        lore.add("");
        lore.add("&6==========");

        return itemBuilder("&cAmount of Blocks placed", lore, Material.COBBLESTONE);
    }

    private ItemStack ObtainingOre() {
        List<String> lore = new ArrayList<>();
        lore.add("&6=========");
        lore.add("");
        lore.add("&cOre Blocks: &f" + user.getStat(Types.OBTAINING_ORE.getId()));
        lore.add("");
        lore.add("&6==========");

        return itemBuilder("&cAmount of Ores obtained", lore, Material.DIAMOND_ORE);
    }

    private ItemStack PassiveMobKilling() {
        List<String> lore = new ArrayList<>();
        lore.add("&6=========");
        lore.add("");
        lore.add("&cBlocks: &f" + user.getStat(Types.BLOCK_BREAKING.getId()));
        lore.add("");
        lore.add("&6==========");

        return itemBuilder("&cAmount of Blocks broken", lore, Material.STONE);
    }

    private ItemStack OffensiveMobKilling() {
        List<String> lore = new ArrayList<>();
        lore.add("&6=========");
        lore.add("");
        lore.add("&cKilled: &f" + user.getStat(Types.KILLING_OFFENSIVE_CREATURE.getId()));
        lore.add("");
        lore.add("&6==========");

        return itemBuilder("&cAmount of Offensive Mobs Killed", lore, Material.DIAMOND_SWORD);
    }

    private ItemStack PlayersKilled() {
        List<String> lore = new ArrayList<>();
        lore.add("&6=========");
        lore.add("");
        lore.add("&cKilled: &f" + user.getStat(Types.PLAYERS_KILLED.getId()));
        lore.add("");
        lore.add("&6==========");

        return itemBuilder("&cAmount of Players Killed", lore, Material.STONE_SWORD);
    }

    private ItemStack AmountKilled() {
        List<String> lore = new ArrayList<>();
        lore.add("&6=========");
        lore.add("");
        lore.add("&cKilled: &f" + user.getStat(Types.AMOUNT_KILLED.getId()));
        lore.add("");
        lore.add("&6==========");

        return itemBuilder("&cAmount of Kills", lore, Material.DIAMOND_AXE);
    }

    private ItemStack itemBuilder(String name, List<String> lore, Material material) {
        ItemStack item = new ItemStack(material);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        List<String> clore = new ArrayList<>();

        for(String l : lore) {
            clore.add(ChatColor.translateAlternateColorCodes('&', l));
        }

        meta.setLore(clore);

        item.setItemMeta(meta);

        return item;
    }


    public Inventory getGui() {
        return gui;
    }
}
