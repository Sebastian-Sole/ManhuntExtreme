package manhunt_extreme.manhunt_player;

import manhunt_extreme.calculators.PlayerScoreCalculator;
import manhunt_extreme.manhunt_team.ManhuntTeam;
import org.bukkit.entity.Player;

public class ManhuntPlayer {

    final private Player player;
    private double deaths = 0.0;
    private double kills = 0.0;
    private double chestsGenerated = 0.0;
    private ManhuntTeam team;

    private Double playerScore = new PlayerScoreCalculator(this).calculatePlayerScore();

    public ManhuntPlayer(Player player) {
        this.player = player;
    }

    public void addDeath() {
        deaths++;
    }

    public Player getPlayer() {
        return player;
    }

    public double getDeaths() {
        return deaths;
    }

    public void setDeaths(double deaths) {
        this.deaths = deaths;
    }

    public double getKills() {
        return kills;
    }

    public void setKills(double kills) {
        this.kills = kills;
    }

    public double getChestsGenerated() {
        return chestsGenerated;
    }

    public void setChestsGenerated(double chestsGenerated) {
        this.chestsGenerated = chestsGenerated;
    }

    public ManhuntTeam getTeam() {
        return team;
    }

    public void setTeam(ManhuntTeam team) {
        this.team = team;
    }

    public Double getPlayerScore() {
        return playerScore;
    }

}