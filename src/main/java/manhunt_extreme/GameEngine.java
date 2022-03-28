package manhunt_extreme;

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

    // Constructor
    public GameEngine() {
        convertPlayersToManhuntPlayers();
    }


    // Methods
    public ManhuntPlayer playerToManhuntPlayer(Player player) {
        return manhuntPlayers.stream().filter(manhuntPlayer -> manhuntPlayer.getPlayer().equals(player)).findFirst().orElseThrow();
    }

    private void convertPlayersToManhuntPlayers() {
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
}
