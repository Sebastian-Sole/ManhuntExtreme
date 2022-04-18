package manhunt_extreme.commands;

import manhunt_extreme.Game;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;

public class HunterHelpCommand extends GameRuleCommand {

    public HunterHelpCommand(ManhuntPlayer manhuntPlayer, String[] args, Game game, String commandCall) {
        super(manhuntPlayer, args, game, commandCall);
    }

    @Override
    public boolean execute() {
        if (isIllegalCommand()) {
            return true;
        }
        gameStateHandler.setHunterHelp(gameStateHandler.isHunterHelp());
        Bukkit.broadcastMessage("Hunter help is: " + gameStateHandler.isHunterHelp());

        return true;
    }
}
