package manhunt_extreme;

import manhunt_extreme.task_manager.TaskManager;

public class GameEngine {

    private Game game = new Game(false);

    private TaskManager taskManager = new TaskManager();

    public GameEngine() {

    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }

    public void setTaskManager(TaskManager taskManager) {
        this.taskManager = taskManager;
    }
}
