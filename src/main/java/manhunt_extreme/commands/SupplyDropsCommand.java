package manhunt_extreme.commands;

import manhunt_extreme.GameEngine;
import manhunt_extreme.manhunt_player.ManhuntPlayer;

public class SupplyDropsCommand extends GameRuleCommand {
    public SupplyDropsCommand(ManhuntPlayer manhuntPlayer, String[] args, GameEngine gameEngine, String commandCall) {
        super(manhuntPlayer, args, gameEngine, commandCall);
    }

    @Override
    public boolean execute() {
        if (isIllegalCommand()) {
            return true;
        }
        gameEngine.getGameStateHandler().setSupplyDrops(!gameStateHandler.isSupplyDrops());
        manhuntPlayer.getPlayer().sendMessage("Supply drops are set to: " + gameStateHandler.isSupplyDrops());

        return true;
    }
}
