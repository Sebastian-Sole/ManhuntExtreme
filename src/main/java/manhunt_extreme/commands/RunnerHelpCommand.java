package manhunt_extreme.commands;

import manhunt_extreme.Game;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;

public class RunnerHelpCommand extends GameRuleCommand {

    public RunnerHelpCommand(ManhuntPlayer manhuntPlayer, String[] args, Game game, String commandCall) {
        super(manhuntPlayer, args, game, commandCall);
    }

    @Override
    public boolean execute() {
        if (isIllegalCommand()) {
            return true;
        }
        gameStateHandler.setRunnerHelp(!gameStateHandler.isRunnerHelp());
        Bukkit.broadcastMessage("Runner help is: " + gameStateHandler.isRunnerHelp());
        return true;
    }
}
