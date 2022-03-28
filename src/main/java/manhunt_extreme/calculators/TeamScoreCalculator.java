package manhunt_extreme.calculators;

import manhunt_extreme.manhunt_player.ManhuntPlayer;
import manhunt_extreme.manhunt_team.ManhuntTeam;

public class TeamScoreCalculator {

    private final ManhuntTeam manhuntTeam;

    public TeamScoreCalculator(ManhuntTeam manhuntTeam) {
        this.manhuntTeam = manhuntTeam;
    }

    public Double getTeamScore() {
        return manhuntTeam.getPlayerList().stream().mapToDouble(ManhuntPlayer::getPlayerScore).sum();
    }
}
