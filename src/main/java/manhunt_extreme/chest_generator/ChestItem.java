package manhunt_extreme.chest_generator;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ChestItem {

    private Material material = null;
    private int numberGenerated = 1;
    private ArrayList<Enchantment> enchants = new ArrayList<>();
    private Random random = new Random();
    private int enchantmentStrengths = 1;
    private int enchantsGenerated = random.nextInt(2);
    private boolean isPotion = false;
    private List<PotionType> potionTypes = Arrays.asList(
            PotionType.SPEED,
            PotionType.FIRE_RESISTANCE,
            PotionType.INSTANT_HEAL,
            PotionType.INVISIBILITY,
            PotionType.NIGHT_VISION,
            PotionType.REGEN,
            PotionType.WATER_BREATHING,
            PotionType.JUMP
    );

    public ChestItem(Material material, int numberGenerated) {
        this.material = material;
        this.numberGenerated = numberGenerated;
    }

    public ChestItem(Material material, ArrayList<Enchantment> enchants) {
        this.material = material;
        this.enchants = enchants;
    }

    public ChestItem(boolean isPotion) {
        this.isPotion = isPotion;
    }

    public ItemStack createItemStack() {
        if (isPotion) {
            ItemStack potion = new ItemStack(Material.POTION, 1);
            PotionMeta meta = (PotionMeta) potion.getItemMeta();
            meta.setBasePotionData(new PotionData(potionTypes.get(random.nextInt(potionTypes.size()))));
            potion.setItemMeta(meta);
            return potion;
        }

        ItemStack itemStack = new ItemStack(material, numberGenerated);

        // Check if material is enchanted book
        if (material == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) itemStack.getItemMeta();
            // Add a single enchantment, with the strength
            meta.addStoredEnchant(enchants.get(random.nextInt(enchants.size())), enchantmentStrengths, true);
            itemStack.setItemMeta(meta);
        }
        // If it isn't an enchanted book
        else {
            if (enchantsGenerated == 0) {
                return itemStack;
            }
            // If item to be generated is unenchantable, then just return the item
            if (enchants.size() == 0) {
                return itemStack;
            }

            itemStack.addEnchantment(enchants.get(random.nextInt(enchants.size())), enchantmentStrengths);
        }
        return itemStack;
    }


}
