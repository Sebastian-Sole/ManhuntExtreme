package task_manager;

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
import org.bukkit.inventory.meta.CompassMeta;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CompassHandlerTest {

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
    public void testBasicTracking() {
        server.getScheduler().performTicks(100L);
        assertEquals(playerHunter.getCompassTarget(), manhuntRunner.getPlayer().getLocation());
        var compass = playerHunter.getInventory().getItemInMainHand();
        CompassMeta meta = (CompassMeta) compass.getItemMeta();
        assertEquals(meta.getLodestone(), manhuntRunner.getPlayer().getLocation());
    }

    @Test
    public void testTrackingAfterNetherPortal() {
        PlayerPortalEvent event = new PlayerPortalEvent(manhuntRunner.getPlayer(), manhuntRunner.getPlayer().getLocation(), toNether, PlayerPortalEvent.TeleportCause.NETHER_PORTAL);
        manhuntRunner.getPlayer().teleport(toNether);
        server.getPluginManager().callEvent(event);

        server.getScheduler().performTicks(100L);

        assertNotEquals(manhuntRunner.getPlayer().getWorld().getEnvironment(), manhuntHunter.getPlayer().getWorld().getEnvironment());
        assertEquals(playerHunter.getCompassTarget(), plugin.getGameEngine().getOverworldPortals().get(manhuntRunner));
        var compass = playerHunter.getInventory().getItemInMainHand();
        CompassMeta meta = (CompassMeta) compass.getItemMeta();
        assertEquals(meta.getLodestone(), plugin.getGameEngine().getOverworldPortals().get(manhuntRunner));
    }

    @Test
    public void testTrackingAfterEndPortal() {
        Location to = new Location(endMock, 50, 50, 50);
        PlayerPortalEvent event = new PlayerPortalEvent(manhuntRunner.getPlayer(), manhuntRunner.getPlayer().getLocation(), to, PlayerPortalEvent.TeleportCause.END_PORTAL);
        manhuntRunner.getPlayer().teleport(to);
        server.getPluginManager().callEvent(event);

        server.getScheduler().performTicks(100L);

        assertNotEquals(manhuntRunner.getPlayer().getWorld().getEnvironment(), manhuntHunter.getPlayer().getWorld().getEnvironment());
        assertEquals(playerHunter.getCompassTarget(), plugin.getGameEngine().getEndPortalLocation());
        var compass = playerHunter.getInventory().getItemInMainHand();
        CompassMeta meta = (CompassMeta) compass.getItemMeta();
        assertEquals(meta.getLodestone(), plugin.getGameEngine().getEndPortalLocation());
    }

    @Test
    public void testTrackingWhenTrackerIsInNether() {
        PlayerPortalEvent runnerEvent = new PlayerPortalEvent(manhuntRunner.getPlayer(), manhuntRunner.getPlayer().getLocation(), toNether, PlayerPortalEvent.TeleportCause.NETHER_PORTAL);
        manhuntRunner.getPlayer().teleport(toNether);
        server.getPluginManager().callEvent(runnerEvent);

        PlayerPortalEvent hunterEvent = new PlayerPortalEvent(manhuntHunter.getPlayer(), manhuntHunter.getPlayer().getLocation(), toNether, PlayerPortalEvent.TeleportCause.NETHER_PORTAL);
        manhuntHunter.getPlayer().teleport(toNether);
        server.getPluginManager().callEvent(hunterEvent);

        Location backTo = worldMock.getSpawnLocation();
        PlayerPortalEvent runnerBackEvent = new PlayerPortalEvent(manhuntRunner.getPlayer(), manhuntRunner.getPlayer().getLocation(), backTo, PlayerPortalEvent.TeleportCause.NETHER_PORTAL);
        manhuntRunner.getPlayer().teleport(backTo);
        server.getPluginManager().callEvent(runnerBackEvent);

        server.getScheduler().performTicks(100L);

        assertNotEquals(manhuntRunner.getPlayer().getWorld().getEnvironment(), manhuntHunter.getPlayer().getWorld().getEnvironment());

        assertEquals(playerHunter.getCompassTarget(), plugin.getGameEngine().getNetherPortals().get(manhuntRunner));
        var compass = playerHunter.getInventory().getItemInMainHand();
        CompassMeta meta = (CompassMeta) compass.getItemMeta();
        assertEquals(meta.getLodestone(), plugin.getGameEngine().getNetherPortals().get(manhuntRunner));
    }

}
