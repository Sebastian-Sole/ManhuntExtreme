package manhunt_extreme;

import manhunt_extreme.calculators.GameBalancingCalculator;
import manhunt_extreme.calculators.PlayerScoreCalculator;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import manhunt_extreme.manhunt_team.HunterTeam;
import manhunt_extreme.manhunt_team.RunnerTeam;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class GameEngine {

    private final GameBalancingCalculator gameBalancingCalculator = new GameBalancingCalculator(this);
    // Fields
    private Game game;
    private ArrayList<ManhuntPlayer> manhuntPlayers = new ArrayList<>();
    private HunterTeam hunters = new HunterTeam();
    private RunnerTeam runners = new RunnerTeam();
    private World world;

    // Constructor
    public GameEngine() {
        convertAllPlayersToManhuntPlayers();
        initializePlayerScores();
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
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
}
