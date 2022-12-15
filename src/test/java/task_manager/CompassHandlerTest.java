package task_manager;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import manhunt_extreme.PluginMain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompassHandlerTest {


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
    public void testBasicTracking() {
        // Todo: Implement
    }

    @Test
    public void testTrackingAfterNetherPortal() {
        // Todo: Implement
    }

    @Test
    public void testTrackingAfterNetherPortalBack() {
        // Todo: Implement
    }

}
