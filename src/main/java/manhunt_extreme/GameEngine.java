package manhunt_extreme;

import manhunt_extreme.calculators.GameBalancingCalculator;
import manhunt_extreme.calculators.PlayerScoreCalculator;
import manhunt_extreme.chest_generator.ChestGenerator;
import manhunt_extreme.commands.UserInput;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import manhunt_extreme.manhunt_team.HunterTeam;
import manhunt_extreme.manhunt_team.RunnerTeam;
import manhunt_extreme.task_manager.TaskManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.logging.Logger;

public class GameEngine {

    private final GameBalancingCalculator gameBalancingCalculator = new GameBalancingCalculator(this);
    // Fields
    private Game game;
    private ArrayList<ManhuntPlayer> manhuntPlayers = new ArrayList<>();
    private HunterTeam hunters = new HunterTeam();
    private RunnerTeam runners = new RunnerTeam();
    private World world;
    private TaskManager taskManager;
    private Logger logger;
    private UserInput commands;
    private ChestGenerator chestGenerator;

    // Constructor
    public GameEngine(TaskManager taskManager) {
        convertAllPlayersToManhuntPlayers();
        initializePlayerScores();
        this.taskManager = taskManager;
        this.chestGenerator = new ChestGenerator(this);
        this.game = new Game(this, false, taskManager);
    }


    private void initializePlayerScores() {
        for (ManhuntPlayer manhuntPlayer : manhuntPlayers) {
            manhuntPlayer.setPlayerScore(new PlayerScoreCalculator(manhuntPlayer, game.getTaskManager().getGameClock()).calculatePlayerScore());
        }
    }

    // Methods
    public ManhuntPlayer getManhuntPlayerFromPlayer(Player player) {
        return manhuntPlayers.stream().filter(manhuntPlayer -> manhuntPlayer.getPlayer().equals(player)).findFirst().orElseThrow();
    }

    private void convertAllPlayersToManhuntPlayers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            ManhuntPlayer manhuntPlayer = new ManhuntPlayer(player);
            if (!manhuntPlayers.contains(manhuntPlayer)) {
                manhuntPlayers.add(manhuntPlayer);
            }
        }
    }

    // Getters and Setters

    public ArrayList<ManhuntPlayer> getManhuntPlayers() {
        return manhuntPlayers;
    }

    public void setManhuntPlayers(ArrayList<ManhuntPlayer> manhuntPlayers) {
        this.manhuntPlayers = manhuntPlayers;
    }

    public HunterTeam getHuntersTeam() {
        return hunters;
    }

    public RunnerTeam getRunnersTeam() {
        return runners;
    }

    public ArrayList<ManhuntPlayer> getHunters() {
        return hunters.getPlayerList();
    }

    public ArrayList<ManhuntPlayer> getRunners() {
        return runners.getPlayerList();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public GameBalancingCalculator getGameBalancingCalculator() {
        return gameBalancingCalculator;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }

    public void setTaskManager(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public UserInput getCommands() {
        return commands;
    }

    public void setCommands(UserInput commands) {
        this.commands = commands;
    }

    public ChestGenerator getChestGenerator() {
        return chestGenerator;
    }

    public void setChestGenerator(ChestGenerator chestGenerator) {
        this.chestGenerator = chestGenerator;
    }
}
