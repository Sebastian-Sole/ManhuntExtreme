package task_manager;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import manhunt_extreme.PluginMain;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SupplyDropTest {

    private ServerMock server;
    private PluginMain plugin;

    @BeforeEach
    public void setUp() {
        // Start the mock server
        server = MockBukkit.mock();
        // Load your plugin
        WorldMock worldMock = server.addSimpleWorld("world");
        plugin = MockBukkit.load(PluginMain.class);
        server.setPlayers(2);
        ManhuntPlayer runner = new ManhuntPlayer(server.getPlayer(0));
        ManhuntPlayer hunter = new ManhuntPlayer(server.getPlayer(1));
        plugin.getGameEngine().getRunnersTeam().addPlayer(runner);
        plugin.getGameEngine().getHuntersTeam().addPlayer(hunter);
        server.getPlayer(0).setLocation(new Location(worldMock, 100, 100, 100));
        server.getPlayer(1).setLocation(new Location(worldMock, 50, 50, 50));
    }

    @AfterEach
    public void tearDown() {
        // Stop the mock server
        MockBukkit.unmock();
    }

    @Test
    public void testValidDrop() {
        plugin.getGameEngine().getTaskManager().getSupplyDropHandler().start();
        server.getScheduler().performTicks(30001L);
        assert plugin.getGameEngine().getWorld().getBlockAt(75, 75, 75).getType().equals(Material.CHEST);
    }
}
