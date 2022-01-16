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
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import javax.xml.soap.Text;
import java.lang.reflect.Array;
import java.util.ArrayList;

public final class FivooAnarchy extends JavaPlugin {

    boolean found;
    ItemStack currentitem;
    Location currentbocklocation;
    TextChannel textchannel;
    TextChannel serverstatus;


    private LootChests ml = new LootChests(this);

    public LootChests getML() {
        return ml;
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


    JDA jda;

    @Override
    public void onEnable() {
        // Plugin startup logic
        JDABuilder builder = JDABuilder.createDefault("");
        try {
            jda = builder.build();
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
        getServer().getPluginManager().registerEvents(new ChestOpen(this), this);
        getServer().getPluginManager().registerEvents(new togglepvp(this), this);
        getServer().getPluginManager().registerEvents(new SpawnLootChest(this), this);
        System.out.println("hi");
        TeleportUtils teleport = new TeleportUtils(this);
        this.saveDefaultConfig();


        ArrayList<ItemStack> possibleitems = new ArrayList<>();

        ItemStack leggings = new ItemStack(Material.DIAMOND_BOOTS);
        leggings.addEnchantment(Enchantment.PROTECTION_FALL, 3);

        ItemStack leggings1 = new ItemStack(Material.DIAMOND_CHESTPLATE);
        leggings1.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);

        ItemStack leggings2 = new ItemStack(Material.DIAMOND_SWORD);
        leggings2.addEnchantment(Enchantment.FIRE_ASPECT, 1);

        ItemStack leggings3 = new ItemStack(Material.DIAMOND_HELMET);
        leggings3.addEnchantment(Enchantment.WATER_WORKER, 1);

        possibleitems.add(leggings);
        possibleitems.add(leggings1);
        possibleitems.add(leggings2);
        possibleitems.add(leggings3);

        // loop to print elements at randonm
        for (int i = 0; i < possibleitems.size(); i++) {
            // generating the index using Math.random()
            int index = (int) (Math.random() * possibleitems.size());
            currentitem = possibleitems.get(index);
            break;



        }


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

    }
}
