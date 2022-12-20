package manhunt_extreme.commands;

import manhunt_extreme.GameEngine;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;

public class ClearTeamsCommand extends GameRuleCommand {

    public ClearTeamsCommand(ManhuntPlayer manhuntPlayer, String[] args, GameEngine gameEngine) {
        super(manhuntPlayer, args, gameEngine, "clearteams");
    }

    public boolean execute() {
        if (isIllegalCommand()) {
            return true;
        }
        gameEngine.getHuntersTeam().clearTeam();
        gameEngine.getRunnersTeam().clearTeam();
        Bukkit.broadcastMessage("Teams have been cleared");
        return true;
    }
}
