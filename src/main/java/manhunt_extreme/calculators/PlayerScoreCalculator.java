package manhunt_extreme.calculators;

import manhunt_extreme.manhunt_player.ManhuntPlayer;
import manhunt_extreme.manhunt_team.HunterTeam;
import manhunt_extreme.manhunt_team.RunnerTeam;
import manhunt_extreme.task_manager.GameClock;

import java.util.Arrays;
import java.util.List;

public class PlayerScoreCalculator {


    private final ManhuntPlayer manhuntPlayer;
    private final int chestWeight = 20;
    // Runner, Hunter
    private final List<Integer> killWeights = Arrays.asList(10, 10);
    // Runner, Hunter
    private final List<Integer> deathWeights = Arrays.asList(-15, 0);
    private final int starterScore = 10;
    private InventoryCalculator inventoryCalculator;
    private GameClock gameClock;


    public PlayerScoreCalculator(ManhuntPlayer manhuntPlayer, GameClock gameClock) {
        this.manhuntPlayer = manhuntPlayer;
        this.gameClock = gameClock;
        this.inventoryCalculator = new InventoryCalculator(manhuntPlayer);
    }

    /**
     * @return the player's score based on their inventory and their state.
     */
    public double calculatePlayerScore() {
        boolean isHunter;
        if (manhuntPlayer.getTeam() instanceof HunterTeam) {
            isHunter = true;
        } else if (manhuntPlayer.getTeam() instanceof RunnerTeam) {
            isHunter = false;
        } else {
            throw new IllegalArgumentException("Player is neither hunter nor runner. Team: " + manhuntPlayer.getTeam().toString());
        }

        var kills = manhuntPlayer.getKills();
        var deaths = manhuntPlayer.getDeaths();
        var chestsGenerated = manhuntPlayer.getChestsGenerated();

        int i = isHunter ? 1 : 0;
        int deathScore = deathWeights.get(i);
        int killScore = killWeights.get(i);


        return (starterScore
                + (chestsGenerated * chestWeight)
                + (kills * killScore)
                + (deaths * deathScore)
                + inventoryCalculator.calculateInventoryScore()
                + convertTime()
        );

    }

    private int convertTime() {
        var minutes = gameClock.getMinutes();
        if (minutes <= 30) {
            return 0;
        } else {
            return minutes - 30;
        }
    }


}
