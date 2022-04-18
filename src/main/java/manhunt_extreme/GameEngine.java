package manhunt_extreme;

import manhunt_extreme.calculators.GameBalancingCalculator;
import manhunt_extreme.chest_generator.ChestGenerator;
import manhunt_extreme.commands.UserInput;
import manhunt_extreme.listeners.respawn_handler.RespawnHandler;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import manhunt_extreme.manhunt_team.HunterTeam;
import manhunt_extreme.manhunt_team.RunnerTeam;
import manhunt_extreme.task_manager.TaskManager;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class GameEngine {

    // Fields
    private ArrayList<ManhuntPlayer> manhuntPlayers = new ArrayList<>();
    private HunterTeam hunters = new HunterTeam();
    private RunnerTeam runners = new RunnerTeam();
    private World world;
    private TaskManager taskManager;
    private Logger logger;
    private UserInput commands;
    private ChestGenerator chestGenerator;
    private RespawnHandler respawnHandler;
    private GameStateHandler gameStateHandler = new GameStateHandler();
    private HashMap<ManhuntPlayer, ManhuntPlayer> targets = new HashMap<>();
    private GameBalancingCalculator gameBalancingCalculator = new GameBalancingCalculator(this);

    // Nether portals found in the overworld
    private HashMap<ManhuntPlayer, Location> overworldPortals = new HashMap<>();
    // Nether portals found in the nether
    private HashMap<ManhuntPlayer, Location> netherPortals = new HashMap<>();
    // End portal location
    private Location endPortalLocation = null;

    // Session specific
    private boolean isRunning;
    private PluginMain pluginMain;

    // Constructor
    public GameEngine(PluginMain pluginMain) {
        this.pluginMain = pluginMain;
        this.chestGenerator = new ChestGenerator(this);
        this.respawnHandler = new RespawnHandler(this);
        this.taskManager = new TaskManager(pluginMain, this);
    }

    // Methods
    public ManhuntPlayer getManhuntPlayerFromPlayer(Player player) {
        return manhuntPlayers.stream().filter(manhuntPlayer -> manhuntPlayer.getPlayer().equals(player)).findFirst().orElseThrow();
    }

    public void startGame() {
        setRunning(true);
        this.taskManager.getGameClock().start();
        this.taskManager.getCompassHandler().start();
        this.taskManager.getActionBarHandler().start();
        this.taskManager.getHasteHandler().start();
        this.taskManager.getSupplyDropHandler().start();
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

    public RespawnHandler getRespawnHandler() {
        return respawnHandler;
    }

    public void setRespawnHandler(RespawnHandler respawnHandler) {
        this.respawnHandler = respawnHandler;
    }


    public GameStateHandler getGameStateHandler() {
        return gameStateHandler;
    }

    public void setGameStateHandler(GameStateHandler gameStateHandler) {
        this.gameStateHandler = gameStateHandler;
    }

    public HashMap<ManhuntPlayer, ManhuntPlayer> getTargets() {
        return targets;
    }

    public void setTargets(HashMap<ManhuntPlayer, ManhuntPlayer> targets) {
        this.targets = targets;
    }

    public HashMap<ManhuntPlayer, Location> getOverworldPortals() {
        return overworldPortals;
    }

    public void setOverworldPortals(HashMap<ManhuntPlayer, Location> overworldPortals) {
        this.overworldPortals = overworldPortals;
    }

    public HashMap<ManhuntPlayer, Location> getNetherPortals() {
        return netherPortals;
    }

    public void setNetherPortals(HashMap<ManhuntPlayer, Location> netherPortals) {
        this.netherPortals = netherPortals;
    }

    public Location getEndPortalLocation() {
        return endPortalLocation;
    }

    public void setEndPortalLocation(Location endPortalLocation) {
        this.endPortalLocation = endPortalLocation;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public PluginMain getPluginMain() {
        return pluginMain;
    }
}
