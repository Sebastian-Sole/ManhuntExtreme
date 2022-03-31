package manhunt_extreme.commands;

import manhunt_extreme.Game;
import manhunt_extreme.manhunt_player.ManhuntPlayer;

public class SupplyDropsCommand extends GameRuleCommand {
    public SupplyDropsCommand(ManhuntPlayer manhuntPlayer, String[] args, Game game, String commandCall) {
        super(manhuntPlayer, args, game, commandCall);
    }

    @Override
    public boolean execute() {
        if (isIllegalCommand()) {
            return true;
        }
        gameStateHandler.setSupplyDrops(true);
        return true;
    }
}
