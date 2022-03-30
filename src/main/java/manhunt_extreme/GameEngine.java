package manhunt_extreme;

import manhunt_extreme.calculators.GameBalancingCalculator;
import manhunt_extreme.calculators.PlayerScoreCalculator;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import manhunt_extreme.manhunt_team.HunterTeam;
import manhunt_extreme.manhunt_team.RunnerTeam;
import manhunt_extreme.task_manager.TaskManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class GameEngine {

    // Fields
    private Game game = new Game(false);
    private TaskManager taskManager = new TaskManager();
    private ArrayList<ManhuntPlayer> manhuntPlayers = new ArrayList<>();

    private HunterTeam hunters = new HunterTeam();
    private RunnerTeam runners = new RunnerTeam();
    private GameBalancingCalculator gameBalancingCalculator = new GameBalancingCalculator(this);

    // Constructor
    public GameEngine() {
        convertAllPlayersToManhuntPlayers();
        initializePlayerScores();
    }

    private void initializePlayerScores() {
        for (ManhuntPlayer manhuntPlayer : manhuntPlayers) {
            manhuntPlayer.setPlayerScore(new PlayerScoreCalculator(manhuntPlayer, taskManager.getGameClock()).calculatePlayerScore());
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

    public ArrayList<ManhuntPlayer> getManhuntPlayers() {
        return manhuntPlayers;
    }

    public void setManhuntPlayers(ArrayList<ManhuntPlayer> manhuntPlayers) {
        this.manhuntPlayers = manhuntPlayers;
    }

    public HunterTeam getHunters() {
        return hunters;
    }

    public void setHunters(HunterTeam hunters) {
        this.hunters = hunters;
    }

    public RunnerTeam getRunners() {
        return runners;
    }

    public void setRunners(RunnerTeam runners) {
        this.runners = runners;
    }

}
