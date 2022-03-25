package manhunt_extreme;

import manhunt_extreme.manhunt_team.HunterTeam;
import manhunt_extreme.manhunt_team.RunnerTeam;

public class Game {

    private boolean isRunning;
    private HunterTeam hunters = new HunterTeam();
    private RunnerTeam runners = new RunnerTeam();


    public Game(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void start() {
        setRunning(true);
    }

    public void end() {
        setRunning(false);
    }


    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
