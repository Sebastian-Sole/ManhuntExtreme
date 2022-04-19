package manhunt_extreme.listeners;

import manhunt_extreme.GameEngine;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {
    
    private GameEngine gameEngine;

    public QuitEvent(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        ManhuntPlayer manhuntPlayer = gameEngine.getManhuntPlayerFromPlayer(player);
        manhuntPlayer.getTeam().removePlayer(manhuntPlayer);
        gameEngine.getManhuntPlayers().remove(manhuntPlayer);
    }

}
