package manhunt_extreme;

import manhunt_extreme.manhunt_player.ManhuntPlayer;
import manhunt_extreme.task_manager.TaskManager;
import org.bukkit.Location;

import java.util.HashMap;

public class Game {
    private final GameEngine gameEngine;
    private boolean isRunning;
    private TaskManager taskManager;
    private GameStateHandler gameStateHandler = new GameStateHandler();
    private HashMap<ManhuntPlayer, ManhuntPlayer> targets = new HashMap<>();

    // Nether portals found in the overworld
    private HashMap<ManhuntPlayer, Location> overworldPortals = new HashMap<>();
    // Nether portals found in the nether
    private HashMap<ManhuntPlayer, Location> netherPortals = new HashMap<>();
    // End portal location
    private Location endPortalLocation = null;

    public Game(GameEngine gameEngine, boolean isRunning, TaskManager taskManager) {
        this.taskManager = taskManager;
        this.gameEngine = gameEngine;
        this.isRunning = isRunning;
    }

    public void startGame() {
        setRunning(true);
        taskManager.getGameClock().start();
        taskManager.getHasteHandler().start();
        taskManager.getSupplyDropHandler().start();
        taskManager.getActionBarHandler().start();
        taskManager.getCompassHandler().start();
        //Todo: Start task manager tasks
    }


    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }

    public void setTaskManager(TaskManager taskManager) {
        this.taskManager = taskManager;
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
}
