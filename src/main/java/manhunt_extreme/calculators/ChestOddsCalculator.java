package manhunt_extreme.calculators;

import manhunt_extreme.manhunt_player.ManhuntPlayer;
import manhunt_extreme.manhunt_team.HunterTeam;
import manhunt_extreme.manhunt_team.RunnerTeam;
import org.bukkit.Bukkit;

public class ChestOddsCalculator {

    private GameBalancingCalculator gameBalancingCalculator;

    public ChestOddsCalculator(GameBalancingCalculator gameBalancingCalculator) {
        this.gameBalancingCalculator = gameBalancingCalculator;
    }

    public int getPlayerChestOdds(ManhuntPlayer manhuntPlayer) {
        Double scoreDiff = gameBalancingCalculator.getTeamScoreDifference();
        boolean huntersHaveBetterOdds = scoreDiff >= 0;
        // If player is a hunter
        if (manhuntPlayer.getTeam() instanceof HunterTeam) {
            // If the hunters should have better odds
            if (huntersHaveBetterOdds) {
                if (scoreDiff <= 15) {
                    return 450;
                } else if (scoreDiff <= 30) {
                    return 400;
                } else if (scoreDiff <= 60) {
                    return 450;
                } else if (scoreDiff <= 90) {
                    return 300;
                } else if (scoreDiff <= 120) {
                    return 265;
                } else if (scoreDiff <= 150) {
                    return 225;
                } else {
                    return 150;
                }
            }
            // If the runners should have better odds
            else {
                return 600;
            }

        } else if (manhuntPlayer.getTeam() instanceof RunnerTeam) {
            if (huntersHaveBetterOdds) {
                if (scoreDiff <= 15) {
                    return 450;
                } else if (scoreDiff <= 30) {
                    return 500;
                } else if (scoreDiff <= 60) {
                    return 600;
                } else if (scoreDiff <= 90) {
                    return 650;
                } else if (scoreDiff <= 120) {
                    return 750;
                } else if (scoreDiff <= 150) {
                    return 800;
                } else {
                    return 900;
                }
            } else {
                if (scoreDiff <= -100) {
                    return 40;
                } else if (scoreDiff <= -50) {
                    return 90;
                } else if (scoreDiff <= -25) {
                    return 120;
                } else if (scoreDiff < 0) {
                    return 150;
                } else {
                    Bukkit.getLogger().info("Invalid score diff: " + scoreDiff + " Runner broke block and has better odds. Default set to 175.");
                    return 175;
                }
            }

        } else {
            Bukkit.broadcastMessage("Error when creating chest. Shiiii");
            throw new IllegalArgumentException("Chest could not be generated. Player had no team");
        }
    }


}
