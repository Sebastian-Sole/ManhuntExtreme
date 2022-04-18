package manhunt_extreme.commands;

import manhunt_extreme.GameEngine;
import manhunt_extreme.GameStateHandler;
import manhunt_extreme.manhunt_player.ManhuntPlayer;

public class SetHeadStartCommand {
    private ManhuntPlayer manhuntPlayer;
    private String[] args;
    private GameStateHandler gameStateHandler;
    private GameEngine gameEngine;

    public SetHeadStartCommand(ManhuntPlayer manhuntPlayer, String[] args, GameEngine gameEngine) {
        this.manhuntPlayer = manhuntPlayer;
        this.args = args;
        this.gameStateHandler = gameEngine.getGameStateHandler();
        this.gameEngine = gameEngine;
    }

    public boolean execute() {
        if (gameEngine.isRunning()) {
            manhuntPlayer.getPlayer().sendMessage("Cannot set this after the game has started");
            return true;
        }

        if (args.length == 0) {
            manhuntPlayer.getPlayer().sendMessage("Provide a headstart duration as a non-negative integer.");
            return true;
        }
        int duration = Integer.parseInt(args[0]);
        if (duration < 0) {
            manhuntPlayer.getPlayer().sendMessage("Please provide a non-negative integer");
            return true;
        }
        gameStateHandler.setHeadStart(duration);
        return true;
    }
}
