package commands;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import manhunt_extreme.GameEngine;
import manhunt_extreme.PluginMain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameRuleCommandTest {

    private ServerMock server;
    private PluginMain plugin;
    private GameEngine gameEngine;

    @BeforeEach
    public void setUp() {
        // Start the mock server
        server = MockBukkit.mock();
        // Load your plugin
        WorldMock worldMock = server.addSimpleWorld("world");
        plugin = MockBukkit.load(PluginMain.class);
        gameEngine = new GameEngine(plugin);
        plugin.setGameEngine(gameEngine);
    }

    @AfterEach
    public void tearDown() {
        // Stop the mock server
        MockBukkit.unmock();
    }

    @Test
    public void testGameRuleCommand() {
        PlayerMock player = server.addPlayer();
        player.setOp(true);
        server.execute("supplydrops", player, "test");
        Assertions.assertEquals("Illegal format. Use /supplydrops", player.nextMessage());
    }

}
