package commands;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import manhunt_extreme.PluginMain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HealthCommandTest {

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
    public void testHealthCommand() {
        PlayerMock player = server.addPlayer();
        player.setOp(true);
        server.execute("health", player, "40");
        Assertions.assertEquals("Health is set to: 40.0", player.nextMessage());
    }

    @Test
    public void testInvalidCommand() {
        PlayerMock player = server.addPlayer();
        player.setOp(true);
        server.execute("health", player, "yes");
        Assertions.assertEquals("Invalid number", player.nextMessage());
        server.execute("health", player, "40", "40");
        Assertions.assertEquals("Invalid format. Use /health <number>", player.nextMessage());
        server.execute("health", player, "5");
        Assertions.assertEquals("Cannot set health under 20", player.nextMessage());
        plugin.getGameEngine().setRunning(true);
        server.execute("health", player, "40");
        Assertions.assertEquals("Game is running. Start a new game to use this command", player.nextMessage());
    }
}
