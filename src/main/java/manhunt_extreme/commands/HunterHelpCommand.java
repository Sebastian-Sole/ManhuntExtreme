package manhunt_extreme.commands;

import manhunt_extreme.GameEngine;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;

public class HunterHelpCommand extends GameRuleCommand {

    public HunterHelpCommand(ManhuntPlayer manhuntPlayer, String[] args, GameEngine gameEngine, String commandCall) {
        super(manhuntPlayer, args, gameEngine, commandCall);
    }

    @Override
    public boolean execute() {
        if (isIllegalCommand()) {
            return true;
        }
        gameStateHandler.setHunterHelp(!gameStateHandler.isHunterHelp());
        Bukkit.broadcastMessage("Hunter help is: " + gameStateHandler.isHunterHelp());

        return true;
    }
}
