package manhunt_extreme.commands;

import manhunt_extreme.GameEngine;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;

public class HasteBoostCommand extends GameRuleCommand {

    public HasteBoostCommand(ManhuntPlayer manhuntPlayer, String[] args, GameEngine gameEngine, String commandCall) {
        super(manhuntPlayer, args, gameEngine, commandCall);
    }

    @Override
    public boolean execute() {
        if (isIllegalCommand()) {
            return true;
        }
        gameStateHandler.setHasteBoost(!gameStateHandler.isHasteBoost());
        Bukkit.broadcastMessage("Haste boost is: " + gameStateHandler.isHasteBoost());

        return true;
    }
}
