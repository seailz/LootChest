package dev.ioyo.fivooanarchy;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnLootChest implements CommandExecutor {

    private FivooAnarchy plugin;

    public SpawnLootChest (FivooAnarchy plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!plugin.getConfig().getBoolean("LootChestsEnabled")) return;

        Location randomLocation = TeleportUtils.findSafeLocation();
        Block b = randomLocation.getBlock();


        return true;
    }
}
