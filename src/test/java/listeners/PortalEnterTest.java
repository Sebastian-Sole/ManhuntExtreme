package listeners;

import manhunt_extreme.GameEngine;
import manhunt_extreme.PluginMain;
import manhunt_extreme.listeners.PortalEnter;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.scheduler.BukkitScheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PortalEnterTest {

    GameEngine gameEngine;
    PortalEnter portalEnter;
    PlayerPortalEvent event;

    @BeforeEach
    public void setUp() {
        PluginMain pluginMain = mock(PluginMain.class);
        gameEngine = new GameEngine(pluginMain);
        portalEnter = new PortalEnter(gameEngine);
        event = mock(PlayerPortalEvent.class);
    }

//    @AfterEach
//    public void tearDown() {
//        Mockito.reset(event);
//    }

    @Test
    public void testOverworldPortalEvent() {

        when(event.getPlayer()).thenReturn(mock(Player.class));

        Player player = mock(Player.class);
        when(event.getPlayer()).thenReturn(player);


        try (MockedStatic<Bukkit> mocked = mockStatic(Bukkit.class)) {
            World overworld = mock(World.class);
            World nether = mock(World.class);
            when(overworld.getEnvironment()).thenReturn(World.Environment.NORMAL);
            when(nether.getEnvironment()).thenReturn(World.Environment.NETHER);

            when(player.getWorld()).thenReturn(overworld);
            when(player.getLocation()).thenReturn(new Location(overworld, 10, 10, 10));

            ManhuntPlayer manhuntPlayer = new ManhuntPlayer(player);
            gameEngine.setRunning(true);
            gameEngine.setWorld(overworld);
            gameEngine.getRunnersTeam().addPlayer(manhuntPlayer);
            gameEngine.getManhuntPlayers().add(manhuntPlayer);

            Location from = new Location(overworld, 0, 0, 0);
            Location to = new Location(nether, 0, 0, 0);

            when(event.getPlayer()).thenReturn(player);
            when(event.getFrom()).thenReturn(from);
            when(event.getTo()).thenReturn(to);
            mocked.when(() -> Bukkit.getWorld("world")).thenReturn(overworld);
            mocked.when(() -> Bukkit.getWorld("world_nether")).thenReturn(nether);
            var scheduler = mock(BukkitScheduler.class);
            mocked.when(Bukkit::getScheduler).thenReturn(scheduler);
            assertFalse(portalEnter.hasAlreadyEnteredNether());

            portalEnter.onPlayerEnterPortal(event);

            assertTrue(gameEngine.getOverworldPortals().containsKey(manhuntPlayer));
            assertSame(gameEngine.getOverworldPortals().get(manhuntPlayer), from);
            assertTrue(gameEngine.getNetherPortals().containsKey(manhuntPlayer));
            assertSame(gameEngine.getNetherPortals().get(manhuntPlayer), to);

            assertTrue(portalEnter.hasAlreadyEnteredNether());
        }


    }

    @Test
    public void testEndPortalEvent() {

        when(event.getPlayer()).thenReturn(mock(Player.class));

        Player player = mock(Player.class);
        when(event.getPlayer()).thenReturn(player);


        try (MockedStatic<Bukkit> mocked = mockStatic(Bukkit.class)) {
            World overworld = mock(World.class);
            World end = mock(World.class);
            when(overworld.getEnvironment()).thenReturn(World.Environment.NORMAL);
            when(end.getEnvironment()).thenReturn(World.Environment.THE_END);

            when(player.getWorld()).thenReturn(overworld);
            when(player.getLocation()).thenReturn(new Location(overworld, 10, 10, 10));

            ManhuntPlayer manhuntPlayer = new ManhuntPlayer(player);
            gameEngine.setRunning(true);
            gameEngine.setWorld(overworld);
            gameEngine.getRunnersTeam().addPlayer(manhuntPlayer);
            gameEngine.getManhuntPlayers().add(manhuntPlayer);

            Location from = new Location(overworld, 0, 0, 0);
            Location to = new Location(end, 0, 0, 0);

            when(event.getPlayer()).thenReturn(player);
            when(event.getFrom()).thenReturn(from);
            when(event.getTo()).thenReturn(to);
            mocked.when(() -> Bukkit.getWorld("world")).thenReturn(overworld);
            mocked.when(() -> Bukkit.getWorld("world_end")).thenReturn(end);

            assertFalse(portalEnter.hasAlreadyEnteredNether());

            portalEnter.onPlayerEnterPortal(event);

            assertEquals(gameEngine.getEndPortalLocation(), from);
        }


    }
}
