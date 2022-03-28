package manhunt_extreme.calculators;

import manhunt_extreme.GameEngine;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import manhunt_extreme.manhunt_team.ManhuntTeam;

public class GameBalancingCalculator {

    private final GameEngine gameEngine;

    public GameBalancingCalculator(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public double calculatePlayerScore(ManhuntPlayer manhuntPlayer) {
        PlayerScoreCalculator playerScoreCalculator = new PlayerScoreCalculator(manhuntPlayer);
//        return playerScoreCalculator.getScore();
        return 0;
    }

    public double calculateTeamScore(ManhuntTeam manhuntTeam) {
        TeamScoreCalculator teamScoreCalculator = new TeamScoreCalculator(manhuntTeam);
        return 0;
    }

}
