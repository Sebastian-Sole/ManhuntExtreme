package manhunt_extreme.commands;

import manhunt_extreme.GameEngine;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserInput implements CommandExecutor {

    private final String[] registeredCommands = {
            "hunter",
            "runner",
            "spectator",
            "clearteams",
            "start",
            "end",
            "compass",
            "chestgenerate",
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

    private final GameEngine gameEngine;


    public UserInput(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
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
                return new StartCommand(manhuntPlayer, args, gameEngine).execute();
            }
            case "end" -> {
                return new EndCommand(manhuntPlayer, args, gameEngine).execute();
            }
            case "compass" -> {
                return new CompassCommand(manhuntPlayer, args, gameEngine).execute();
            }
            case "chestgenerate" -> {
                return new ChestGenerateCommand(manhuntPlayer, args, gameEngine.getGame(), label).execute();
            }
            case "setheadstart" -> {
                return new SetHeadStartCommand(manhuntPlayer, args, gameEngine).execute();
            }
            case "runnerhelp" -> {
                return new RunnerHelpCommand(manhuntPlayer, args, gameEngine.getGame(), label).execute();
            }
            case "hunterhelp" -> {
                return new HunterHelpCommand(manhuntPlayer, args, gameEngine.getGame(), label).execute();
            }
            case "extradrops" -> {
                return new ExtraDropsCommand(manhuntPlayer, args, gameEngine.getGame(), label).execute();
            }
            case "hasteboost" -> {
                return new HasteBoostCommand(manhuntPlayer, args, gameEngine.getGame(), label).execute();
            }
            case "allhelp" -> {
                return new AllHelpCommand(manhuntPlayer, args, gameEngine.getGame(), label).execute();
            }
            case "cutclean" -> {
                return new CutCleanCommand(manhuntPlayer, args, gameEngine.getGame(), label).execute();
            }
            case "pause" -> {
                return new PauseCommand(manhuntPlayer, args, gameEngine.getGame(), label).execute();
            }
            case "unpause" -> {
                return new UnpauseCommand(manhuntPlayer, args, gameEngine.getGame(), label).execute();
            }
            case "health" -> {
                return new HealthCommand(manhuntPlayer, args, gameEngine.getGame()).execute();
            }
            case "supplydrops" -> {
                return new SupplyDropsCommand(manhuntPlayer, args, gameEngine.getGame(), label).execute();
            }
            case "clearteams" -> {
                return new ClearTeamsCommand(manhuntPlayer, args, gameEngine).execute();
            }
        }

        return false;
    }

    public List<String> getCompletions(String[] args, List<String> existingCompletions) {
        switch (args[0]) {
            case "/hunter":
            case "/runner":
            case "/spectator": {
                return Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).collect(Collectors.toList());
            }
            case "/start":
            case "/end":
            case "/chestgenerate":
            case "/compass":
            case "/clearteams":
            case "/hasteboost":
            case "/allhelp":
            case "/cutclean":
            case "/pause":
            case "/unpause":
            case "/health":
            case "/supplydrops":
            case "/extradrops":
                return new ArrayList<String>();
            case "/setheadstart": {
                return new ArrayList<String>() {
                    {
                        add("0");
                        add("30");
                        add("60");
                    }
                };
            }
            default:
                return existingCompletions;

        }
    }

    public String[] getRegisteredCommands() {
        return registeredCommands;
    }
}
