package manhunt_team;

import manhunt_extreme.manhunt_player.ManhuntPlayer;
import manhunt_extreme.manhunt_team.HunterTeam;
import manhunt_extreme.manhunt_team.RunnerTeam;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class TeamTest {

    HunterTeam hunterTeam;
    ManhuntPlayer manhuntPlayer;

    @BeforeEach
    public void setUp() {
        hunterTeam = new HunterTeam();
        Player player = Mockito.mock(Player.class);
        manhuntPlayer = new ManhuntPlayer(player);
        hunterTeam.addPlayer(manhuntPlayer);
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void testSize() {
        assert hunterTeam.size() == 1;
    }

    @Test
    public void testRemovePlayer() {
        hunterTeam.removePlayer(manhuntPlayer);
        assert hunterTeam.size() == 0;
        assert hunterTeam.getPlayerDeaths().size() == 0;
    }

    @Test
    public void testAddPlayerDeath() {
        hunterTeam.addPlayerDeath(manhuntPlayer);
        assert hunterTeam.getPlayerDeaths().get(manhuntPlayer) == 1;
    }

    @Test
    public void testClearTeam() {
        hunterTeam.clearTeam();
        assert hunterTeam.size() == 0;
        assert hunterTeam.getPlayerDeaths().size() == 0;
    }

    @Test
    public void testGetPlayerList() {
        assert hunterTeam.getPlayerList().size() == 1;
    }

    @Test
    public void testCreateRunnerTeam() {
        RunnerTeam runnerTeam = new RunnerTeam();
        assert runnerTeam.size() == 0;
    }
}
