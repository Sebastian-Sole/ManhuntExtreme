package commands;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import manhunt_extreme.PluginMain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HasteBoostCommandTest {

    private ServerMock server;
    private PluginMain plugin;

    @BeforeEach
    public void setUp() {
//        // Start the mock server
        server = MockBukkit.mock();
//        // Load your plugin
        WorldMock worldMock = server.addSimpleWorld("world");
        plugin = MockBukkit.load(PluginMain.class);
        plugin.onEnable();
    }

    @AfterEach
    public void tearDown() {
        // Stop the mock server
        MockBukkit.unmock();
    }

    @Test
    public void testHasteBoostCommand() {
        PlayerMock player = server.addPlayer();
        player.setOp(true);
        boolean startingValue = plugin.getGameEngine().getGameStateHandler().isHasteBoost();
        server.execute("hasteBoost", player);
        Assertions.assertEquals(player.nextMessage(), "Haste boost is set to: " + !startingValue);
    }

    @Test
    public void testInvalidCommand() {
        PlayerMock player = server.addPlayer();
        player.setOp(true);
        server.execute("hasteboost", player, "invalid");
        Assertions.assertEquals(player.nextMessage(), "Illegal format. Use /hasteboost");
    }
}
