package manhunt_extreme;

import manhunt_extreme.manhunt_player.ManhuntPlayer;
import manhunt_extreme.task_manager.TaskManager;

import java.util.HashMap;

public class Game {
    private final GameEngine gameEngine;
    private boolean isRunning;
    private TaskManager taskManager = new TaskManager();
    private GameStateHandler gameStateHandler;

    private HashMap<ManhuntPlayer, String> targets = new HashMap<>();


    public Game(GameEngine gameEngine, boolean isRunning) {
        this.gameEngine = gameEngine;
        this.isRunning = isRunning;
    }

    public void startGame() {
        setRunning(true);
        taskManager.getGameClock().start();
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

    public HashMap<ManhuntPlayer, String> getTargets() {
        return targets;
    }

    public void setTargets(HashMap<ManhuntPlayer, String> targets) {
        this.targets = targets;
    }
}
