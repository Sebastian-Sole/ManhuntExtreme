package manhunt_extreme.listeners.death_drop;

import manhunt_extreme.chest_generator.ChestItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DeathDropGenerator {


    private static Random random = new Random();

    private static List<Enchantment> bookEnchants = Arrays.asList(
            Enchantment.DAMAGE_ALL,
            Enchantment.DEPTH_STRIDER,
            Enchantment.DURABILITY,
            Enchantment.PROTECTION_FALL,
            Enchantment.ARROW_DAMAGE,
            Enchantment.ARROW_KNOCKBACK,
            Enchantment.DIG_SPEED,
            Enchantment.WATER_WORKER,
            Enchantment.LOOT_BONUS_BLOCKS,
            Enchantment.FROST_WALKER
    );

    public static ItemStack createItem() {
        int number = random.nextInt(42);

        if (isBetween(number, 0, 4)) {
            return new ItemStack(Material.GOLDEN_APPLE);
        } else if (isBetween(number, 5, 10)) {
            return new ItemStack(Material.ENDER_PEARL);
        } else if (isBetween(number, 11, 15)) {
            return new ItemStack(Material.DIAMOND, random.nextInt(3) + 1);
        } else if (isBetween(number, 16, 19)) {
            return new ItemStack(Material.OAK_WOOD, 16);
        } else if (isBetween(number, 20, 25)) {
            return new ItemStack(Material.ENCHANTING_TABLE);
        } else if (isBetween(number, 26, 30)) {
            return createBook();
        } else if (isBetween(number, 31, 34)) {
            return new ItemStack(Material.ANVIL);
        } else if (isBetween(number, 35, 37)) {
            return new ItemStack(Material.GOLD_BLOCK, 4);
        } else if (isBetween(number, 38, 41)) {
            return new ChestItem(true).createItemStack();
        } else {
            return new ItemStack(Material.IRON_BLOCK);
        }
    }

    private static ItemStack createBook() {
        ChestItem item = new ChestItem(Material.ENCHANTED_BOOK, bookEnchants);
        return item.createItemStack();
    }

    private static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

}
