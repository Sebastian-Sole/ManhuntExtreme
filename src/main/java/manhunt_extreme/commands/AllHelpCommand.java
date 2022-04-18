package manhunt_extreme.commands;

import manhunt_extreme.Game;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;

public class AllHelpCommand extends GameRuleCommand {

    public AllHelpCommand(ManhuntPlayer manhuntPlayer, String[] args, Game game, String commandCall) {
        super(manhuntPlayer, args, game, commandCall);
    }

    @Override
    public boolean execute() {
        if (isIllegalCommand()) {
            return true;
        }
        gameStateHandler.setRunnerHelp(true);
        gameStateHandler.setHunterHelp(true);
        gameStateHandler.setHeadStart(60);
        gameStateHandler.setHasteBoost(true);
        gameStateHandler.setExtraDrops(true);
        gameStateHandler.setHealth(40.0);
        gameStateHandler.setChestGenerate(true);
        gameStateHandler.setSupplyDrops(true);
        gameStateHandler.setCutClean(true);
        Bukkit.broadcastMessage("All help is true");
        return true;
    }
}
