package dev.ioyo.fivooanarchy;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;

public final class FivooAnarchy extends JavaPlugin {


    ArrayList<ItemStack> possibleitems = new ArrayList<>();
    ArrayList<ItemStack> possibleitemsconfig = new ArrayList<>();
    boolean found;
    ItemStack currentitem;
    Location currentbocklocation;
    TextChannel textchannel;


    private LootChests ml = new LootChests(this);

    public LootChests getML() {
        return ml;
    }

    private whitelist ml68 = new whitelist(this);

    public whitelist getML68() {
        return ml68;
    }

    private ChestOpen ml3 = new ChestOpen(this);

    public ChestOpen getML3() {
        return ml3;
    }

    private togglepvp ml1 = new togglepvp(this);

    public togglepvp getML1() {
        return ml1;
    }

    private SpawnLootChest ml12 = new SpawnLootChest(this);

    public SpawnLootChest getML12() {
        return ml12;
    }

    private GUIRotor ml112 = new GUIRotor(this);

    public GUIRotor getML112() {
        return ml112;
    }



    JDA jda;

    @Override
    public void onEnable() {
        // Plugin startup logic

        possibleitems.add(new ItemStack(Material.DIAMOND_HELMET));
        getConfig().set("items", possibleitems);
        ArrayList<ItemStack> item1 = (ArrayList<ItemStack>) getConfig().get("items");
        item1.toArray(new ItemStack[0]);
        item1.add(new ItemStack(Material.DIAMOND_SWORD));
        possibleitems = item1;


        JDABuilder builder = JDABuilder.createDefault("no");
        try {
            jda = builder.
             addEventListeners(new whitelist(this))
        .build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
        // Disable parts of the cache
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        // Enable the bulk delete event
        builder.setBulkDeleteSplittingEnabled(false);
        // Disable compression (not recommended)
        builder.setCompression(Compression.NONE);
        // Set activity (like "playing Something")
        builder.setActivity(Activity.watching("Fivoo"));

        getCommand("toggleloot").setExecutor(new LootChests(this));
        getCommand("togglepvp").setExecutor(new togglepvp(this));
        getCommand("spawnloot").setExecutor(new SpawnLootChest(this));
        getCommand("gui").setExecutor(new GUIRotor(this));
        getServer().getPluginManager().registerEvents(new ChestOpen(this), this);
        getServer().getPluginManager().registerEvents(new togglepvp(this), this);
        getServer().getPluginManager().registerEvents(new SpawnLootChest(this), this);
        getServer().getPluginManager().registerEvents(new GUIRotor(this), this);
        System.out.println("hi");
        TeleportUtils teleport = new TeleportUtils(this);
        this.saveDefaultConfig();

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("fivoo.*")) return true;

        if (command.getName().equals("freload")) {
            reloadConfig();
            sender.sendMessage("Reloaded!");
        }
        else {
            if (command.getName().equals("setitem")) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    if (p.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                        p.sendMessage("You don't have any items in your hand!");
                        return true;
                    }
                    ItemStack item = p.getInventory().getItemInMainHand();
                    currentitem = item;
                    p.sendMessage("Item has been set.");

                }
            }
        }



        return true;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getConfig().set("items", possibleitems);
        saveConfig();
    }
}
