package manhunt_extreme;

import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class TargetSelectInventory {

    public static final String INVENTORY_NAME = "Select player to track";

    Inventory inv;
    GameEngine gameEngine;


    public TargetSelectInventory(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        inv = Bukkit.createInventory(null, 9, INVENTORY_NAME);
        int slotPosition = 0;
        for (ManhuntPlayer runner : gameEngine.getRunners()) {
            if (runner == null) continue;
            ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta meta = (SkullMeta) stack.getItemMeta();
            meta.setOwningPlayer(runner.getPlayer());
            meta.setDisplayName(runner.getPlayer().getName());
            stack.setItemMeta(meta);
            inv.setItem(slotPosition, stack);
            slotPosition++;
        }
    }

    public Inventory getInventory() {
        return inv;
    }

    public void displayToPlayer(Player player) {
        player.openInventory(inv);
    }


}
