package manhunt_extreme.commands;

import manhunt_extreme.GameEngine;
import manhunt_extreme.GameStateHandler;
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
    GameStateHandler gameStateHandler;
    

    public UserInput(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        gameStateHandler = new GameStateHandler();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player senderPlayer = (Player) sender;
        ManhuntPlayer manhuntPlayer = gameEngine.getManhuntPlayerFromPlayer(senderPlayer);
        switch (label) {
            case "hunter" -> {
                return new HunterCommand(manhuntPlayer, args, gameEngine).execute();
            }
            case "runner" -> {
                return new RunnerCommand(manhuntPlayer, args, gameEngine).execute();
            }
            case "start" -> {

                // Create new Game instance with gameStateHandler and running

                // Set gameEngine's game

                new StartCommand(manhuntPlayer, args, gameEngine);
                return true;
            }
            case "end" -> {
                new EndCommand(manhuntPlayer, args, gameEngine);
                return true;
            }
            case "compass" -> {
                return new CompassCommand(manhuntPlayer, args, gameEngine).execute();
            }
            case "setheadstart" -> {
                return new SetHeadStartCommand(manhuntPlayer, args, gameEngine.getGame(), gameStateHandler).execute();
            }
            case "runnerhelp" -> {
                return new RunnerHelpCommand(manhuntPlayer, args, gameEngine.getGame(), gameStateHandler, label).execute();
            }
            case "hunterhelp" -> {
                return new HunterHelpCommand(manhuntPlayer, args, gameEngine.getGame(), gameStateHandler, label).execute();
            }
            case "extradrops" -> {
                return new ExtraDropsCommand(manhuntPlayer, args, gameEngine.getGame(), gameStateHandler, label).execute();
            }
            case "hasteboost" -> {
                return new HasteBoostCommand(manhuntPlayer, args, gameEngine.getGame(), gameStateHandler, label).execute();
            }
            case "allhelp" -> {
                return new AllHelpCommand(manhuntPlayer, args, gameEngine.getGame(), gameStateHandler, label).execute();
            }
            case "cutclean" -> {
                return new CutCleanCommand(manhuntPlayer, args, gameEngine.getGame(), gameStateHandler, label).execute();
            }
            case "pause" -> {
                return new PauseCommand(manhuntPlayer, args, gameEngine.getGame(), gameStateHandler, label).execute();
            }
            case "unpause" -> {
                return new UnpauseCommand(manhuntPlayer, args, gameEngine.getGame(), gameStateHandler, label).execute();
            }
            case "health" -> {
                return new HealthCommand(manhuntPlayer, args, gameEngine.getGame(), gameStateHandler).execute();
            }
            case "supplydrops" -> {
                return new SupplyDropsCommand(manhuntPlayer, args, gameEngine.getGame(), gameStateHandler, label).execute();
            }
            case "clearteams" -> {
                return new ClearTeamsCommand(manhuntPlayer, args, gameEngine).execute();
            }
        }

        return false;
    }


}
