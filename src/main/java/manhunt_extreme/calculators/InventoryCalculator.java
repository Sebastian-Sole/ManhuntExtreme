package manhunt_extreme.calculators;

import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryCalculator {

    private final List<Material> valuableItems = Arrays.asList(
            Material.WOODEN_AXE,
            Material.GOLDEN_AXE,
            Material.STONE_AXE,
            Material.IRON_AXE,
            Material.DIAMOND_AXE,
            Material.NETHERITE_AXE,
            Material.WOODEN_SWORD,
            Material.GOLDEN_SWORD,
            Material.STONE_SWORD,
            Material.IRON_SWORD,
            Material.IRON_SWORD,
            Material.DIAMOND_SWORD,
            Material.NETHERITE_SWORD,
            Material.BOW,
            Material.CROSSBOW
    );

    private final List<Double> helmetScore = Arrays.asList(1.0, 2.0, 3.0, 5.0, 5.0);
    private final List<Double> chestplateScore = Arrays.asList(3.0, 5.0, 7.0, 11.0, 11.0);
    private final List<Double> leggingsScore = Arrays.asList(2.0, 3.0, 6.0, 9.0, 9.0);
    private final List<Double> bootsScore = Arrays.asList(1.0, 1.0, 3.0, 5.0, 5.0);

    private final List<Double> axeScore = Arrays.asList(2.5, 3.0, 3.3, 3.6, 4.0);
    private final List<Double> swordScore = Arrays.asList(0.5, 1.0, 2.0, 3.0, 4.0);
    private final List<Double> bowScore = Arrays.asList(4.0, 4.5);
    private final Double enchantWeight = 1.3;
    private final ManhuntPlayer manhuntPlayer;


    public InventoryCalculator(ManhuntPlayer manhuntPlayer) {
        this.manhuntPlayer = manhuntPlayer;
    }

    public Double calculateInventoryScore() {
        return calculateInventoryArmorScore() + calculateInventoryWeaponScore();
    }

    /**
     * Calculate the score of a player's armor
     *
     * @return the score of armor
     */
    private Double calculateInventoryArmorScore() {
        var invScore = 0.0;
        final var inv = this.manhuntPlayer.getPlayer().getInventory();

        if (inv.getHelmet() != null) {
            invScore += calculateArmorPieceScore(inv.getHelmet(), helmetScore);
        }
        if (inv.getLeggings() != null) {
            invScore += calculateArmorPieceScore(inv.getLeggings(), leggingsScore);
        }
        if (inv.getChestplate() != null) {
            invScore += calculateArmorPieceScore(inv.getChestplate(), chestplateScore);
        }
        if (inv.getBoots() != null) {
            invScore += calculateArmorPieceScore(inv.getBoots(), bootsScore);
        }
        return invScore;
    }

    /**
     * @param armorPiece Armor piece being evaluated
     * @param armorList  the category of the armor piece
     * @return the score of the armor piece
     */
    private Double calculateArmorPieceScore(ItemStack armorPiece, List<Double> armorList) {
        var armorPieceName = armorPiece.getType().name();
        var substringName = armorPieceName.substring(0, armorPieceName.indexOf("_"));
        var armorWeight = calculateArmorWeight(substringName);
        Double armorPieceScore = 0.0;
        if (armorWeight != -1) {
            armorPieceScore = armorList.get(armorWeight);
        }
        if (!armorPiece.getEnchantments().isEmpty()) {
            armorPieceScore *= enchantWeight;
        }
        return armorPieceScore;
    }


    /**
     * @param name The name of the item
     * @return the index which the item is in their category.
     */
    private int calculateArmorWeight(String name) {
        switch (name) {
            case "LEATHER" -> {
                return 0;
            }
            case "GOLD", "CHAINMAIL" -> {
                return 1;
            }
            case "IRON" -> {
                return 2;
            }
            case "DIAMOND" -> {
                return 3;
            }
            case "NETHERITE" -> {
                return 4;
            }
        }
        return -1;
    }

    /**
     * @return the score of a player's weapons
     */
    private Double calculateInventoryWeaponScore() {
        Double weaponScore = 0.0;
        final var inv = this.manhuntPlayer.getPlayer().getInventory();
        final var hotbar = new ArrayList<ItemStack>();
        for (int i = 0; i <= 8; i++) {
            if (inv.isEmpty()) {
                break;
            }
            if (inv.getItem(i) == null) {
                continue;
            }
            hotbar.add(inv.getItem(i));
        }

        for (ItemStack itemStack : hotbar) {
            weaponScore += calculateItemScore(itemStack);
        }
        return weaponScore;
    }

    /**
     * @param itemStack The material which is being taken into consideration
     * @return the score of the item based on its ore, type, and potential enchantments.
     */
    private Double calculateItemScore(ItemStack itemStack) {
        var material = itemStack.getType();
        if (!valuableItems.contains(material)) {
            return 0.0;
        }

        double totalItemScore;

        if (!(material.name().equals("BOW") || material.name().equals("CROSSBOW"))) {
            var materialOre = material.name().substring(0, material.name().indexOf("_"));
            var materialType = material.name().substring(material.name().indexOf("_") + 1);

            int oreScore;

            switch (materialOre) {
                case "WOODEN", "GOLDEN" -> oreScore = 0;
                case "STONE" -> oreScore = 1;
                case "IRON" -> oreScore = 2;
                case "DIAMOND" -> oreScore = 3;
                case "NETHERITE" -> oreScore = 4;
                default -> {
                    throw new IllegalArgumentException("Material is " + materialOre);
                }
            }
            switch (materialType) {
                case "AXE" -> totalItemScore = axeScore.get(oreScore);
                case "SWORD" -> totalItemScore = swordScore.get(oreScore);
                default -> throw new IllegalArgumentException("Type is " + materialType);
            }
        } else {
            switch (material.name()) {
                case "BOW" -> totalItemScore = bowScore.get(0);
                case "CROSSBOW" -> totalItemScore = bowScore.get(1);
                default -> throw new IllegalArgumentException("Error when item should be a type of bow. Item: " + material.name());
            }
        }


        if (itemStack.getType() == material) {
            if (!itemStack.getEnchantments().isEmpty()) {
                totalItemScore *= enchantWeight;
            }
        }
        return totalItemScore;
    }


}

