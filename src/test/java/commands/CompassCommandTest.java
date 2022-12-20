package commands;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import manhunt_extreme.PluginMain;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompassCommandTest {

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
    public void testCompassCommand() {
        PlayerMock player = server.addPlayer();
        player.setOp(true);
        server.execute("compass", player);
        Assertions.assertTrue(player.getInventory().contains(new ItemStack(Material.COMPASS)));
    }

    @Test
    public void testInvalidCommand() {
        PlayerMock player = server.addPlayer();
        player.setOp(true);
        server.execute("compass", player, "test");
        Assertions.assertEquals("Illegal format, please use /compass", player.nextMessage());
    }
}
