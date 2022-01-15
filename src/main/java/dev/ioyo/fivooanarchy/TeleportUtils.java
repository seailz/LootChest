package dev.ioyo.fivooanarchy;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Random;

public class TeleportUtils {

    //Get an instance of the main class so we can access config
    static FivooAnarchy plugin;

    public TeleportUtils(FivooAnarchy plugin) {
        this.plugin = plugin;
    }

    //List of all the unsafe blocks
    public static HashSet<Material> bad_blocks = new HashSet<>();

    static{
        bad_blocks.add(Material.LAVA);
        bad_blocks.add(Material.FIRE);
        bad_blocks.add(Material.CACTUS);
    }

    public static Location generateLocation(){
        //Generate Random Location
        Random random = new Random();

        int x = 0;
        int z = 0;
        int y = 0;

        if(plugin.getConfig().getBoolean("world-border")){ //If they want to limit the distance
            x = random.nextInt(plugin.getConfig().getInt("border"));
            z = random.nextInt(plugin.getConfig().getInt("border"));
            y = 150;
        }else if(!plugin.getConfig().getBoolean("world-border")){ //If they dont
            x = random.nextInt(5000); //25000 is default
            z = random.nextInt(5000);
            y = 150;
        }

        Location randomLocation = new Location(Bukkit.getWorld("world"), x, y, z);
        y = randomLocation.getWorld().getHighestBlockYAt(randomLocation);
        randomLocation.setY(y);

        return randomLocation;
    }

    public static Location findSafeLocation(){

        Location randomLocation = generateLocation();

        while (!isLocationSafe(randomLocation)){
            //Keep looking for a safe location
            randomLocation = generateLocation();
        }
        return randomLocation;
    }

    public static boolean isLocationSafe(Location location){

        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        //Get instances of the blocks around where the player would spawn
        Block block = location.getWorld().getBlockAt(x, y, z);
        Block below = location.getWorld().getBlockAt(x, y - 1, z);
        Block above = location.getWorld().getBlockAt(x, y + 1, z);

        //Check to see if the surroundings are safe or not
        return !(bad_blocks.contains(below.getType())) || (block.getType().isSolid()) || (above.getType().isSolid());
    }

}

