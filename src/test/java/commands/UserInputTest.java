package commands;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import manhunt_extreme.PluginMain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class UserInputTest {

    private ServerMock server;
    private PluginMain plugin;

    @BeforeEach
    public void setUp() {
//        // Start the mock server
        server = MockBukkit.mock();
//        // Load your plugin
        server.addSimpleWorld("world");
        plugin = MockBukkit.load(PluginMain.class);
        plugin.onEnable();
    }

    @AfterEach
    public void tearDown() {
        // Stop the mock server
        MockBukkit.unmock();
    }

//    @Test
//    public void testRunnerCommand() {
//        plugin.getGameEngine().getCommands().getCompletions("/hun", )
//    }
}
