package commands;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import manhunt_extreme.PluginMain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class EndCommandTest {
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
    public void testHunterHelpCommand() {
        PlayerMock player = server.addPlayer();
        player.setOp(true);
        var startingValue = plugin.getGameEngine();

        server.execute("end", player);
        Assertions.assertTrue(Objects.requireNonNull(player.nextMessage()).contains("There is no game in progress. Use /start to start a game"));
        plugin.getGameEngine().setRunning(true);
        server.execute("end", player);
        Assertions.assertTrue(Objects.requireNonNull(player.nextMessage()).contains("Manhunt Stopped!"));
        Assertions.assertNotSame(plugin.getGameEngine(), startingValue);
    }
}
