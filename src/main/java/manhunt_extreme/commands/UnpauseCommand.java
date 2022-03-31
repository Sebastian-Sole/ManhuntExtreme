package manhunt_extreme.commands;

import manhunt_extreme.Game;
import manhunt_extreme.GameStateHandler;
import manhunt_extreme.manhunt_player.ManhuntPlayer;

public class UnpauseCommand extends GameRuleCommand {


    public UnpauseCommand(ManhuntPlayer manhuntPlayer, String[] args, Game game, GameStateHandler gameStateHandler, String commandCall) {
        super(manhuntPlayer, args, game, gameStateHandler, commandCall);
    }

    @Override
    public boolean execute() {
        if (isIllegalCommand()) {
            return true;
        }
        //TODO: Unpause Logic
        return true;
    }
}
