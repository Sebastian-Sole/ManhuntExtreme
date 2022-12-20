package commands;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import manhunt_extreme.PluginMain;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClearTeamsCommandTest {

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
    public void testClearTeamsCommand() {
        PlayerMock player = server.addPlayer();
        player.setOp(true);
        ManhuntPlayer manhuntPlayer = new ManhuntPlayer(player);
        plugin.getGameEngine().getRunnersTeam().addPlayer(manhuntPlayer);
        server.execute("clearteams", player);
        Assertions.assertTrue(plugin.getGameEngine().getHunters().isEmpty());
    }

    @Test
    public void testInvalidCommand() {
        PlayerMock player = server.addPlayer();
        player.setOp(true);
        plugin.getGameEngine().setRunning(true);
        server.execute("clearteams", player);
        Assertions.assertEquals("Game is running. Restart a game to change this option.", player.nextMessage());
        plugin.getGameEngine().setRunning(false);
        server.execute("clearteams", player, "invalid");
        Assertions.assertEquals("Illegal format. Use /clearteams", player.nextMessage());
    }


}
