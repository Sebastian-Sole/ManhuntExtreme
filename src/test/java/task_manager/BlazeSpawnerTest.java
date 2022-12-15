package task_manager;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import manhunt_extreme.PluginMain;
import manhunt_extreme.task_manager.BlazeSpawnerHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BlazeSpawnerTest {

    private ServerMock server;
    private PluginMain plugin;

    @BeforeEach
    public void setUp() {
        // Start the mock server
        server = MockBukkit.mock();
        // Load your plugin
        WorldMock worldMock = server.addSimpleWorld("world");
        WorldMock nether = server.addSimpleWorld("world_nether");
        plugin = MockBukkit.load(PluginMain.class);
    }

    @AfterEach
    public void tearDown() {
        // Stop the mock server
        MockBukkit.unmock();
    }

    @Test
    public void testTimingSpawns() {
        BlazeSpawnerHandler blazeSpawner = plugin.getGameEngine().getTaskManager().getBlazeSpawnerHandler();
        blazeSpawner.start();
        server.getScheduler().performTicks(8399);
        assert blazeSpawner.getSpawnersGenerated() == 0;
        server.getScheduler().performTicks(1);
        assert blazeSpawner.getSpawnersGenerated() == 1;
        server.getScheduler().performTicks(3600);
        assert blazeSpawner.getSpawnersGenerated() == 2;
        server.getScheduler().performTicks(3600);
        assert blazeSpawner.getSpawnersGenerated() == 3;
        server.getScheduler().performTicks(3600);
        assert blazeSpawner.getSpawnersGenerated() == 4;
        server.getScheduler().performTicks(3600);
        assert blazeSpawner.getSpawnersGenerated() == 5;
        server.getScheduler().performTicks(10000);
        assert blazeSpawner.getSpawnersGenerated() == 5;
    }

}
