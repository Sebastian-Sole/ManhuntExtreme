package manhunt_extreme.commands;

import manhunt_extreme.GameEngine;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;

public class ExtraDropsCommand extends GameRuleCommand {

    public ExtraDropsCommand(ManhuntPlayer manhuntPlayer, String[] args, GameEngine gameEngine, String commandLabel) {
        super(manhuntPlayer, args, gameEngine, commandLabel);
    }

    @Override
    public boolean execute() {
        if (isIllegalCommand()) {
            return true;
        }
        gameStateHandler.setExtraDrops(!gameStateHandler.isExtraDrops());
        Bukkit.broadcastMessage("Extra drops is set to: " + gameStateHandler.isExtraDrops());
        return true;
    }
}
