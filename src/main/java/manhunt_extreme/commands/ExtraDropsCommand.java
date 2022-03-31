package manhunt_extreme.commands;

import manhunt_extreme.Game;
import manhunt_extreme.GameStateHandler;
import manhunt_extreme.manhunt_player.ManhuntPlayer;

public class ExtraDropsCommand extends GameRuleCommand {

    public ExtraDropsCommand(ManhuntPlayer manhuntPlayer, String[] args, Game game, GameStateHandler gameStateHandler, String commandLabel) {
        super(manhuntPlayer, args, game, gameStateHandler, commandLabel);
    }

    @Override
    public boolean execute() {
        if (isIllegalCommand()) {
            return true;
        }
        gameStateHandler.setExtraDrops(!gameStateHandler.isExtraDrops());
        return true;
    }
}
