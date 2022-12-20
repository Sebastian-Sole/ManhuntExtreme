package commands;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import manhunt_extreme.PluginMain;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.ChatColor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StartCommandTest {

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
    public void testRunnerCommand() {
        server.setPlayers(2);
        PlayerMock runner = server.getPlayer(0);
        runner.setOp(true);
        ManhuntPlayer manhuntRunner = new ManhuntPlayer(runner);

        PlayerMock hunter = server.getPlayer(1);
        ManhuntPlayer manhuntHunter = new ManhuntPlayer(hunter);

        plugin.getGameEngine().getManhuntPlayers().add(manhuntRunner);
        plugin.getGameEngine().getManhuntPlayers().add(manhuntHunter);

        plugin.getGameEngine().getRunnersTeam().addPlayer(manhuntRunner);
        plugin.getGameEngine().getHuntersTeam().addPlayer(manhuntHunter);
        server.execute("start", runner);
        Assertions.assertEquals(runner.nextMessage(), ChatColor.DARK_RED + "Manhunt Started!");
        Assertions.assertEquals(hunter.nextMessage(), ChatColor.DARK_RED + "Manhunt Started!");

        Assertions.assertEquals("Kill the Ender Dragon without being killed by a hunter!", runner.nextMessage());
        Assertions.assertEquals("Kill the runners before they kill the Ender Dragon!", hunter.nextMessage());
    }

    @Test
    public void testInvalidCommand() {
        server.setPlayers(2);
        PlayerMock runner = server.getPlayer(0);
        runner.setOp(true);
        ManhuntPlayer manhuntRunner = new ManhuntPlayer(runner);

        PlayerMock hunter = server.getPlayer(1);
        ManhuntPlayer manhuntHunter = new ManhuntPlayer(hunter);

        plugin.getGameEngine().getManhuntPlayers().add(manhuntRunner);
        plugin.getGameEngine().getManhuntPlayers().add(manhuntHunter);

        server.execute("start", runner);
        Assertions.assertEquals(runner.nextMessage(), "Not enough speedrunners to start");

        plugin.getGameEngine().getRunnersTeam().addPlayer(manhuntRunner);
        plugin.getGameEngine().getHuntersTeam().addPlayer(manhuntHunter);
        server.execute("start", runner, "invalid");
        Assertions.assertEquals(runner.nextMessage(), "Illegal format. Use /start");
        plugin.getGameEngine().setRunning(true);
        server.execute("start", runner);
        Assertions.assertEquals(runner.nextMessage(), "Game is in progress. Use /end to end the game before using /start");

    }
}
