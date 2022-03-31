package manhunt_extreme;

import manhunt_extreme.task_manager.TaskManager;

public class Game {
    private final GameEngine gameEngine;
    private boolean isRunning;
    private TaskManager taskManager = new TaskManager();


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
}
