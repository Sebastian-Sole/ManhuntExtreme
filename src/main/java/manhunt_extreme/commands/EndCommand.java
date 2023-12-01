package manhunt_extreme.commands;

import manhunt_extreme.GameEngine;
import manhunt_extreme.GameStateHandler;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;

public class EndCommand {
    private ManhuntPlayer manhuntPlayer;
    private String[] args;
    private GameEngine gameEngine;
    private GameStateHandler gameStateHandler;

    public EndCommand(ManhuntPlayer manhuntPlayer, String[] args, GameEngine gameEngine) {
        this.manhuntPlayer = manhuntPlayer;
        this.args = args;
        this.gameEngine = gameEngine;
        this.gameStateHandler = gameEngine.getGameStateHandler();
    }

    public boolean execute() {
        if (!gameEngine.isRunning()) {
            manhuntPlayer.getPlayer().sendMessage("There is no game in progress. Use /start to start a game");
            return true;
        }
        // Todo: Cancel tasks
        gameEngine.setRunning(false);
        gameEngine.reset();

        Bukkit.broadcastMessage("Manhunt Stopped!");
        return true;
    }
}
