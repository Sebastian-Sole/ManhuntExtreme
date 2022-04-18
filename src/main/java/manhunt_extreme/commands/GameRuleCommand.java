package manhunt_extreme.commands;

import manhunt_extreme.GameEngine;
import manhunt_extreme.GameStateHandler;
import manhunt_extreme.manhunt_player.ManhuntPlayer;

public abstract class GameRuleCommand {

    protected ManhuntPlayer manhuntPlayer;
    protected String[] args;
    protected String commandCall;
    protected GameEngine gameEngine;
    protected GameStateHandler gameStateHandler;

    public GameRuleCommand(ManhuntPlayer manhuntPlayer, String[] args, GameEngine gameEngine, String commandCall) {
        this.manhuntPlayer = manhuntPlayer;
        this.args = args;
        this.commandCall = commandCall;
        this.gameEngine = gameEngine;
        this.gameStateHandler = gameEngine.getGameStateHandler();
    }

    public boolean isIllegalCommand() {
        if (gameEngine.isRunning()) {
            manhuntPlayer.getPlayer().sendMessage("Game is running. Restart a game to change this option.");
            return true;
        }
        if (args.length != 0) {
            manhuntPlayer.getPlayer().sendMessage("Illegal format. Use /" + commandCall);
            return true;
        }
        return false;
    }

    public abstract boolean execute();


}
