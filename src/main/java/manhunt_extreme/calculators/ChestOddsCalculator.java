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
                if (scoreDiff <= 50) {
                    return 550;
                } else if (scoreDiff <= 100) {
                    return 500;
                } else if (scoreDiff <= 140) {
                    return 450;
                } else if (scoreDiff <= 180) {
                    return 350;
                } else if (scoreDiff <= 240) {
                    return 275;
                } else if (scoreDiff <= 300) {
                    return 225;
                } else {
                    return 150;
                }
            }
            // If the runners should have better odds
            else {
                return 750;
            }

        } else if (manhuntPlayer.getTeam() instanceof RunnerTeam) {
            if (huntersHaveBetterOdds) {
                if (scoreDiff <= 50) {
                    return 550;
                } else if (scoreDiff <= 100) {
                    return 600;
                } else if (scoreDiff <= 140) {
                    return 650;
                } else if (scoreDiff <= 180) {
                    return 750;
                } else if (scoreDiff <= 240) {
                    return 800;
                } else if (scoreDiff <= 300) {
                    return 850;
                } else {
                    return 1000;
                }
            } else {
                if (scoreDiff <= -100) {
                    return 40;
                } else if (scoreDiff <= -50) {
                    return 115;
                } else if (scoreDiff <= -25) {
                    return 150;
                }
            }

        } else {
            Bukkit.broadcastMessage("Error when creating chest. Shiiii");
            throw new IllegalArgumentException("Chest could not be generated. Player had no team");
        }
        return 0;
    }


}
