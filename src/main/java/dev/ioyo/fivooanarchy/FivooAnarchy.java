package dev.ioyo.fivooanarchy;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public final class FivooAnarchy extends JavaPlugin {

    private LootChests ml = new LootChests(this);

    public LootChests getML() {
        return ml;
    }

    private togglepvp ml1 = new togglepvp(this);

    public togglepvp getML1() {
        return ml1;
    }

    private SpawnLootChest ml12 = new SpawnLootChest(this);

    public SpawnLootChest getML12() {
        return ml12;
    }


    @Override
    public void onEnable() {
        // Plugin startup logic
        JDABuilder builder = JDABuilder.createDefault("OTMxODcyMjczNzA1NjIzNTUy.YeKvrw.-rNZAHEdFVh4A4MxK2Ndw_5YqHY");

        // Disable parts of the cache
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        // Enable the bulk delete event
        builder.setBulkDeleteSplittingEnabled(false);
        // Disable compression (not recommended)
        builder.setCompression(Compression.NONE);
        // Set activity (like "playing Something")
        builder.setActivity(Activity.watching("Fivoo"));

        try {
            builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

        getCommand("toggleloot").setExecutor(new LootChests(this));
        getCommand("togglepvp").setExecutor(new togglepvp(this));
        getServer().getPluginManager().registerEvents(new togglepvp(this), this);
        System.out.println("hi");
        TeleportUtils teleport = new TeleportUtils(this);
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
