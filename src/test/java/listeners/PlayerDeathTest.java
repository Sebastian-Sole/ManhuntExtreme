package listeners;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import manhunt_extreme.PluginMain;
import manhunt_extreme.calculators.PlayerScoreCalculator;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PlayerDeathTest {

    private ServerMock server;
    private PluginMain plugin;
    private ManhuntPlayer manhuntRunner;
    private ManhuntPlayer manhuntHunter;
    private PlayerMock playerRunner;
    private PlayerMock playerHunter;

    @BeforeEach
    public void setUp() {
        // Start the mock server
        server = MockBukkit.mock();
        // Load your plugin
        WorldMock worldMock = server.addSimpleWorld("world");
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

    }

    @AfterEach
    public void tearDown() {
        // Stop the mock server
        MockBukkit.unmock();
    }

    @Test
    public void testRunnerDeath() {
        server.execute("start", playerRunner);

        PlayerDeathEvent event = new PlayerDeathEvent(Objects.requireNonNull(playerRunner.getPlayer()), new ArrayList<ItemStack>(), 0, 0, "Death");
        event.getEntity().setKiller(playerHunter.getPlayer());

        server.getPluginManager().callEvent(event);
        assertEquals(1, manhuntRunner.getDeaths());
        assertEquals(1, manhuntHunter.getKills());
        // TODO: Fix code so this works
//        Assertions.assertEquals(playerRunner.nextMessage(), "Game over! Hunters win!");

    }

    @Test
    public void testHunterDeath() {
        server.execute("start", playerRunner);

        PlayerDeathEvent event = new PlayerDeathEvent(Objects.requireNonNull(playerHunter.getPlayer()), new ArrayList<ItemStack>(), 0, 0, "Death");
        event.getEntity().setKiller(manhuntRunner.getPlayer());
        event.getDrops().clear();

        server.getPluginManager().callEvent(event);
        assertEquals(1, manhuntHunter.getDeaths());
        assertEquals(1, manhuntRunner.getKills());
        assertEquals(48.0, manhuntRunner.getPlayer().getMaxHealth());
        assertFalse(manhuntRunner.getPlayer().getActivePotionEffects().isEmpty());
        assertFalse(event.getDrops().isEmpty());
    }

    @Test
    public void testGameNotStarted() {
        PlayerDeathEvent event = new PlayerDeathEvent(Objects.requireNonNull(playerHunter.getPlayer()), new ArrayList<ItemStack>(), 0, 0, "Death");
        Assertions.assertEquals(0, manhuntHunter.getDeaths());
    }

    @Test
    public void testNoKiller() {
        server.execute("start", playerRunner);
        PlayerDeathEvent event = new PlayerDeathEvent(Objects.requireNonNull(playerHunter.getPlayer()), new ArrayList<ItemStack>(), 0, 0, "Death");
        server.getPluginManager().callEvent(event);
        Assertions.assertEquals(0, manhuntHunter.getDeaths());

    }
}
