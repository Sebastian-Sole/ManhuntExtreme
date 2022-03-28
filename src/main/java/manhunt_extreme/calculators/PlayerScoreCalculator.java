package manhunt_extreme.calculators;

import manhunt_extreme.manhunt_player.ManhuntPlayer;
import manhunt_extreme.manhunt_team.HunterTeam;
import manhunt_extreme.manhunt_team.RunnerTeam;

import java.util.Arrays;
import java.util.List;

public class PlayerScoreCalculator {


    private final ManhuntPlayer manhuntPlayer;
    private final InventoryCalculator inventoryCalculator;
    private final Double inventoryScore;

    private final int chestWeight = 5;
    // Runner, Hunter
    private final List<Integer> killWeights = Arrays.asList(10, 15);
    // Runner, Hunter
    private final List<Integer> deathWeights = Arrays.asList(-10, 0);
    private final int starterScore = 10;


    public PlayerScoreCalculator(ManhuntPlayer manhuntPlayer) {
        this.manhuntPlayer = manhuntPlayer;
        this.inventoryCalculator = new InventoryCalculator(manhuntPlayer);
        this.inventoryScore = inventoryCalculator.calculateInventoryScore();
    }

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
                + inventoryScore
        );

    }


}
