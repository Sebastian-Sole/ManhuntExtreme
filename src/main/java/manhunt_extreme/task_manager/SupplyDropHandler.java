package manhunt_extreme.task_manager;

import manhunt_extreme.GameEngine;
import manhunt_extreme.PluginMain;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;

public class SupplyDropHandler {
    private final GameEngine gameEngine;
    private PluginMain pluginMain;

    public SupplyDropHandler(PluginMain pluginMain, GameEngine gameEngine) {
        this.pluginMain = pluginMain;
        this.gameEngine = gameEngine;
    }

    public void start() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(pluginMain, () ->
                Bukkit.broadcastMessage("Supply Drop lands in 3 minutes, be ready for a chance at great loot!"), 26400L, 30000L
        );
        Bukkit.getScheduler().scheduleSyncRepeatingTask(pluginMain, this::spawnSupplyDrop, 30000L, 30000L
        ); // 30000L, 30000L
    }

    private void spawnSupplyDrop() {
        var hunterCoords = teamCoords(gameEngine.getHunters());
        var runnerCoords = teamCoords(gameEngine.getRunners());
        // Supply drops will always spawn in the runner's world
        World targetWorld = runnerCoords.getWorld();

        if (targetWorld.getEnvironment() == World.Environment.THE_END) {
            return;
        }
        // If hunters in overworld, and runners in nether
        if (hunterCoords.getWorld().getEnvironment() == World.Environment.NORMAL && runnerCoords.getWorld().getEnvironment() == World.Environment.NETHER) {
            ManhuntPlayer runnerInNether = gameEngine.getRunners().stream().filter(manhuntPlayer -> manhuntPlayer.getPlayer().getWorld().getEnvironment() == World.Environment.NETHER).toList().get(0);
            hunterCoords = gameEngine.getNetherPortals().get(runnerInNether);
        } else if (hunterCoords.getWorld().getEnvironment() == World.Environment.NETHER && runnerCoords.getWorld().getEnvironment() == World.Environment.NORMAL) {
            ManhuntPlayer runnerInOverworld = gameEngine.getRunners().stream().filter(manhuntPlayer -> manhuntPlayer.getPlayer().getWorld().getEnvironment() == World.Environment.NORMAL).toList().get(0);
            hunterCoords = gameEngine.getOverworldPortals().get(runnerInOverworld);
        }

        double middleX = Math.floor((hunterCoords.getX() + runnerCoords.getX()) / 2);
        double middleY = Math.floor((hunterCoords.getY() + runnerCoords.getY()) / 2);
        double middleZ = Math.floor((hunterCoords.getZ() + runnerCoords.getZ()) / 2);

        Location supplyDropLocation = new Location(targetWorld, middleX, middleY, middleZ);
        createSupplyDrop(supplyDropLocation, targetWorld);

    }

    private void createSupplyDrop(Location supplyDropLocation, World targetWorld) {
        generateSupplyDropBox(targetWorld, supplyDropLocation);
        createSupplyDropChest(targetWorld, supplyDropLocation);
        Bukkit.broadcastMessage("A supply drop has landed at: " + supplyDropLocation.getX() + ", " + supplyDropLocation.getY() + ", " + supplyDropLocation.getZ());
    }

    private void createSupplyDropChest(World targetWorld, Location supplyDropLocation) {
        Block block = targetWorld.getBlockAt(supplyDropLocation);
        block.setType(Material.CHEST);
        Chest chest = (Chest) block.getState();
        Inventory inventory = chest.getInventory();
        gameEngine.getChestGenerator().createSupplyDropChest(targetWorld, supplyDropLocation);
    }

    private void generateSupplyDropBox(World targetWorld, Location supplyDropLocation) {
        for (int i = -3; i <= 3; i++) {
            for (int j = -3; j <= 3; j++) {
                if (i == -3 || i == 3 || j == -3 || j == 3) {
                    setBlock(targetWorld, supplyDropLocation, 3, i, j);
                    setBlock(targetWorld, supplyDropLocation, -3, i, j);
                    setBlock(targetWorld, supplyDropLocation, j, i, 3);
                    setBlock(targetWorld, supplyDropLocation, j, i, -3);
                    setBlock(targetWorld, supplyDropLocation, j, 3, i);
                    setBlock(targetWorld, supplyDropLocation, j, -3, i);
                }
            }
        }
    }

    private void setBlock(World world, Location location, int x, int y, int z) {
        world.getBlockAt((int) (location.getX() + x), (int) (location.getY() + y), (int) (location.getZ() + z)).setType(Material.OBSIDIAN);
    }

    private Location teamCoords(ArrayList<ManhuntPlayer> players) {
        double teamX = 0;
        double teamY = 0;
        double teamZ = 0;
        ArrayList<World> teamWorlds = new ArrayList<>();

        for (ManhuntPlayer manhuntPlayer : players) {
            Player player = manhuntPlayer.getPlayer();
            teamX += player.getLocation().getX();
            teamY += player.getLocation().getY();
            teamZ += player.getLocation().getZ();
            teamWorlds.add(player.getWorld());
        }
        var world = mostFrequentWorld(teamWorlds);

        teamX = Math.floor(teamX / players.size());
        teamY = Math.floor(teamY / players.size());
        teamY = Math.floor(teamY / players.size());

        return new Location(world, teamX, teamY, teamZ);
    }

    /**
     * Finds the world which most of the team members are in. If tied, it should be a "special" world, (nether or end)
     *
     * @param teamWorlds
     * @return the world which the majority of team members are in.
     */
    private World mostFrequentWorld(ArrayList<World> teamWorlds) {
        HashMap<World, Integer> worldCount = new HashMap<>();
        for (World world : teamWorlds) {
            if (!worldCount.containsKey(world)) {
                worldCount.put(world, 1);
            } else {
                worldCount.put(world, worldCount.get(world) + 1);
            }
        }
        World currentWorld = gameEngine.getWorld();
        int currentCount = 0;
        for (var entry : worldCount.entrySet()) {
            // If the world count for the team's list is the same as the "current" world count
            if (entry.getValue() == currentCount) {
                /* If the entry's world is not normal (nether or end),
                 and the "current" world is, update it to the entry's world */
                if (currentWorld.getEnvironment() == World.Environment.NORMAL && entry.getKey().getEnvironment() != World.Environment.NORMAL) {
                    currentWorld = entry.getKey();
                }
            }
            // If the world count for the team's list is not the same as the "current" world count
            else {
                currentWorld = entry.getKey();
                currentCount = entry.getValue();
            }
        }
        return currentWorld;
    }


}
