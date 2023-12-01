package manhunt_extreme.listeners.death_drop;

import manhunt_extreme.chest_generator.ChestItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DeathDropGenerator {


    private static Random random = new Random();

    private static ArrayList<Enchantment> bookEnchants = new ArrayList<>(Arrays.asList(
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
    ));

    public static ItemStack createItem() {
        int number = random.nextInt(43);

        if (number <= 4) {
            return new ItemStack(Material.GOLDEN_APPLE);
        } else if (number <= 10) {
            return new ItemStack(Material.ENDER_PEARL);
        } else if (number <= 15) {
            return new ItemStack(Material.DIAMOND, random.nextInt(3) + 1);
        } else if (number <= 19) {
            return new ItemStack(Material.OAK_WOOD, 16);
        } else if (number <= 25) {
            return new ItemStack(Material.ENCHANTING_TABLE);
        } else if (number <= 30) {
            return createBook();
        } else if (number <= 34) {
            return new ItemStack(Material.ANVIL);
        } else if (number <= 37) {
            return new ItemStack(Material.GOLD_BLOCK, 4);
        } else if (number <= 41) {
            return new ChestItem(true).createItemStack();
        } else {
            return new ItemStack(Material.IRON_BLOCK);
        }
    }

    private static ItemStack createBook() {
        ChestItem item = new ChestItem(Material.ENCHANTED_BOOK, bookEnchants);
        return item.createItemStack();
    }
}
