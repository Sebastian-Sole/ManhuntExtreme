package manhunt_extreme.calculators;

import manhunt_extreme.manhunt_player.ManhuntPlayer;
import manhunt_extreme.manhunt_team.HunterTeam;
import manhunt_extreme.manhunt_team.RunnerTeam;
import org.bukkit.Bukkit;

public class CutCleanCalculator {

    private GameBalancingCalculator gameBalancingCalculator;

    public CutCleanCalculator(GameBalancingCalculator gameBalancingCalculator) {
        this.gameBalancingCalculator = gameBalancingCalculator;
    }

    public double calculateOdds(ManhuntPlayer manhuntPlayer) {
        Double scoreDiff = gameBalancingCalculator.getTeamScoreDifference();
        boolean huntersHaveBetterOdds = scoreDiff >= 0;

        // If hunter breaks block
        if (manhuntPlayer.getTeam() instanceof HunterTeam) {
            if (huntersHaveBetterOdds) {
                return 1;
            } else {
                return 0.3;
            }
        } else if (manhuntPlayer.getTeam() instanceof RunnerTeam) {
            if (huntersHaveBetterOdds) {
                if (scoreDiff <= 15) {
                    return 1;
                } else if (scoreDiff <= 20) {
                    return 0.9;
                } else if (scoreDiff <= 30) {
                    return 0.7;
                } else if (scoreDiff <= 45) {
                    return 0.5;
                } else if (scoreDiff <= 65) {
                    return 0.4;
                } else if (scoreDiff <= 95) {
                    return 0.3;
                } else {
                    return 0.1;
                }

            } else {
                return 1;
            }
        } else {
            Bukkit.broadcastMessage("Player has no team, cannot calculate cut clean odds");
            throw new IllegalArgumentException("Cannot calculate cut clean odds, player has no team");
        }

    }

}
