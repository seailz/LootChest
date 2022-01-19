package dev.ioyo.fivooanarchy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class SpawnLootChest implements CommandExecutor, Listener {

    ItemStack item;
    private FivooAnarchy plugin;
    boolean found;
    Location location;
    TextChannel channel;

    public SpawnLootChest (FivooAnarchy plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        plugin.currentitem = null;

        if (!plugin.getConfig().getBoolean("LootChestsEnabled")) return true;

        if (!sender.hasPermission("fivoo.spawn")) return true;

        if (plugin.currentbocklocation != null) {
            plugin.currentbocklocation.getBlock().setType(Material.AIR);
        }
        Location randomLocation = TeleportUtils.findSafeLocation();
        Block b = randomLocation.getBlock();

        Player p = Bukkit.getPlayerExact("Seailz");
        Player p2 = Bukkit.getPlayerExact("Fivoo_");
        TextChannel tx = plugin.jda.getTextChannelById(931915784211558442L);
        new Embed("Loot Chest Spawned", Color.RED, "A new loot chest has been spawned at these coords! Go collect it to recive some great loot :) \n X: " + randomLocation.getX() + "\n Y: " + randomLocation.getY() + "\n Z: " + randomLocation.getZ(), "Developed by Seailz - https://www.youtube.com/seailz", "https://i.imgur.com/YGWwqFy.png", tx);

        for (Player online : Bukkit.getOnlinePlayers()) {
            online.sendTitle(ChatColor.WHITE + "" + ChatColor.BOLD + "A LOOT CHEST HAS BEEN" + ChatColor.GREEN + ChatColor.BOLD + " SPAWNED!", "COORDS: x: " + randomLocation.getX() + " y: " + randomLocation.getY() + " z: " + randomLocation.getZ() , 3, 80, 3);
        }
        found = false;
        plugin.found = false;
        channel = tx;
        plugin.currentbocklocation = randomLocation;
        plugin.textchannel = tx;

        location = randomLocation;
        Block block = randomLocation.getBlock();
        randomLocation.getBlock().setType(Material.CHEST);
        Chest chest = (Chest)block.getState();
        Inventory inv = chest.getInventory();


        if (plugin.currentitem == null) {

            // loop to print elements at randonm
            for (int i = 0; i < plugin.possibleitems.size(); i++) {
                // generating the index using Math.random()
                int index = (int) (Math.random() * plugin.possibleitems.size());
                item = plugin.possibleitems.get(index);
                break;
            }
            inv.addItem(item);
        }
        else {
            item = plugin.currentitem;
            inv.addItem(item);

            item.getItemMeta().setLore(null);
        }



        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                chest.setType(Material.AIR);
                for (Player online : Bukkit.getOnlinePlayers()) {
                    if (found = false) return;
                    online.sendMessage("The loot chest has been removed because no-one found it.");
                }
            }
        }, 144000L);



        return true;
    }


}
