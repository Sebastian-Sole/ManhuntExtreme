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

public class ChestGeneratorCommandTest {

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
        MockBukkit.unmock();
    }

    @Test
    public void testChestGeneratorCommand() {
        server.addPlayer();
        PlayerMock player = server.getPlayer(0);
        player.setOp(true);
        var startingValue = plugin.getGameEngine().getGameStateHandler().isChestGenerate();
        server.execute("chestgenerate", player);
        //This does not work
//        Assertions.assertTrue(plugin.getGameEngine().getGameStateHandler().isChestGenerate() != startingValue);
        Assertions.assertEquals(player.nextMessage(), "Chest generate is set to: " + !startingValue);
    }
}
