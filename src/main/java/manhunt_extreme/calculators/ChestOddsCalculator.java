package manhunt_extreme.calculators;

import manhunt_extreme.manhunt_player.ManhuntPlayer;
import manhunt_extreme.manhunt_team.HunterTeam;
import manhunt_extreme.manhunt_team.RunnerTeam;

public class ChestOddsCalculator {

    private GameBalancingCalculator gameBalancingCalculator;

    public ChestOddsCalculator(GameBalancingCalculator gameBalancingCalculator) {
        this.gameBalancingCalculator = gameBalancingCalculator;
    }

    public Double getPlayerChestOdds(ManhuntPlayer manhuntPlayer) {
        Double scoreDifference = gameBalancingCalculator.getTeamScoreDifference();
        boolean huntersHaveBetterOdds = scoreDifference > 0;


        if (manhuntPlayer.getTeam() instanceof HunterTeam) {
            if (huntersHaveBetterOdds) {
                if (scoreDifference > 60) {
                    
                }
            } else {

            }

        } else if (manhuntPlayer.getTeam() instanceof RunnerTeam) {
            if (huntersHaveBetterOdds) {

            } else {

            }

        }
        return 750 -;
    }


}
