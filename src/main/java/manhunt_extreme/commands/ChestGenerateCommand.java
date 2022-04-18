package manhunt_extreme.commands;

import manhunt_extreme.Game;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;

public class ChestGenerateCommand extends GameRuleCommand {
    public ChestGenerateCommand(ManhuntPlayer manhuntPlayer, String[] args, Game game, String label) {
        super(manhuntPlayer, args, game, label);
    }

    @Override
    public boolean execute() {
        if (isIllegalCommand()) {
            return true;
        }
        gameStateHandler.setChestGenerate(!gameStateHandler.isChestGenerate());
        Bukkit.broadcastMessage("Chest generate is set to: " + gameStateHandler.isChestGenerate());
        return true;
    }
}
