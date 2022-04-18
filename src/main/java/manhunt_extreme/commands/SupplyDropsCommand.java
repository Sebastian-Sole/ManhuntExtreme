package manhunt_extreme.commands;

import manhunt_extreme.GameEngine;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;

public class SupplyDropsCommand extends GameRuleCommand {
    public SupplyDropsCommand(ManhuntPlayer manhuntPlayer, String[] args, GameEngine gameEngine, String commandCall) {
        super(manhuntPlayer, args, gameEngine, commandCall);
    }

    @Override
    public boolean execute() {
        if (isIllegalCommand()) {
            return true;
        }
        gameStateHandler.setSupplyDrops(true);
        Bukkit.broadcastMessage("Supply drops is: " + gameStateHandler.isSupplyDrops());

        return true;
    }
}
