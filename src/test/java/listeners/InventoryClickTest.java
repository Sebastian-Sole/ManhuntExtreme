package listeners;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import manhunt_extreme.PluginMain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class InventoryClickTest {

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
//    public void testInventoryClick() {
//        PlayerMock runnerPlayer = server.addPlayer();
//        ManhuntPlayer runner = plugin.getGameEngine().getManhuntPlayerFromPlayer(runnerPlayer);
//        runner.setPlayerScoreCalculator(new PlayerScoreCalculator(runner, plugin.getGameEngine().getTaskManager().getGameClock()));
//        plugin.getGameEngine().getRunnersTeam().addPlayer(runner);
//
//        PlayerMock hunterPlayer = server.addPlayer();
//        ManhuntPlayer hunter = plugin.getGameEngine().getManhuntPlayerFromPlayer(hunterPlayer);
//        hunter.setPlayerScoreCalculator(new PlayerScoreCalculator(hunter, plugin.getGameEngine().getTaskManager().getGameClock()));
//        plugin.getGameEngine().getRunnersTeam().addPlayer(hunter);
//
//        plugin.getGameEngine().startGame();
//
//        hunterPlayer.setItemInHand(new ItemStack(Material.COMPASS));
//
//
//        var compassClickEvent = new PlayerInteractEvent(
//                hunterPlayer,
//                Action.RIGHT_CLICK_AIR,
//                hunterPlayer.getItemInHand(),
//                null,
//                null);
//
//        Bukkit.getPluginManager().callEvent(compassClickEvent);
//
//        var inventory = hunterPlayer.getOpenInventory();
//
//
////        var event = new InventoryClickEvent(
////                inventory,
////                InventoryType.SlotType.CONTAINER,
////                0,
////                ClickType.LEFT,
////                InventoryAction.PICKUP_ALL
////        );
//
////        Bukkit.getPluginManager().callEvent(event);
//        var x = hunterPlayer.simulateInventoryClick(inventory, 0);
//        System.out.println("View: " + x.getView());
//        System.out.println("Title: " + x.getView().getTitle());
//        plugin.getGameEngine().getTargets().forEach((manhuntPlayer, manhuntPlayer2) -> {
//            System.out.println("Tracker: " + manhuntPlayer);
//            System.out.println("Target: " + manhuntPlayer2);
//        });
//        assertTrue(plugin.getGameEngine().getTargets().containsKey(hunter));
//        assertEquals(plugin.getGameEngine().getTargets().get(hunter), runner);
//        assertEquals(hunterPlayer.nextMessage(), "Compass is now targeting " + runnerPlayer.getName());
//    }
}
