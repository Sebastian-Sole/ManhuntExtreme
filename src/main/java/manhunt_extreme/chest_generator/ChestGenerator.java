package manhunt_extreme.chest_generator;

import manhunt_extreme.GameEngine;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class ChestGenerator {

    private GameEngine gameEngine;
    private Chest chest;
    private ChestPopulator chestPopulator;
    private Random random = new Random();

    public ChestGenerator(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.chestPopulator = new ChestPopulator();
        this.chestPopulator.setGameEngine(this.gameEngine);
    }

    private void createChest(World world, Location location) {
        Block block = world.getBlockAt(location);
        block.setType(Material.CHEST);
        this.chest = (Chest) block.getState();
    }


    public void generateChest(Location location, BlockBreakEvent event, World world) {
        event.setCancelled(true);
        createChest(world, location);
        var items = chestPopulator.populateChest("chest");
        addItemsToChest(items);
    }


    public void createSupplyDropChest(World world, Location location) {
        createChest(world, location);
        var items = chestPopulator.populateChest("supply");
        addItemsToChest(items);
    }

    private void addItemsToChest(ArrayList<ItemStack> items) {
        for (ItemStack stack : items) {
            boolean set = false;
            while (!set) {
                int slot = random.nextInt(27);
                if (chest.getInventory().getItem(slot) == null) {
                    chest.getInventory().setItem(slot, stack);
                    set = true;
                }
            }
        }
    }


}
