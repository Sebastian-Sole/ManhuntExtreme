package commands;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import manhunt_extreme.PluginMain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AllHelpCommandTest {
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

    @Test
    public void testAllHelpCommand() {
        PlayerMock player = server.addPlayer();
        player.setOp(true);
        server.execute("allhelp", player);
        Assertions.assertEquals("All help is enabled", player.nextMessage());
    }

    @Test
    public void testInvalidCommand() {
        PlayerMock player = server.addPlayer();
        player.setOp(true);
        server.execute("allhelp", player, "yes");
        Assertions.assertEquals("Illegal format. Use /allhelp", player.nextMessage());
    }
}
