package manhunt_extreme.commands;

import manhunt_extreme.GameEngine;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class RunnerCommand {


    private final ManhuntPlayer manhuntPlayer;
    private final String[] args;
    private final GameEngine gameEngine;

    public RunnerCommand(ManhuntPlayer manhuntPlayer, String[] args, GameEngine gameEngine) {
        this.manhuntPlayer = manhuntPlayer;
        this.args = args;
        this.gameEngine = gameEngine;
    }

    public boolean execute() {
        if (args.length != 1) {
            return true;
        }
        var targetPlayer = Bukkit.getPlayer(args[0]);
        if (targetPlayer == null) {
            manhuntPlayer.getPlayer().sendMessage("Player is not online");
            return true;
        }
        var targetManhuntPlayer = gameEngine.getManhuntPlayerFromPlayer(targetPlayer);
        if (gameEngine.getRunnersTeam().getPlayerList().stream().anyMatch(
                player -> player.getPlayer().getName().equals(targetPlayer.getName())
        )) {
            manhuntPlayer.getPlayer().sendMessage("Target is already on this team");
            return true;
        }
        gameEngine.getHuntersTeam().removePlayer(targetManhuntPlayer);
        gameEngine.getRunnersTeam().addPlayer(targetManhuntPlayer);
        Bukkit.broadcastMessage(manhuntPlayer.getPlayer().getName() + " is now a " + ChatColor.RED + ChatColor.BOLD + "HUNTER!");
        return true;
    }
}
