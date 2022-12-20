package listeners;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import manhunt_extreme.PluginMain;
import manhunt_extreme.calculators.PlayerScoreCalculator;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.block.BlockBreakEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.Random;

public class BlockBreakTest {

    private ServerMock server;
    private PluginMain plugin;

    @BeforeEach
    public void setUp() {
        // Start the mock server
        server = MockBukkit.mock();
        // Load your plugin
        WorldMock worldMock = server.addSimpleWorld("world");
        plugin = MockBukkit.load(PluginMain.class);
        server.getPluginManager().enablePlugin(plugin);
    }

    @AfterEach
    public void tearDown() {
        // Stop the mock server
        MockBukkit.unmock();
    }


//    @Test
//    public void testCutClean() {
//        PlayerMock player = server.addPlayer();
//        ManhuntPlayer manhuntPlayer = plugin.getGameEngine().getManhuntPlayerFromPlayer(player);
//        manhuntPlayer.setPlayerScoreCalculator(new PlayerScoreCalculator(manhuntPlayer, plugin.getGameEngine().getTaskManager().getGameClock()));
//        plugin.getGameEngine().getRunnersTeam().addPlayer(manhuntPlayer);
//        plugin.getGameEngine().startGame();
//        var world = server.getWorld("world");
//
//        var ironOre = world.getBlockAt(0, 47, 0);
//        ironOre.setType(Material.IRON_ORE);
//        var deepslateIronOre = world.getBlockAt(1, 47, 0);
//        ironOre.setType(Material.DEEPSLATE_IRON_ORE);
//        var goldOre = world.getBlockAt(2, 47, 0);
//        goldOre.setType(Material.GOLD_ORE);
//        var deepslateGoldOre = world.getBlockAt(3, 47, 0);
//        deepslateGoldOre.setType(Material.DEEPSLATE_GOLD_ORE);
//        var copperOre = world.getBlockAt(4, 47, 0);
//        copperOre.setType(Material.COPPER_ORE);
//        var deepslateCopperOre = world.getBlockAt(5, 47, 0);
//        deepslateCopperOre.setType(Material.DEEPSLATE_COPPER_ORE);
//        var potato = world.getBlockAt(6, 47, 0);
//        potato.setType(Material.POTATO);
//        var potatoes = world.getBlockAt(7, 47, 0);
//        potatoes.setType(Material.POTATOES);
//
//
//        var blockBreakEvent = new BlockBreakEvent(ironOre, player);
//        Bukkit.getPluginManager().callEvent(blockBreakEvent);
//        assertTrue(blockBreakEvent.isCancelled());
//        assertSame(blockBreakEvent.getBlock().getType(), Material.AIR);
//
//        blockBreakEvent = new BlockBreakEvent(deepslateIronOre, player);
//        Bukkit.getPluginManager().callEvent(blockBreakEvent);
//        assertTrue(blockBreakEvent.isCancelled());
//        assertSame(blockBreakEvent.getBlock().getType(), Material.AIR);
//
//        blockBreakEvent = new BlockBreakEvent(goldOre, player);
//        Bukkit.getPluginManager().callEvent(blockBreakEvent);
//        assertTrue(blockBreakEvent.isCancelled());
//        assertSame(blockBreakEvent.getBlock().getType(), Material.AIR);
//
//        blockBreakEvent = new BlockBreakEvent(deepslateGoldOre, player);
//        Bukkit.getPluginManager().callEvent(blockBreakEvent);
//        assertTrue(blockBreakEvent.isCancelled());
//        assertSame(blockBreakEvent.getBlock().getType(), Material.AIR);
//
//        blockBreakEvent = new BlockBreakEvent(copperOre, player);
//        Bukkit.getPluginManager().callEvent(blockBreakEvent);
//        assertTrue(blockBreakEvent.isCancelled());
//        assertSame(blockBreakEvent.getBlock().getType(), Material.AIR);
//
//        blockBreakEvent = new BlockBreakEvent(deepslateCopperOre, player);
//        Bukkit.getPluginManager().callEvent(blockBreakEvent);
//        assertTrue(blockBreakEvent.isCancelled());
//        assertSame(blockBreakEvent.getBlock().getType(), Material.AIR);
//
//        blockBreakEvent = new BlockBreakEvent(potato, player);
//        Bukkit.getPluginManager().callEvent(blockBreakEvent);
//        assertTrue(blockBreakEvent.isCancelled());
//        assertSame(blockBreakEvent.getBlock().getType(), Material.AIR);
//
//        blockBreakEvent = new BlockBreakEvent(potatoes, player);
//        Bukkit.getPluginManager().callEvent(blockBreakEvent);
//        assertTrue(blockBreakEvent.isCancelled());
//        assertSame(blockBreakEvent.getBlock().getType(), Material.AIR);
//    }

    @Test
    public void testBlockBreak() {
        PlayerMock player = server.addPlayer();
        ManhuntPlayer manhuntPlayer = plugin.getGameEngine().getManhuntPlayerFromPlayer(player);
        manhuntPlayer.setPlayerScoreCalculator(new PlayerScoreCalculator(manhuntPlayer, plugin.getGameEngine().getTaskManager().getGameClock()));
        plugin.getGameEngine().getRunnersTeam().addPlayer(manhuntPlayer);

        Random random = new Random();
        plugin.getGameEngine().startGame();

        int count = 0;
        int maxTries = 1000000;
        while (count < maxTries) {
            Bukkit.getPluginManager().callEvent(
                    new BlockBreakEvent(server.getWorld("world").getBlockAt(
                            random.nextInt(100000000),
                            random.nextInt(30),
                            random.nextInt(100000000)), player));
            try {
                Assertions.assertEquals(player.nextMessage(), "A chest was generated by " + player.getName());
                break;
            } catch (AssertionFailedError | IllegalArgumentException e) {
                count++;
                if (count == maxTries) {
                    throw e;
                }
            }
        }


    }

    @Test
    public void testInvalidBlockBreak() {
        PlayerMock player = server.addPlayer();
        ManhuntPlayer manhuntPlayer = plugin.getGameEngine().getManhuntPlayerFromPlayer(player);
        manhuntPlayer.setPlayerScoreCalculator(new PlayerScoreCalculator(manhuntPlayer, plugin.getGameEngine().getTaskManager().getGameClock()));
        plugin.getGameEngine().getRunnersTeam().addPlayer(manhuntPlayer);

        Bukkit.getPluginManager().callEvent(new BlockBreakEvent(server.getWorld("world").getBlockAt(0, 47, 0), player));
        Assertions.assertEquals(player.nextMessage(), "Cannot break blocks before game has started");

    }

}
