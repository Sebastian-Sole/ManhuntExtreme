package manhunt_player;

import manhunt_extreme.manhunt_player.ManhuntPlayer;
import manhunt_extreme.manhunt_team.HunterTeam;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ManhuntPlayerTest {

    ManhuntPlayer manhuntPlayer;

    @BeforeEach
    public void setUp() {
        Player player = Mockito.mock(Player.class);
        manhuntPlayer = new ManhuntPlayer(player);
    }

    @Test
    public void testAddKill() {
        manhuntPlayer.addKill();
        assert manhuntPlayer.getKills() == 1;
    }

    @Test
    public void testAddDeath() {
        HunterTeam hunterTeam = new HunterTeam();
        hunterTeam.addPlayer(manhuntPlayer);
        manhuntPlayer.addDeath();
        assert manhuntPlayer.getDeaths() == 1;
        assert hunterTeam.getPlayerDeaths().get(manhuntPlayer) == 1;
    }

}
