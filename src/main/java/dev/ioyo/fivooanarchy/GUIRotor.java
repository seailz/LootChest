package dev.ioyo.fivooanarchy;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GUIRotor implements CommandExecutor, Listener {
    private FivooAnarchy plugin;

    public GUIRotor (FivooAnarchy plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return true;
        Player p = (Player) sender;
        if (!p.hasPermission("fivoo.*")) return true;
        if (args.length > 0) {
            if (!args[0].equals("add")) return true;
                plugin.possibleitems.add(p.getInventory().getItemInMainHand());
            plugin.getConfig().set("items", plugin.possibleitems);
            plugin.saveConfig();

                p.sendMessage("Item added!");



            return true;
        }


        ArrayList<ItemStack> abc = (ArrayList<ItemStack>) plugin.getConfig().get("items");
        abc.toArray();

        Inventory gui = Bukkit.createInventory(p, 54, "Loot Chest Items");
        for (ItemStack item : abc) {
            gui.addItem(item);
        }




        p.openInventory(gui);

        return true;
    }


    @EventHandler
    public void InventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(!e.getView().getTitle().equals("Loot Chest Items")) return;
        ItemStack i = e.getCurrentItem();
        e.getInventory().remove(i);
        plugin.possibleitems.remove(i);
        e.setCancelled(true);


    }

}
