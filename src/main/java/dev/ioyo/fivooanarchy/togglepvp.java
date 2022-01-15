package dev.ioyo.fivooanarchy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.jetbrains.annotations.NotNull;

public class togglepvp implements CommandExecutor, Listener {

    private FivooAnarchy plugin;

    public togglepvp (FivooAnarchy plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("fivoo.togglepvp")) {
            if (plugin.getConfig().getBoolean("PVP")) {
                sender.sendMessage(ChatColor.WHITE + "" + ChatColor.BOLD + "PVP HAS BEEN" + ChatColor.RED + ChatColor.BOLD + " DISABLED.");
                plugin.getConfig().set("PVP", false);

                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendTitle(ChatColor.WHITE + "" + ChatColor.BOLD + "PVP HAS BEEN" + ChatColor.RED + ChatColor.BOLD + " DISABLED.", "YOU CAN NO LONGER FIGHT PLAYERS", 3, 40, 3);
                }

            }
            else {
                sender.sendMessage(ChatColor.WHITE + "" + ChatColor.BOLD + "PVP HAS BEEN" + ChatColor.GREEN + ChatColor.BOLD + " ENABLED.");
                plugin.getConfig().set("PVP", true);

                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendTitle(ChatColor.WHITE + "" + ChatColor.BOLD + "PVP HAS BEEN" + ChatColor.GREEN + ChatColor.BOLD + " ENABLED.", "YOU CAN NOW FIGHT PLAYERS", 3, 40, 3);
                }

            }
        }


        return true;
    }

    @EventHandler
    public void onPVP(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        if (plugin.getConfig().getBoolean("PVP")) return;
        e.setCancelled(true);

    }

}
