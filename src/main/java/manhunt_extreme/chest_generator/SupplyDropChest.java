package manhunt_extreme.chest_generator;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SupplyDropChest {
    private final Random random = new Random();

    private final ArrayList<Enchantment> pickaxeEnchants = new ArrayList<>(Arrays.asList(
            Enchantment.DIG_SPEED,
            Enchantment.LOOT_BONUS_BLOCKS,
            Enchantment.MENDING,
            Enchantment.DURABILITY
    ));
    private final ArrayList<Enchantment> bookEnchants = new ArrayList<>(Arrays.asList(
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
    private final ArrayList<Enchantment> swordEnchants = new ArrayList<>(Arrays.asList(
            Enchantment.DAMAGE_ALL,
            Enchantment.KNOCKBACK,
            Enchantment.LOOT_BONUS_MOBS
    ));
    private final ArrayList<Enchantment> mainArmorEnchants = new ArrayList<>(Arrays.asList(
            Enchantment.PROTECTION_ENVIRONMENTAL,
            Enchantment.PROTECTION_FIRE,
            Enchantment.PROTECTION_EXPLOSIONS,
            Enchantment.PROTECTION_PROJECTILE
    ));
    private final ArrayList<Enchantment> helmetEnchants = new ArrayList<>(Arrays.asList(
            Enchantment.OXYGEN,
            Enchantment.PROTECTION_ENVIRONMENTAL,
            Enchantment.PROTECTION_EXPLOSIONS,
            Enchantment.WATER_WORKER
    ));
    private final ArrayList<Enchantment> bootsEnchants = new ArrayList<>(Arrays.asList(
            Enchantment.PROTECTION_FALL,
            Enchantment.SOUL_SPEED,
            Enchantment.FROST_WALKER,
            Enchantment.PROTECTION_ENVIRONMENTAL));
    private final ArrayList<ChestItem> tierTwo = new ArrayList<>(Arrays.asList(
            new ChestItem(Material.OBSIDIAN, 5),
            new ChestItem(Material.ARROW, 15),
            new ChestItem(Material.IRON_CHESTPLATE, mainArmorEnchants),
            new ChestItem(Material.IRON_LEGGINGS, mainArmorEnchants),
            new ChestItem(Material.IRON_BOOTS, bootsEnchants),
            new ChestItem(Material.IRON_HELMET, helmetEnchants),
            new ChestItem(Material.BOOK, 12),
            new ChestItem(Material.BOOKSHELF, 4),
            new ChestItem(Material.GOLD_BLOCK, 2),
            new ChestItem(Material.ENDER_PEARL, 3),
            new ChestItem(Material.PIGLIN_SPAWN_EGG, 5),
            new ChestItem(Material.BLAZE_ROD, 1)
    ));
    private final ArrayList<ChestItem> tierThree = new ArrayList<>(Arrays.asList(
            new ChestItem(Material.GOLDEN_APPLE, 2),
            new ChestItem(Material.IRON_CHESTPLATE, 1),
            new ChestItem(Material.IRON_LEGGINGS, 1),
            new ChestItem(Material.IRON_BOOTS, 1),
            new ChestItem(Material.IRON_HELMET, 1),
            new ChestItem(Material.COOKED_BEEF, 32),
            new ChestItem(Material.OBSIDIAN, 2),
            new ChestItem(Material.IRON_INGOT, 5),
            new ChestItem(Material.COOKED_BEEF, 32),
            new ChestItem(Material.FLINT_AND_STEEL, 1)
    ));
    private final ArrayList<ChestItem> tierOne = new ArrayList<>(Arrays.asList(
            new ChestItem(Material.DIAMOND_BOOTS, bootsEnchants),
            new ChestItem(Material.DIAMOND_LEGGINGS, mainArmorEnchants),
            new ChestItem(Material.DIAMOND_CHESTPLATE, mainArmorEnchants),
            new ChestItem(Material.DIAMOND_HELMET, helmetEnchants),
            new ChestItem(Material.DIAMOND_PICKAXE, pickaxeEnchants),
            new ChestItem(Material.DIAMOND, random.nextInt(3) + 2),
            new ChestItem(Material.DIAMOND_SWORD, swordEnchants),
            new ChestItem(Material.ENDER_PEARL, random.nextInt(3) + 4),
            new ChestItem(true),
            new ChestItem(Material.BOOKSHELF, 6),
            new ChestItem(Material.IRON_INGOT, 15),
            new ChestItem(Material.GOLD_BLOCK, 4),
            new ChestItem(Material.ENCHANTED_BOOK, bookEnchants),
            new ChestItem(Material.ANVIL, 1),
            new ChestItem(Material.BLAZE_ROD, 2)
    ));
    private final ArrayList<List<ChestItem>> tiers = new ArrayList<>(Arrays.asList(
            tierOne,
            tierTwo,
            tierThree
    ));


    public SupplyDropChest() {
    }

    public ArrayList<ItemStack> populate() {
        var itemsToAdd = new ArrayList<ItemStack>();
        Random random = new Random();
        int numberOfItemsToAdd = random.nextInt(5) + 7;
        for (int i = 0; i < numberOfItemsToAdd; i++) {
            int generationNumber = random.nextInt(10) + 1;

            if (generationNumber <= 2) {
                itemsToAdd.add(tierOne.get(random.nextInt(tierOne.size())).createItemStack());
            } else if (generationNumber <= 6) {
                itemsToAdd.add(tierTwo.get(random.nextInt(tierTwo.size())).createItemStack());
            } else {
                itemsToAdd.add(tierThree.get(random.nextInt(tierThree.size())).createItemStack());
            }
        }
        return itemsToAdd;
    }


}
