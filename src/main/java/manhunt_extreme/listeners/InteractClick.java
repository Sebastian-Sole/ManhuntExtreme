package manhunt_extreme.listeners;

import manhunt_extreme.GameEngine;
import manhunt_extreme.TargetSelectInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractClick implements Listener {

    private GameEngine gameEngine;


    public InteractClick(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getEquipment().getItemInMainHand().getType() == Material.COMPASS) {
            if (gameEngine.getGame().isRunning()) {
                player.sendMessage("Start a Manhunt game before using the compass!");
            } else {
                var inv = new TargetSelectInventory(gameEngine);
                inv.displayToPlayer(player);
            }
        }
        //Todo: Eye of ender throw
    }
}
