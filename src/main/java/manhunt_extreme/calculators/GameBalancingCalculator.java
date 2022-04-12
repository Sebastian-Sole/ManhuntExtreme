package manhunt_extreme.calculators;

import manhunt_extreme.GameEngine;
import manhunt_extreme.listeners.respawn_handler.RespawnInventoryGenerator;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import manhunt_extreme.manhunt_team.ManhuntTeam;

public class GameBalancingCalculator {

    private final GameEngine gameEngine;
    private Double hunterTeamScore;
    private Double runnerTeamScore;
    private ChestOddsCalculator chestOddsCalculator = new ChestOddsCalculator(this);
    private CutCleanCalculator cutCleanCalculator = new CutCleanCalculator(this);
    private RespawnInventoryGenerator respawnInventoryGenerator = new RespawnInventoryGenerator();

    public GameBalancingCalculator(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.hunterTeamScore = getTeamScore(gameEngine.getHuntersTeam());
        this.runnerTeamScore = getTeamScore(gameEngine.getRunnersTeam());
    }

    public Double getAverageOfAllTeamScores() {
        return (hunterTeamScore * runnerTeamScore) / 2;
    }

    public Double getTeamScore(ManhuntTeam manhuntTeam) {
        return manhuntTeam.getPlayerList().stream().mapToDouble(ManhuntPlayer::getPlayerScore).sum();
    }

    public Double getTeamScoreDifference() {
        return runnerTeamScore - hunterTeamScore;
    }

    //Todo: Individual player score difference
//    public Double playerDifference(ManhuntPlayer manhuntPlayer) {
//        if (manhuntPlayer.getTeam() instanceof HunterTeam) {
//            return manhuntPlayer.getPlayerScore()
//                    - (runnerTeamScore / gameEngine.getRunners().getPlayerList().size());
//        } else if (manhuntPlayer.getTeam() instanceof RunnerTeam) {
//
//        } else {
//            throw new IllegalArgumentException("Manhunt player has no team. ManhuntTeam: " + manhuntPlayer.getTeam().toString());
//        }
//    }
    public Double getPlayerScoreDifference() {
        Double runnerTeamAverageScore = runnerTeamScore / gameEngine.getRunnersTeam().getPlayerList().size();
        Double hunterTeamAverageScore = hunterTeamScore / gameEngine.getHuntersTeam().getPlayerList().size();
        return runnerTeamAverageScore - hunterTeamAverageScore;
    }

    public ChestOddsCalculator getChestOddsCalculator() {
        return chestOddsCalculator;
    }

    public void setChestOddsCalculator(ChestOddsCalculator chestOddsCalculator) {
        this.chestOddsCalculator = chestOddsCalculator;
    }

    public CutCleanCalculator getCutCleanCalculator() {
        return cutCleanCalculator;
    }

    public void setCutCleanCalculator(CutCleanCalculator cutCleanCalculator) {
        this.cutCleanCalculator = cutCleanCalculator;
    }

    public RespawnInventoryGenerator getRespawnInventoryGenerator() {
        return respawnInventoryGenerator;
    }

    public void setRespawnInventoryGenerator(RespawnInventoryGenerator respawnInventoryGenerator) {
        this.respawnInventoryGenerator = respawnInventoryGenerator;
    }
}
