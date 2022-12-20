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

public class HunterCommandTest {

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
        PlayerMock player = server.addPlayer();
        player.setOp(true);
        ManhuntPlayer manhuntPlayer = new ManhuntPlayer(player);
        plugin.getGameEngine().getManhuntPlayers().add(manhuntPlayer);
        server.execute("hunter", player, player.getName());
        Assertions.assertEquals(player.getName() + " is now a " + ChatColor.RED + ChatColor.BOLD + "HUNTER!", player.nextMessage());
    }

    @Test
    public void testSwtichTeams() {
        PlayerMock player = server.addPlayer();
        player.setOp(true);
        ManhuntPlayer manhuntPlayer = new ManhuntPlayer(player);
        plugin.getGameEngine().getManhuntPlayers().add(manhuntPlayer);
        plugin.getGameEngine().getRunnersTeam().addPlayer(manhuntPlayer);
        server.execute("hunter", player, player.getName());
        Assertions.assertEquals(player.getName() + " is now a " + ChatColor.RED + ChatColor.BOLD + "HUNTER!", player.nextMessage());
    }

    @Test
    public void testInvalidCommand() {
        PlayerMock player = server.addPlayer();
        player.setOp(true);
        ManhuntPlayer manhuntPlayer = new ManhuntPlayer(player);
        plugin.getGameEngine().getManhuntPlayers().add(manhuntPlayer);
        plugin.getGameEngine().getHuntersTeam().addPlayer(manhuntPlayer);
        server.execute("hunter", player);
        Assertions.assertEquals("Invalid format. Use /runner <playername>", player.nextMessage());
        server.execute("hunter", player, "hello");
        Assertions.assertEquals("Player is not online", player.nextMessage());
        server.execute("hunter", player, player.getName());
        Assertions.assertEquals("Target is already on this team", player.nextMessage());

    }
}
