package manhunt_extreme.task_manager;

import manhunt_extreme.PluginMain;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;

import java.util.Random;

public class BlazeSpawnerHandler {
    private Random random = new Random();
    private PluginMain pluginMain;
    private int spawnersGenerated;

    public BlazeSpawnerHandler(PluginMain pluginMain) {
        this.pluginMain = pluginMain;
    }


    public void start() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(pluginMain, () -> {
            if (spawnersGenerated < 5) {
                generateSpawner();
                spawnersGenerated++;
            }
        }, 8400L, 3600L); // 8400L, 3600L
    }

    private void generateSpawner() {
        var nether = Bukkit.getWorld("world_nether");
        var x = generateCoordinate();
        var y = random.nextDouble(54.0) + 36;
        var z = generateCoordinate();
        var firstBlockLocation = new Location(nether, x, y, z);
        var block = nether.getBlockAt(firstBlockLocation);
        var spawnerBlock = nether.getBlockAt(firstBlockLocation.getBlockX(), firstBlockLocation.getBlockY() + 1, firstBlockLocation.getBlockZ());
        spawnerBlock.setType(Material.SPAWNER);
        var blockState = (CreatureSpawner) spawnerBlock.getState();
        blockState.setSpawnedType(EntityType.BLAZE);
        generatePlatform(block, nether);
        Bukkit.broadcastMessage("Blaze spawner generated at: " + (int) x + ", " + (int) y + ", " + (int) z);
    }

    private void generatePlatform(Block block, World nether) {
        var spawnBlockLocation = block.getLocation();
        var locX = (int) spawnBlockLocation.getX();
        var locY = (int) spawnBlockLocation.getY();
        var locZ = (int) spawnBlockLocation.getZ();

        // Make 4 quadrants around the block
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                nether.getBlockAt(locX + i, locY, locZ + j).setType(Material.OBSIDIAN);
            }
        }

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                nether.getBlockAt(locX - i, locY, locZ + j).setType(Material.OBSIDIAN);
            }
        }

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                nether.getBlockAt(locX - i, locY, locZ - j).setType(Material.OBSIDIAN);
            }
        }
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                nether.getBlockAt(locX + i, locY, locZ - j).setType(Material.OBSIDIAN);
            }
        }

    }


    private double generateCoordinate() {
        var axis = 1;
        if (Math.random() < 0.5) {
            axis = -1;
        }
        return random.nextDouble(300.0) * axis;
    }


}
