package manhunt_extreme.commands;

import manhunt_extreme.Game;
import manhunt_extreme.GameStateHandler;
import manhunt_extreme.manhunt_player.ManhuntPlayer;

public class HealthCommand {

    private ManhuntPlayer manhuntPlayer;
    private String[] args;
    private Game game;
    private GameStateHandler gameStateHandler;

    public HealthCommand(ManhuntPlayer manhuntPlayer, String[] args, Game game) {

        this.manhuntPlayer = manhuntPlayer;
        this.args = args;
        this.game = game;
        this.gameStateHandler = this.game.getGameStateHandler();
    }

    public boolean execute() {
        if (game.isRunning()) {
            manhuntPlayer.getPlayer().sendMessage("Game is running. Start a new game to use this command");
            return true;
        }
        if (args.length != 1) {
            manhuntPlayer.getPlayer().sendMessage("Invalid format. Use /health <number>");
            return true;
        }
        try {
            var health = Double.parseDouble(args[0]);
            if (health < 20.0) {
                manhuntPlayer.getPlayer().sendMessage("Cannot set health under 20");
                return true;
            }
            gameStateHandler.setHealth(health);
            return true;
        } catch (NumberFormatException e) {
            manhuntPlayer.getPlayer().sendMessage("Invalid number");
            return true;
        }
    }

}
