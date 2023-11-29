package task_manager;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import manhunt_extreme.PluginMain;
import manhunt_extreme.calculators.PlayerScoreCalculator;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupplyDropTest {

    private ServerMock server;
    private PluginMain plugin;
    private WorldMock worldMock;

    @BeforeEach
    public void setUp() {
        // Start the mock server
        server = MockBukkit.mock();
        // Load your plugin
        worldMock = server.addSimpleWorld("world");
        plugin = MockBukkit.load(PluginMain.class);
        server.getPluginManager().enablePlugin(plugin);
    }

    @AfterEach
    public void tearDown() {
        // Stop the mock server
        MockBukkit.unmock();
    }

    @Test
    public void testSupplyDrop() {
        PlayerMock playerRunner = server.addPlayer();
        ManhuntPlayer manhuntRunner = plugin.getGameEngine().getManhuntPlayerFromPlayer(playerRunner);
        manhuntRunner.setPlayerScoreCalculator(new PlayerScoreCalculator(manhuntRunner, plugin.getGameEngine().getTaskManager().getGameClock()));
        plugin.getGameEngine().getRunnersTeam().addPlayer(manhuntRunner);

        PlayerMock playerHunter = server.addPlayer();
        ManhuntPlayer manhuntHunter = plugin.getGameEngine().getManhuntPlayerFromPlayer(playerHunter);
        manhuntHunter.setPlayerScoreCalculator(new PlayerScoreCalculator(manhuntHunter, plugin.getGameEngine().getTaskManager().getGameClock()));
        plugin.getGameEngine().getHuntersTeam().addPlayer(manhuntHunter);

        playerRunner.setOp(true);

        server.execute("start", manhuntRunner.getPlayer());

        playerHunter.getPlayer().teleport(new Location(worldMock, 0, 60, 0));
        playerRunner.getPlayer().teleport(new Location(worldMock, 400, 60, 400));

        server.getScheduler().performTicks(30001L);

        Location location = new Location(worldMock, 200, 60, 200);

        assertEquals(location.getBlock().getType(), Material.CHEST);

    }
}
