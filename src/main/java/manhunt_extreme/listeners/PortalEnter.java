package manhunt_extreme.listeners;

import manhunt_extreme.GameEngine;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class PortalEnter implements Listener {

    private final GameEngine gameEngine;
    private boolean hasAlreadyEnteredNether;

    public PortalEnter(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @EventHandler
    public void onPlayerEnterPortal(PlayerPortalEvent event) {
        System.out.println("Entered");
        if (event.getFrom().getWorld().equals(Bukkit.getWorld("world"))
                && event.getTo().getWorld().equals(Bukkit.getWorld("world_nether"))) {
            ManhuntPlayer manhuntPlayer = gameEngine.getManhuntPlayerFromPlayer(event.getPlayer());
            gameEngine.getOverworldPortals().put(manhuntPlayer, event.getFrom());
            gameEngine.getNetherPortals().put(manhuntPlayer, event.getTo());
            if (!hasAlreadyEnteredNether) {
                hasAlreadyEnteredNether = true;
                gameEngine.getTaskManager().getBlazeSpawnerHandler().start();
            }
        } else if (event.getFrom().getWorld().equals(Bukkit.getWorld("world"))
                && event.getTo().getWorld().equals(Bukkit.getWorld("world_end"))) {
            gameEngine.setEndPortalLocation(event.getFrom());
        } else if (event.getFrom().getWorld().equals(Bukkit.getWorld("world_nether"))
                && event.getTo().getWorld().equals(Bukkit.getWorld("world"))) {
            ManhuntPlayer manhuntPlayer = gameEngine.getManhuntPlayerFromPlayer(event.getPlayer());
            gameEngine.getNetherPortals().put(manhuntPlayer, event.getFrom());
        }
    }

    public boolean hasAlreadyEnteredNether() {
        return hasAlreadyEnteredNether;
    }
}
