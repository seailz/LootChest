package dev.ioyo.fivooanarchy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class LootChests implements CommandExecutor {

    private FivooAnarchy plugin;

    public LootChests (FivooAnarchy plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("fivoo.*")) {
            if (plugin.getConfig().getBoolean("LootChestsEnabled")) {
                sender.sendMessage(ChatColor.WHITE + "" + ChatColor.BOLD + "LOOT CHESTS HAVE BEEN" + ChatColor.RED + ChatColor.BOLD + " DISABLED.");
                plugin.getConfig().set("LootChestsEnabled", false);
            }
            else {
                sender.sendMessage(ChatColor.WHITE + "" + ChatColor.BOLD + "LOOT CHESTS HAVE BEEN" + ChatColor.GREEN + ChatColor.BOLD + " ENABLED.");
                plugin.getConfig().set("LootChestsEnabled", true);


            }
        }


        return true;
    }
}
