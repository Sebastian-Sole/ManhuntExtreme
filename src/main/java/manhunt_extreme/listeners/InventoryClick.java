package manhunt_extreme.listeners;

import manhunt_extreme.GameEngine;
import manhunt_extreme.TargetSelectInventory;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.SkullMeta;

public class InventoryClick implements Listener {

    private GameEngine gameEngine;

    public InventoryClick(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        var player = (Player) event.getWhoClicked();
        var manhuntPlayer = gameEngine.getManhuntPlayerFromPlayer(player);
        var clickedHead = event.getCurrentItem();
        if (event.getView().getTitle().equals(TargetSelectInventory.INVENTORY_NAME)) {
            if (clickedHead == null || clickedHead.getType() != Material.PLAYER_HEAD) {
                gameEngine.getLogger().warning("Item clicked is not player head");
                event.setCancelled(true);
                return;
            }
            if (!clickedHead.hasItemMeta()) {
                gameEngine.getLogger().warning("Clicked head has no item meta");
                player.sendMessage("Something went wrong: Does not have ItemMeta");
                event.setCancelled(true);
                return;
            }
            var itemMeta = clickedHead.getItemMeta();
            if (!(itemMeta instanceof SkullMeta meta)) {
                gameEngine.getLogger().warning("Clicked head meta is not instanceof SkullMeta.");
                gameEngine.getLogger().info(itemMeta.getClass().toString());
                player.sendMessage("Something went wrong: Not an instanceof SkullMeta");
                event.setCancelled(true);
                return;
            }
            Player owningPlayer = (Player) meta.getOwningPlayer();
            ManhuntPlayer target = gameEngine.getManhuntPlayerFromPlayer(owningPlayer);
            gameEngine.getTargets().put(manhuntPlayer, target);
            event.setCancelled(true);
            player.closeInventory();
            player.sendMessage("Compass is now targeting " + target.getPlayer().getName());

        }
    }

}
