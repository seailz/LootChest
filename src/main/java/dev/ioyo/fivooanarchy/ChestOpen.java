package dev.ioyo.fivooanarchy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ChestOpen implements Listener {

    private FivooAnarchy plugin;

    public ChestOpen (FivooAnarchy plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void ChestOpen(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null) return;
        if (!e.getClickedBlock().getType().equals(Material.CHEST)) return;
        if (!e.getClickedBlock().getLocation().equals(plugin.currentbocklocation)) return;
        if (e.getPlayer().hasPermission("fivoo.bypasslootchest")) return;
        if (plugin.found) {
            e.getPlayer().sendMessage("This loot chest has been found already, hopefully they left something for you!");
            return;
        }
        Player p = e.getPlayer();
        Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + p.getName() + ChatColor.WHITE + "" + ChatColor.BOLD + " FOUND THE LOOT CHEST!");
        plugin.textchannel.sendMessage(p.getName() + " found the loot chest!").queue();
        plugin.found = true;

    }
}
