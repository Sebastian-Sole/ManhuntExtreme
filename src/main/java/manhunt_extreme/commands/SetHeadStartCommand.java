package manhunt_extreme.commands;

import manhunt_extreme.Game;
import manhunt_extreme.GameEngine;
import manhunt_extreme.GameStateHandler;
import manhunt_extreme.manhunt_player.ManhuntPlayer;

public class SetHeadStartCommand {
    private ManhuntPlayer manhuntPlayer;
    private String[] args;
    private Game game;
    private GameStateHandler gameStateHandler;

    public SetHeadStartCommand(ManhuntPlayer manhuntPlayer, String[] args, GameEngine gameEngine) {
        this.manhuntPlayer = manhuntPlayer;
        this.args = args;
        this.game = gameEngine.getGame();
        this.gameStateHandler = this.game.getGameStateHandler();
    }

    public boolean execute() {
        if (game.isRunning()) {
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
