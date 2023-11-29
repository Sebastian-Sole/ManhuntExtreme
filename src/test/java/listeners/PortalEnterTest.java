package listeners;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import manhunt_extreme.PluginMain;
import manhunt_extreme.calculators.PlayerScoreCalculator;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import manhunt_extreme.task_manager.CompassHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PortalEnterTest {

    private ServerMock server;
    private PluginMain plugin;
    private ManhuntPlayer manhuntRunner;
    private ManhuntPlayer manhuntHunter;
    private PlayerMock playerRunner;
    private PlayerMock playerHunter;
    private WorldMock netherMock;
    private WorldMock worldMock;
    private WorldMock endMock;
    private CompassHandler compassHandler;
    private Location toNether;

    @BeforeEach
    public void setUp() {
        // Start the mock server
        server = MockBukkit.mock();
        // Load your plugin
        worldMock = server.addSimpleWorld("world");
        netherMock = server.addSimpleWorld("world_nether");
        endMock = server.addSimpleWorld("world_end");
        plugin = MockBukkit.load(PluginMain.class);
        server.getPluginManager().enablePlugin(plugin);
        playerRunner = server.addPlayer();
        playerRunner.setOp(true);
        manhuntRunner = plugin.getGameEngine().getManhuntPlayerFromPlayer(playerRunner);
        manhuntRunner.setPlayerScoreCalculator(new PlayerScoreCalculator(manhuntRunner, plugin.getGameEngine().getTaskManager().getGameClock()));
        plugin.getGameEngine().getRunnersTeam().addPlayer(manhuntRunner);

        playerHunter = server.addPlayer();
        manhuntHunter = plugin.getGameEngine().getManhuntPlayerFromPlayer(playerHunter);
        manhuntHunter.setPlayerScoreCalculator(new PlayerScoreCalculator(manhuntHunter, plugin.getGameEngine().getTaskManager().getGameClock()));
        plugin.getGameEngine().getHuntersTeam().addPlayer(manhuntHunter);

        server.execute("start", playerRunner);

        netherMock.setEnvironment(World.Environment.NETHER);
        endMock.setEnvironment(World.Environment.THE_END);

        plugin.getGameEngine().getTargets().put(manhuntHunter, manhuntRunner);
        compassHandler = new CompassHandler(plugin, plugin.getGameEngine());
        playerHunter.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.COMPASS));
        compassHandler.start();
        toNether = new Location(netherMock, 50, 50, 50);
    }

    @AfterEach
    public void tearDown() {
        // Stop the mock server
        MockBukkit.unmock();
    }

    @Test
    public void testPortalEnter() {
        PlayerPortalEvent event = new PlayerPortalEvent(playerHunter.getPlayer(), playerHunter.getPlayer().getLocation(), toNether, PlayerPortalEvent.TeleportCause.NETHER_PORTAL);
        plugin.getServer().getPluginManager().callEvent(event);

    }
}
