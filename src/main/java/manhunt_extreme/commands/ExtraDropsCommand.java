package manhunt_extreme.commands;

import manhunt_extreme.Game;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;

public class ExtraDropsCommand extends GameRuleCommand {

    public ExtraDropsCommand(ManhuntPlayer manhuntPlayer, String[] args, Game game, String commandLabel) {
        super(manhuntPlayer, args, game, commandLabel);
    }

    @Override
    public boolean execute() {
        if (isIllegalCommand()) {
            return true;
        }
        gameStateHandler.setExtraDrops(!gameStateHandler.isExtraDrops());
        Bukkit.broadcastMessage("Extra drops is: " + gameStateHandler.isExtraDrops());
        return true;
    }
}
