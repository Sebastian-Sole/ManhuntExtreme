package manhunt_extreme.listeners.respawn_handler;

import manhunt_extreme.GameEngine;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.inventory.ItemStack;

public class RespawnHandler {
    private final RespawnInventoryGenerator respawnInventoryGenerator;
    private final GameEngine gameEngine;

    public RespawnHandler(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.respawnInventoryGenerator = gameEngine.getGameBalancingCalculator().getRespawnInventoryGenerator();
    }

    public void giveItems(ManhuntPlayer manhuntPlayer) {
        var items = respawnInventoryGenerator.generateItemStack(gameEngine.getTaskManager().getGameClock().getMinutes());
        for (ItemStack stack : items) {
            manhuntPlayer.getPlayer().getInventory().addItem(stack);
        }
    }

}
