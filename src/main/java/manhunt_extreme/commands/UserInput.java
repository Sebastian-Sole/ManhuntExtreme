package manhunt_extreme.commands;

import manhunt_extreme.GameEngine;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UserInput implements CommandExecutor {

    public static final String[] registeredCommands = {
            "hunter",
            "runner",
            "spectator",
            "clearteams",
            "start",
            "end",
            "compass",
            "setheadstart",
            "runnerhelp",
            "hunterhelp",
            "extradrops",
            "chestgenerate",
            "hasteboost",
            "allhelp",
            "cutclean",
            "pause",
            "unpause",
            "health",
            "supplydrops"
    };
    
    GameEngine gameEngine;

    public UserInput(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player senderPlayer = (Player) sender;
        ManhuntPlayer manhuntPlayer = gameEngine.getManhuntPlayerFromPlayer(senderPlayer);
        switch (label) {
            case "hunter" -> {
                new HunterCommand(manhuntPlayer, args, gameEngine);
                return true;
            }
            case "runner" -> {
                new RunnerCommand(manhuntPlayer, args, gameEngine);
                return true;
            }
            case "start" -> {
                new StartCommand(manhuntPlayer, args, gameEngine);
                return true;
            }
            case "end" -> {
                new EndCommand(manhuntPlayer, args, gameEngine);
                return true;
            }
            case "compass" -> {
                new CompassCommand(manhuntPlayer, args, gameEngine);
                return true;
            }
            case "setheadstart" -> {
                new SetHeadStartCommand(manhuntPlayer, args, gameEngine);
                return true;
            }
            case "runnerhelp" -> {
                new RunnerHelpCommand(manhuntPlayer, args, gameEngine);
                return true;
            }
            case "hunterhelp" -> {
                new HunterHelpCommand(manhuntPlayer, args, gameEngine);
                return true;
            }
            case "extradrops" -> {
                new ExtraDropsCommand(manhuntPlayer, args, gameEngine);
                return true;
            }
            case "hasteboost" -> {
                new HasteBoostCommand(manhuntPlayer, args, gameEngine);
                return true;
            }
            case "allhelp" -> {
                new AllHelpCommand(manhuntPlayer, args, gameEngine);
                return true;
            }
            case "cutclean" -> {
                new CutCleanCommand(manhuntPlayer, args, gameEngine);
                return true;
            }
            case "pause" -> {
                new PauseCommand(manhuntPlayer, args, gameEngine);
                return true;
            }
            case "unpause" -> {
                new UnpauseCommand(manhuntPlayer, args, gameEngine);
                return true;
            }
            case "health" -> {
                new HealthCommand(manhuntPlayer, args, gameEngine);
                return true;
            }
            case "supplydrops" -> {
                new SupplyDropsCommand(manhuntPlayer, args, gameEngine);
                return true;
            }
        }

        return false;
    }


}
