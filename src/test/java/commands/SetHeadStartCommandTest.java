package commands;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import manhunt_extreme.PluginMain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetHeadStartCommandTest {

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
    public void testSetHeadStartCommand() {
        PlayerMock player = server.addPlayer();
        player.setOp(true);
        server.execute("setheadstart", player, "55");
        Assertions.assertEquals("Headstart duration set to: 55", player.nextMessage());
    }

    @Test
    public void testInvalidCommand() {
        PlayerMock player = server.addPlayer();
        player.setOp(true);
        server.execute("setheadstart", player, "55 yes", "yes");
        Assertions.assertEquals("Provide a headstart duration as a single, non-negative integer.", player.nextMessage());
        server.execute("setheadstart", player, "-55");
        Assertions.assertEquals("Provide a headstart duration as a single, non-negative integer.", player.nextMessage());
        server.execute("setheadstart", player);
        Assertions.assertEquals("Provide a headstart duration as a single, non-negative integer.", player.nextMessage());
        plugin.getGameEngine().setRunning(true);
        server.execute("setheadstart", player, "55");
        Assertions.assertEquals("Cannot set this after the game has started", player.nextMessage());
    }
}
