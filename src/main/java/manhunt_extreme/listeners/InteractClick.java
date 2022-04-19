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
            if (gameEngine.isRunning()) {
                player.sendMessage("Start a Manhunt game before using the compass!");
            } else {
                var inv = new TargetSelectInventory(gameEngine);
                inv.displayToPlayer(player);
            }
        }
        if (player.getEquipment().getItemInMainHand().getType().equals(Material.BEDROCK)) {
            if (gameEngine.getGameStateHandler().isAllowJamming()) {
                var jammer = player.getEquipment().getItemInMainHand();
                switch (jammer.getItemMeta().getDisplayName().toLowerCase()) {
                    case "1 min jammer" -> {
                        gameEngine.getTaskManager().getCompassJammer().jamCompass(1200L);
                    }
                    case "2 min jammer" -> {
                        gameEngine.getTaskManager().getCompassJammer().jamCompass(2400L);
                    }
                    case "5 min jammer" -> {
                        gameEngine.getTaskManager().getCompassJammer().jamCompass(6000L);
                    }
                    default -> {
                        throw new IllegalArgumentException("Jammer has no display name, could not jam");
                    }
                }
            } else {
                throw new IllegalArgumentException("Jammers are not activated");
            }
        }
    }

}
