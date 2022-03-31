package manhunt_extreme.task_manager;

public class TaskManager {

    //Todo: Compass object
    //Todo: BlazeSpawner object
    //Todo: Actionbar object
    //Todo: Supply drop object
    //Todo: Haste object

    //Todo: GameClock
    private GameClock gameClock;

    public TaskManager() {
        this.gameClock = new GameClock();
        //Todo:
    }


    public GameClock getGameClock() {
        return gameClock;
    }

    public void setGameClock(GameClock gameClock) {
        this.gameClock = gameClock;
    }
}
