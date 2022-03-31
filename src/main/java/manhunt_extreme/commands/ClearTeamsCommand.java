package manhunt_extreme.commands;

import manhunt_extreme.GameEngine;
import manhunt_extreme.manhunt_player.ManhuntPlayer;

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
        if (gameEngine.getGame().isRunning()) {
            manhuntPlayer.getPlayer().sendMessage("Game is running. End game before calling this command");
            return true;
        }
        if (args.length != 0) {
            manhuntPlayer.getPlayer().sendMessage("Invalid format. Please use /clearteams");
            return true;
        }
        for (ManhuntPlayer teammate : gameEngine.getRunnersTeam().getPlayerList()) {
            gameEngine.getRunnersTeam().removePlayer(teammate);
        }
        for (ManhuntPlayer teammate : gameEngine.getHuntersTeam().getPlayerList()) {
            gameEngine.getHuntersTeam().removePlayer(teammate);
        }
        return true;
    }
}
