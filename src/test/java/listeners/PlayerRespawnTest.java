package listeners;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import manhunt_extreme.PluginMain;
import manhunt_extreme.calculators.PlayerScoreCalculator;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffectType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerRespawnTest {

    private ServerMock server;
    private PluginMain plugin;
    private ManhuntPlayer manhuntRunner;
    private ManhuntPlayer manhuntHunter;
    private PlayerMock playerRunner;
    private PlayerMock playerHunter;
    private WorldMock worldMock;

    @BeforeEach
    public void setUp() {
        // Start the mock server
        server = MockBukkit.mock();
        // Load your plugin
        worldMock = server.addSimpleWorld("world");
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
    }

    @AfterEach
    public void tearDown() {
        // Stop the mock server
        MockBukkit.unmock();
    }

    @Test
    public void testPlayerRespawn() {
        playerRunner.nextMessage();
        playerRunner.nextMessage();

        PlayerRespawnEvent playerRespawnEvent = new PlayerRespawnEvent(playerRunner, plugin.getGameEngine().getWorld().getSpawnLocation(), false);
        server.getPluginManager().callEvent(playerRespawnEvent);
        server.getScheduler().performTicks(60L);
        playerRunner.nextMessage();
//        assertEquals("You're still in the game! The hunters must kill you to eliminate you!", playerRunner.nextMessage());
        assertEquals(4800, Objects.requireNonNull(playerRunner.getPotionEffect(PotionEffectType.SPEED)).getDuration());
        assertEquals(2, Objects.requireNonNull(playerRunner.getPotionEffect(PotionEffectType.SPEED)).getAmplifier());

    }

    @Test
    public void testHunterRespawn() {
        PlayerRespawnEvent playerRespawnEvent = new PlayerRespawnEvent(playerHunter, plugin.getGameEngine().getWorld().getSpawnLocation(), false);
        server.getPluginManager().callEvent(playerRespawnEvent);
        server.getScheduler().performTicks(60L);
        assertEquals(4800, Objects.requireNonNull(playerHunter.getPotionEffect(PotionEffectType.SPEED)).getDuration());
        assertEquals(2, Objects.requireNonNull(playerHunter.getPotionEffect(PotionEffectType.SPEED)).getAmplifier());
    }

//    @Test
//    public void testHunterBedRespawn() {
//        var respawnLocation = new Location(plugin.getGameEngine().getWorld(), 50, 50, 50);
//        manhuntHunter.getPlayer().setBedSpawnLocation(respawnLocation);
//        PlayerRespawnEvent playerRespawnEvent = new PlayerRespawnEvent(manhuntHunter.getPlayer(), respawnLocation, true);
//        server.getPluginManager().callEvent(playerRespawnEvent);
//        server.getScheduler().performTicks(60L);
//        assertEquals(200, Objects.requireNonNull(manhuntHunter.getPlayer().getPotionEffect(PotionEffectType.SPEED)).getDuration());
//        assertEquals(2, Objects.requireNonNull(manhuntHunter.getPlayer().getPotionEffect(PotionEffectType.SPEED)).getAmplifier());
//    }

}
