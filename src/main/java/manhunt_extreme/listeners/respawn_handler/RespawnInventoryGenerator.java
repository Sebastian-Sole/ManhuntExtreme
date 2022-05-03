package manhunt_extreme.listeners.respawn_handler;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class RespawnInventoryGenerator {

    // 0-10 minutes
    private ArrayList<ItemStack> respawnOne = new ArrayList<>(Arrays.asList(
            new ItemStack(Material.OAK_LOG, 4),
            new ItemStack(Material.COBBLESTONE, 6),
            new ItemStack(Material.BREAD, 8),
            new ItemStack(Material.TORCH, 5)
    ));
    // 10-20 minutes
    private ArrayList<ItemStack> respawnTwo = new ArrayList<>(Arrays.asList(
            new ItemStack(Material.BREAD, 12),
            new ItemStack(Material.TORCH, 5),
            new ItemStack(Material.OAK_LOG, 7),
            new ItemStack(Material.COBBLESTONE, 9),
            new ItemStack(Material.IRON_INGOT, 7)
    ));
    // 20-30 minutes
    private ArrayList<ItemStack> respawnThree = new ArrayList<>(Arrays.asList(
            new ItemStack(Material.IRON_INGOT, 13),
            new ItemStack(Material.GOLD_INGOT, 16),
            new ItemStack(Material.OAK_LOG, 7),
            new ItemStack(Material.COBBLESTONE, 9),
            new ItemStack(Material.BREAD, 16),
            new ItemStack(Material.TORCH, 10)
    ));
    // 30-40 minutes
    private ArrayList<ItemStack> respawnFour = new ArrayList<>(Arrays.asList(
            new ItemStack(Material.COBBLESTONE, 9),
            new ItemStack(Material.IRON_INGOT, 19),
            new ItemStack(Material.GOLD_INGOT, 13),
            new ItemStack(Material.BREAD, 40),
            new ItemStack(Material.OAK_LOG, 10),
            new ItemStack(Material.WHITE_WOOL, 3)
    ));
    // 40-50 minutes
    private ArrayList<ItemStack> respawnFive = new ArrayList<>(Arrays.asList(
            new ItemStack(Material.COBBLESTONE, 12),
            new ItemStack(Material.IRON_INGOT, 21),
            new ItemStack(Material.GOLD_INGOT, 18),
            new ItemStack(Material.COOKED_BEEF, 12),
            new ItemStack(Material.OAK_LOG, 10),
            new ItemStack(Material.WHITE_WOOL, 3)
    ));
    // 50-60 minutes
    private ArrayList<ItemStack> respawnSix = new ArrayList<>(Arrays.asList(
            new ItemStack(Material.COBBLESTONE, 12),
            new ItemStack(Material.IRON_INGOT, 33),
            new ItemStack(Material.GOLD_INGOT, 19),
            new ItemStack(Material.COOKED_BEEF, 24),
            new ItemStack(Material.OAK_LOG, 10),
            new ItemStack(Material.WHITE_WOOL, 3),
            new ItemStack(Material.APPLE)
    ));
    // 60-70 minutes
    private ArrayList<ItemStack> respawnSeven = new ArrayList<>(Arrays.asList(
            new ItemStack(Material.COBBLESTONE, 12),
            new ItemStack(Material.IRON_INGOT, 39),
            new ItemStack(Material.GOLD_INGOT, 25),
            new ItemStack(Material.COOKED_BEEF, 32),
            new ItemStack(Material.OAK_LOG, 10),
            new ItemStack(Material.WHITE_WOOL, 3),
            new ItemStack(Material.APPLE)
    ));
    // 70-80 minutes
    private ArrayList<ItemStack> respawnEight = new ArrayList<>(Arrays.asList(
            new ItemStack(Material.COBBLESTONE, 12),
            new ItemStack(Material.IRON_INGOT, 42),
            new ItemStack(Material.GOLD_INGOT, 28),
            new ItemStack(Material.COOKED_BEEF, 32),
            new ItemStack(Material.OAK_LOG, 10),
            new ItemStack(Material.WHITE_WOOL, 3),
            new ItemStack(Material.APPLE),
            new ItemStack(Material.DIAMOND)
    ));
    // 80+
    private ArrayList<ItemStack> respawnNine = new ArrayList<>(Arrays.asList(
            new ItemStack(Material.COBBLESTONE, 12),
            new ItemStack(Material.IRON_INGOT, 42),
            new ItemStack(Material.GOLD_INGOT, 28),
            new ItemStack(Material.COOKED_BEEF, 32),
            new ItemStack(Material.OAK_LOG, 10),
            new ItemStack(Material.WHITE_WOOL, 3),
            new ItemStack(Material.APPLE),
            new ItemStack(Material.DIAMOND),
            new ItemStack(Material.FLINT, 4),
            new ItemStack(Material.FEATHER, 4)
    ));

    private ArrayList<ArrayList<ItemStack>> respawnLevel = new ArrayList<>(Arrays.asList(
            respawnOne,
            respawnTwo,
            respawnThree,
            respawnFour,
            respawnFive,
            respawnSix,
            respawnSeven,
            respawnEight,
            respawnNine
    ));

    public ArrayList<ItemStack> generateItemStack(int minutes) {
        if (minutes < 5) {
            return new ArrayList<>(
                    Arrays.asList(
                            new ItemStack(Material.CAKE),
                            new ItemStack(Material.DEAD_BUSH),
                            new ItemStack(Material.EGG)
                    )
            );
        }

        int tier = minutes / 10;
        if (tier > 8) {
            tier = 8;
        }
        return respawnLevel.get(tier);
    }
}
