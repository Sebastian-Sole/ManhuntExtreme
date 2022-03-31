package manhunt_extreme;

import manhunt_extreme.calculators.GameBalancingCalculator;
import manhunt_extreme.calculators.PlayerScoreCalculator;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import manhunt_extreme.manhunt_team.HunterTeam;
import manhunt_extreme.manhunt_team.RunnerTeam;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class GameEngine {

    // Fields
    private final Game game;
    private final GameBalancingCalculator gameBalancingCalculator = new GameBalancingCalculator(this);
    private ArrayList<ManhuntPlayer> manhuntPlayers = new ArrayList<>();
    private HunterTeam hunters = new HunterTeam();
    private RunnerTeam runners = new RunnerTeam();

    // Constructor
    public GameEngine() {
        convertAllPlayersToManhuntPlayers();
        initializePlayerScores();
        game = new Game(this, false);
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

    public Game getGame() {
        return game;
    }

    public GameBalancingCalculator getGameBalancingCalculator() {
        return gameBalancingCalculator;
    }
}
