package manhunt_extreme.commands;

import manhunt_extreme.GameEngine;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;

public class JammerCommand extends GameRuleCommand {


    public JammerCommand(ManhuntPlayer manhuntPlayer, String[] args, GameEngine gameEngine, String commandCall) {
        super(manhuntPlayer, args, gameEngine, commandCall);
    }

    @Override
    public boolean execute() {
        if (!isIllegalCommand()) {
            gameStateHandler.setAllowJamming(!gameStateHandler.isAllowJamming());
            Bukkit.broadcastMessage("Jamming is set to: " + gameStateHandler.isAllowJamming());
        }
        return true;
    }
}
