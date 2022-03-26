package manhunt_extreme.commands;

import manhunt_extreme.GameEngine;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class UserInput implements CommandExecutor {

    GameEngine gameEngine;

    public UserInput(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player senderPlayer = (Player) sender;
        ManhuntPlayer manhuntPlayer = gameEngine.playerToManhuntPlayer(senderPlayer);
        switch (label) {
            case "hunter" -> {
                new HunterCommand(manhuntPlayer, args);
                return true;
            }
            case "runner" -> {
                new RunnerCommand(manhuntPlayer, args);
                return true;
            }
            case "start" -> {
                new StartCommand(manhuntPlayer, args);
                return true;
            }
            case "end" -> {
                new EndCommand(manhuntPlayer, args);
                return true;
            }
            case "compass" -> {
                new CompassCommand(manhuntPlayer, args);
                return true;
            }
            case "setheadstart" -> {
                new SetHeadStartCommand(manhuntPlayer, args);
                return true;
            }
            case "runnerhelp" -> {
                new RunnerHelpCommand(manhuntPlayer, args);
                return true;
            }
            case "hunterhelp" -> {
                new HunterHelpCommand(manhuntPlayer, args);
                return true;
            }
            case "extradrops" -> {
                new ExtraDropsCommand(manhuntPlayer, args);
                return true;
            }
            case "hasteboost" -> {
                new HasteBoostCommand(manhuntPlayer, args);
                return true;
            }
            case "allhelp" -> {
                new AllHelpCommand(manhuntPlayer, args);
                return true;
            }
            case "cutclean" -> {
                new CutCleanCommand(manhuntPlayer, args);
                return true;
            }
            case "pause" -> {
                new PauseCommand(manhuntPlayer, args);
                return true;
            }
            case "unpause" -> {
                new UnpauseCommand(manhuntPlayer, args);
                return true;
            }
            case "health" -> {
                new HealthCommand(manhuntPlayer, args);
                return true;
            }
            case "supplydrops" -> {
                new SupplyDropsCommand(manhuntPlayer, args);
                return true;
            }
        }

        return false;
    }

}
