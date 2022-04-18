package manhunt_extreme.commands;

import manhunt_extreme.GameEngine;
import manhunt_extreme.GameStateHandler;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;

public class HealthCommand {

    private ManhuntPlayer manhuntPlayer;
    private String[] args;
    private GameEngine gameEngine;
    private GameStateHandler gameStateHandler;

    public HealthCommand(ManhuntPlayer manhuntPlayer, String[] args, GameEngine gameEngine) {

        this.manhuntPlayer = manhuntPlayer;
        this.args = args;
        this.gameEngine = gameEngine;
        this.gameStateHandler = this.gameEngine.getGameStateHandler();
    }

    public boolean execute() {
        if (gameEngine.isRunning()) {
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
            Bukkit.broadcastMessage("Health is: " + gameStateHandler.getHealth());
            return true;
        } catch (NumberFormatException e) {
            manhuntPlayer.getPlayer().sendMessage("Invalid number");
            return true;
        }
    }

}
