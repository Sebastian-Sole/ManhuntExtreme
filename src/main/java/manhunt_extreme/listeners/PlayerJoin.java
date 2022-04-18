package manhunt_extreme.listeners;

import manhunt_extreme.GameEngine;
import manhunt_extreme.calculators.PlayerScoreCalculator;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    private GameEngine gameEngine;

    public PlayerJoin(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ManhuntPlayer manhuntPlayer = new ManhuntPlayer(player);
        PlayerScoreCalculator playerScoreCalculator = new PlayerScoreCalculator(manhuntPlayer, gameEngine.getTaskManager().getGameClock());
        manhuntPlayer.setPlayerScoreCalculator(playerScoreCalculator);
        if (!gameEngine.getManhuntPlayers().contains(manhuntPlayer)) {
            gameEngine.getManhuntPlayers().add(manhuntPlayer);
        }
    }

}
