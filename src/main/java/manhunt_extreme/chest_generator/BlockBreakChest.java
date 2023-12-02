package manhunt_extreme.chest_generator;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BlockBreakChest {

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
            Enchantment.PROTECTION_ENVIRONMENTAL
    ));
    private final ArrayList<ChestItem> tierOne = new ArrayList<>(Arrays.asList(
            new ChestItem(Material.DIAMOND_BOOTS, bootsEnchants),
            new ChestItem(Material.DIAMOND_LEGGINGS, mainArmorEnchants),
            new ChestItem(Material.DIAMOND_CHESTPLATE, mainArmorEnchants),
            new ChestItem(Material.DIAMOND_HELMET, helmetEnchants),
            new ChestItem(Material.DIAMOND_PICKAXE, pickaxeEnchants),
            new ChestItem(Material.DIAMOND, random.nextInt(3) + 1),
            new ChestItem(Material.DIAMOND_SWORD, swordEnchants),
            new ChestItem(Material.ENDER_PEARL, random.nextInt(3) + 3),
            new ChestItem(true),
            new ChestItem(Material.IRON_CHESTPLATE, mainArmorEnchants),
            new ChestItem(Material.IRON_LEGGINGS, mainArmorEnchants),
            new ChestItem(Material.IRON_BOOTS, bootsEnchants),
            new ChestItem(Material.IRON_HELMET, helmetEnchants),
            new ChestItem(Material.BOOKSHELF, 6),
            new ChestItem(Material.BLAZE_ROD, 3)
    ));
    private final ArrayList<ChestItem> tierTwo = new ArrayList<>(Arrays.asList(
            new ChestItem(Material.DIAMOND_BOOTS, 1),
            new ChestItem(Material.DIAMOND_HELMET, 1),
            new ChestItem(Material.ENDER_PEARL, random.nextInt(5) + 2),
            new ChestItem(Material.ENCHANTING_TABLE, 1),
            new ChestItem(Material.IRON_INGOT, random.nextInt(16) + 6),
            new ChestItem(Material.DIAMOND, 1),
            new ChestItem(Material.PIGLIN_SPAWN_EGG, 5),
            new ChestItem(Material.OBSIDIAN, 5),
            new ChestItem(Material.ARROW, 15),
            new ChestItem(Material.IRON_CHESTPLATE, 1),
            new ChestItem(Material.IRON_LEGGINGS, 1),
            new ChestItem(Material.IRON_BOOTS, bootsEnchants),
            new ChestItem(Material.IRON_HELMET, helmetEnchants),
            new ChestItem(Material.BOOK, 12),
            new ChestItem(Material.BOOKSHELF, 4),
            new ChestItem(Material.BLAZE_ROD, random.nextInt(3) + 1),
            new ChestItem(Material.GOLD_BLOCK, random.nextInt(2) + 2)
    ));
    private final ArrayList<ChestItem> tierThree = new ArrayList<>(Arrays.asList(
            new ChestItem(Material.GOLDEN_APPLE, 1),
            new ChestItem(Material.IRON_INGOT, random.nextInt(13) + 1),
            new ChestItem(Material.ENCHANTED_BOOK, bookEnchants),
            new ChestItem(Material.ANVIL, 1),
            new ChestItem(Material.GOLD_BLOCK, random.nextInt(2) + 1),
            new ChestItem(Material.GOLD_INGOT, random.nextInt(15) + 15),
            new ChestItem(Material.ENDER_PEARL, random.nextInt(4) + 2),
            new ChestItem(Material.BUCKET, 1),
            new ChestItem(Material.OBSIDIAN, 3),
            new ChestItem(Material.BOOK, 6),
            new ChestItem(Material.BOOKSHELF, 2),
            new ChestItem(Material.BLAZE_SPAWN_EGG, random.nextInt(3) + 1),
            new ChestItem(Material.PIGLIN_SPAWN_EGG, 3)
    ));
    private final ArrayList<ChestItem> tierFour = new ArrayList<>(Arrays.asList(
            new ChestItem(Material.SHULKER_BOX, 1),
            new ChestItem(Material.COOKED_BEEF, random.nextInt(10) + 10),
            new ChestItem(Material.COAL, random.nextInt(10) + 10),
            new ChestItem(Material.GOLD_INGOT, random.nextInt(9) + 12),
            new ChestItem(Material.IRON_INGOT, random.nextInt(6) + 1),
            new ChestItem(Material.GOLD_NUGGET, 50),
            new ChestItem(Material.GUNPOWDER, 5),
            new ChestItem(Material.FEATHER, 7),
            new ChestItem(Material.FLINT, 3),
            new ChestItem(Material.BOOK, 3),
            new ChestItem(Material.ENDER_PEARL, random.nextInt(2) + 1),
            new ChestItem(Material.BLAZE_SPAWN_EGG, 2),
            new ChestItem(Material.PIGLIN_SPAWN_EGG, 1)
    ));
    private final ArrayList<ChestItem> tierFive = new ArrayList<>(Arrays.asList(
            new ChestItem(Material.OAK_PLANKS, 32),
            new ChestItem(Material.TORCH, 32),
            new ChestItem(Material.COAL, random.nextInt(20) + 5),
            new ChestItem(Material.HAY_BLOCK, 16),
            new ChestItem(Material.EXPERIENCE_BOTTLE, 10),
            new ChestItem(Material.IRON_INGOT, random.nextInt(3) + 1),
            new ChestItem(Material.SHIELD, 1),
            new ChestItem(Material.CYAN_BED, 1),
            new ChestItem(Material.STICK, 10),
            new ChestItem(Material.GOLD_NUGGET, 27),
            new ChestItem(Material.IRON_NUGGET, 27),
            new ChestItem(Material.BOOK, 1)
    ));

    private final List<List<ChestItem>> chestTiers = Arrays.asList(
            tierOne,
            tierTwo,
            tierThree,
            tierFour,
            tierFive
    );

    public BlockBreakChest() {
    }

    protected ArrayList<ItemStack> populate() {
        var itemsToAdd = new ArrayList<ItemStack>();
        Random random = new Random();
        int numberOfItemsToAdd = random.nextInt(5) + 6;
        for (int i = 0; i < numberOfItemsToAdd; i++) {
            int generationNumber = random.nextInt(28) + 1;
            if (generationNumber <= 2) {
                itemsToAdd.add(tierOne.get(random.nextInt(tierOne.size())).createItemStack());
            } else if (generationNumber <= 7) {
                itemsToAdd.add(tierTwo.get(random.nextInt(tierTwo.size())).createItemStack());
            } else if (generationNumber <= 13) {
                itemsToAdd.add(tierThree.get(random.nextInt(tierThree.size())).createItemStack());
            } else if (generationNumber <= 20) {
                itemsToAdd.add(tierFour.get(random.nextInt(tierFour.size())).createItemStack());
            } else {
                itemsToAdd.add(tierFive.get(random.nextInt(tierFive.size())).createItemStack());
            }
        }
        if (random.nextInt(17) == 3) {
            itemsToAdd.add(new ChestItem().createJammer());
        }
        // If itemsToAdd contains enchanting table, add lapis
        if (itemsToAdd.stream().anyMatch(itemStack -> itemStack.getType() == Material.ENCHANTING_TABLE)) {
            itemsToAdd.add(new ChestItem(Material.LAPIS_LAZULI, 3).createItemStack());
        }
        return itemsToAdd;
    }
}
