package manhunt_extreme.chest_generator;

import manhunt_extreme.GameEngine;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class ChestPopulator {

    private GameEngine gameEngine;
    private Random random;

    public ArrayList<ItemStack> populateChest(String type) throws IllegalArgumentException {
        if (type.equalsIgnoreCase("chest")) {
            BlockBreakChest blockBreakChest = new BlockBreakChest(random);
            return blockBreakChest.populate();
        } else if (type.equalsIgnoreCase("supply")) {
            SupplyDropChest supplyDropChest = new SupplyDropChest(random);
            return supplyDropChest.populate();
        } else {
            throw new IllegalArgumentException("Tried to populate a chest but couldn't. Generation type: " + type);
        }

    }


    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }


}
