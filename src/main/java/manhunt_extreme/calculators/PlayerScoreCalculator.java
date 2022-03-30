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

    private final int chestWeight = 30;
    // Runner, Hunter
    private final List<Integer> killWeights = Arrays.asList(10, 10);
    // Runner, Hunter
    private final List<Integer> deathWeights = Arrays.asList(-15, 0);
    private final int starterScore = 10;


    public PlayerScoreCalculator(ManhuntPlayer manhuntPlayer) {
        this.manhuntPlayer = manhuntPlayer;
        this.inventoryCalculator = new InventoryCalculator(manhuntPlayer);
        this.inventoryScore = inventoryCalculator.calculateInventoryScore();
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
                + inventoryScore
                //TODO: ADD THE GAME TIME
        );

    }


}
