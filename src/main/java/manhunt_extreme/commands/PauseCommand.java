package manhunt_extreme.commands;

import manhunt_extreme.GameEngine;
import manhunt_extreme.manhunt_player.ManhuntPlayer;

public class PauseCommand extends GameRuleCommand {

    public PauseCommand(ManhuntPlayer manhuntPlayer, String[] args, GameEngine gameEngine, String commandCall) {
        super(manhuntPlayer, args, gameEngine, commandCall);
    }

    @Override
    public boolean execute() {
//        if (isIllegalCommand()){
//            return true;
//        }
//      //TODO: Pause logic
        return true;
    }
}
