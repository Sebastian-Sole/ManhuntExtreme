package manhunt_extreme.commands;

import manhunt_extreme.GameEngine;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;

public class RunnerHelpCommand extends GameRuleCommand {

    public RunnerHelpCommand(ManhuntPlayer manhuntPlayer, String[] args, GameEngine gameEngine, String commandCall) {
        super(manhuntPlayer, args, gameEngine, commandCall);
    }

    @Override
    public boolean execute() {
        if (isIllegalCommand()) {
            return true;
        }
        gameStateHandler.setRunnerHelp(!gameStateHandler.isRunnerHelp());
        Bukkit.broadcastMessage("Runner help is set to: " + gameStateHandler.isRunnerHelp());
        return true;
    }
}
