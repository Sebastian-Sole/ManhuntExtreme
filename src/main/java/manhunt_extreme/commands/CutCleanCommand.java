package manhunt_extreme.commands;

import manhunt_extreme.Game;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;

public class CutCleanCommand extends GameRuleCommand {

    public CutCleanCommand(ManhuntPlayer manhuntPlayer, String[] args, Game game, String commandCall) {
        super(manhuntPlayer, args, game, commandCall);
    }

    @Override
    public boolean execute() {
        if (isIllegalCommand()) {
            return true;
        }
        gameStateHandler.setCutClean(!gameStateHandler.isCutClean());
        Bukkit.broadcastMessage("Cut clean is set to: " + gameStateHandler.isCutClean());
        return true;
    }
}
