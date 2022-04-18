package manhunt_extreme.commands;

import manhunt_extreme.GameEngine;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;

public class ClearTeamsCommand {
    private ManhuntPlayer manhuntPlayer;
    private String[] args;
    private GameEngine gameEngine;

    public ClearTeamsCommand(ManhuntPlayer manhuntPlayer, String[] args, GameEngine gameEngine) {
        this.manhuntPlayer = manhuntPlayer;
        this.args = args;
        this.gameEngine = gameEngine;
    }

    public boolean execute() {
        if (gameEngine.isRunning()) {
            manhuntPlayer.getPlayer().sendMessage("Game is running. End game before calling this command");
            return true;
        }
        if (args.length != 0) {
            manhuntPlayer.getPlayer().sendMessage("Invalid format. Please use /clearteams");
            return true;
        }
        gameEngine.getHuntersTeam().clearTeam();
        gameEngine.getRunnersTeam().clearTeam();
        Bukkit.broadcastMessage("Teams have been cleared");
        return true;
    }
}
