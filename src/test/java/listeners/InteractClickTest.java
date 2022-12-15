package listeners;

import manhunt_extreme.GameEngine;
import manhunt_extreme.PluginMain;
import manhunt_extreme.listeners.InteractClick;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class InteractClickTest {

    GameEngine gameEngine;
    InteractClick interactClick;
    PlayerInteractEvent event;

    @BeforeEach
    public void setUp() {
        PluginMain pluginMain = mock(PluginMain.class);
        gameEngine = new GameEngine(pluginMain);
        interactClick = new InteractClick(gameEngine);
        event = mock(PlayerInteractEvent.class);
    }

//    @Test
//    public void testOnInteractClick() {
//        Player clickerPlayer = mock(Player.class);
//        Player runnerTarget = mock(Player.class);
//
//        try (MockedStatic<Bukkit> mocked = mockStatic(Bukkit.class)) {
//
//
//            mocked.when(Bukkit::getServer).thenReturn(mock(org.bukkit.Server.class));
//            mocked.when((MockedStatic.Verification) Bukkit.getItemFactory()).thenReturn(mock(org.bukkit.inventory.ItemFactory.class));
//            mocked.when((MockedStatic.Verification) Bukkit.createInventory(null, 9, "Select player to track")).thenReturn(mock(org.bukkit.inventory.Inventory.class));
//
//            ManhuntPlayer runner = new ManhuntPlayer(runnerTarget);
//            gameEngine.getRunnersTeam().addPlayer(runner);
//
//            PlayerInventory playerInventory = mock(PlayerInventory.class);
//            SkullMeta skullMeta = mock(SkullMeta.class);
//            ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1);
//            when(Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD)).thenReturn(skullMeta);
//            ItemStack compass = new ItemStack(Material.COMPASS);
//
//            when(event.getPlayer()).thenReturn(clickerPlayer);
//            when(event.getPlayer().getInventory()).thenReturn(playerInventory);
//            EntityEquipment entityEquipment = mock(EntityEquipment.class);
//            when(entityEquipment.getItemInMainHand()).thenReturn(compass);
//            when(event.getPlayer().getEquipment()).thenReturn(entityEquipment);
//            when(event.getPlayer().getInventory().getItemInMainHand()).thenReturn(compass);
//            assertSame(event.getPlayer().getInventory().getItemInMainHand().getType(), Material.COMPASS);
//            assertFalse(gameEngine.isRunning());
//            // Test that the message is sent?
//            gameEngine.setRunning(true);
//            interactClick.onClick(event);
//            when(clickerPlayer.getOpenInventory()).thenReturn(mock(org.bukkit.inventory.InventoryView.class));
//            when(clickerPlayer.getOpenInventory().getTopInventory()).thenReturn(mock(org.bukkit.inventory.Inventory.class));
//            // Complete test: Check that the inventory is opened
//        }
//    }

    @Test
    public void testOneMinuteCompassJammer() {
        Player clickerPlayer = mock(Player.class);

        try (MockedStatic<Bukkit> mocked = mockStatic(Bukkit.class)) {

            mocked.when(Bukkit::getServer).thenReturn(mock(org.bukkit.Server.class));
            mocked.when((MockedStatic.Verification) Bukkit.getItemFactory()).thenReturn(mock(org.bukkit.inventory.ItemFactory.class));

            var scheduler = mock(BukkitScheduler.class);
            mocked.when(Bukkit::getScheduler).thenReturn(scheduler);

            PlayerInventory playerInventory = mock(PlayerInventory.class);
            ItemStack bedrock = new ItemStack(Material.BEDROCK);
            ItemMeta itemMeta = mock(ItemMeta.class);
            itemMeta.setDisplayName("1 minute jammer");
            bedrock.setItemMeta(itemMeta);


            when(event.getPlayer()).thenReturn(clickerPlayer);
            when(event.getPlayer().getInventory()).thenReturn(playerInventory);
            EntityEquipment entityEquipment = mock(EntityEquipment.class);
            when(entityEquipment.getItemInMainHand()).thenReturn(bedrock);
            when(event.getPlayer().getEquipment()).thenReturn(entityEquipment);
            when(event.getPlayer().getInventory().getItemInMainHand()).thenReturn(bedrock);
            when(bedrock.getItemMeta()).thenReturn(itemMeta);
            when(bedrock.getItemMeta().getDisplayName()).thenReturn("1 minute jammer");

            assert !gameEngine.isCompassJammed();
            interactClick.onClick(event);
            assert gameEngine.isCompassJammed();

        }
    }

    @Test
    public void testTwoMinuteCompassJammer() {
        Player clickerPlayer = mock(Player.class);

        try (MockedStatic<Bukkit> mocked = mockStatic(Bukkit.class)) {

            mocked.when(Bukkit::getServer).thenReturn(mock(org.bukkit.Server.class));
            mocked.when((MockedStatic.Verification) Bukkit.getItemFactory()).thenReturn(mock(org.bukkit.inventory.ItemFactory.class));

            var scheduler = mock(BukkitScheduler.class);
            mocked.when(Bukkit::getScheduler).thenReturn(scheduler);

            PlayerInventory playerInventory = mock(PlayerInventory.class);
            ItemStack bedrock = new ItemStack(Material.BEDROCK);
            ItemMeta itemMeta = mock(ItemMeta.class);
            itemMeta.setDisplayName("2 minute jammer");
            bedrock.setItemMeta(itemMeta);


            when(event.getPlayer()).thenReturn(clickerPlayer);
            when(event.getPlayer().getInventory()).thenReturn(playerInventory);
            EntityEquipment entityEquipment = mock(EntityEquipment.class);
            when(entityEquipment.getItemInMainHand()).thenReturn(bedrock);
            when(event.getPlayer().getEquipment()).thenReturn(entityEquipment);
            when(event.getPlayer().getInventory().getItemInMainHand()).thenReturn(bedrock);
            when(bedrock.getItemMeta()).thenReturn(itemMeta);
            when(bedrock.getItemMeta().getDisplayName()).thenReturn("2 minute jammer");

            assert !gameEngine.isCompassJammed();
            interactClick.onClick(event);
            assert gameEngine.isCompassJammed();

        }
    }

    @Test
    public void testFiveMinuteCompassJammer() {
        Player clickerPlayer = mock(Player.class);

        try (MockedStatic<Bukkit> mocked = mockStatic(Bukkit.class)) {

            mocked.when(Bukkit::getServer).thenReturn(mock(org.bukkit.Server.class));
            mocked.when((MockedStatic.Verification) Bukkit.getItemFactory()).thenReturn(mock(org.bukkit.inventory.ItemFactory.class));

            var scheduler = mock(BukkitScheduler.class);
            mocked.when(Bukkit::getScheduler).thenReturn(scheduler);

            PlayerInventory playerInventory = mock(PlayerInventory.class);
            ItemStack bedrock = new ItemStack(Material.BEDROCK);
            ItemMeta itemMeta = mock(ItemMeta.class);
            itemMeta.setDisplayName("5 minute jammer");
            bedrock.setItemMeta(itemMeta);


            when(event.getPlayer()).thenReturn(clickerPlayer);
            when(event.getPlayer().getInventory()).thenReturn(playerInventory);
            EntityEquipment entityEquipment = mock(EntityEquipment.class);
            when(entityEquipment.getItemInMainHand()).thenReturn(bedrock);
            when(event.getPlayer().getEquipment()).thenReturn(entityEquipment);
            when(event.getPlayer().getInventory().getItemInMainHand()).thenReturn(bedrock);
            when(bedrock.getItemMeta()).thenReturn(itemMeta);
            when(bedrock.getItemMeta().getDisplayName()).thenReturn("5 minute jammer");

            assert !gameEngine.isCompassJammed();
            interactClick.onClick(event);
            assert gameEngine.isCompassJammed();

        }
    }

    @Test
    public void testInvalidCompassJammer() {
        Player clickerPlayer = mock(Player.class);

        try (MockedStatic<Bukkit> mocked = mockStatic(Bukkit.class)) {

            mocked.when(Bukkit::getServer).thenReturn(mock(org.bukkit.Server.class));
            mocked.when((MockedStatic.Verification) Bukkit.getItemFactory()).thenReturn(mock(org.bukkit.inventory.ItemFactory.class));

            var scheduler = mock(BukkitScheduler.class);
            mocked.when(Bukkit::getScheduler).thenReturn(scheduler);

            PlayerInventory playerInventory = mock(PlayerInventory.class);
            ItemStack bedrock = new ItemStack(Material.BEDROCK);
            ItemMeta itemMeta = mock(ItemMeta.class);
            itemMeta.setDisplayName("6 minute jammer");
            bedrock.setItemMeta(itemMeta);


            when(event.getPlayer()).thenReturn(clickerPlayer);
            when(event.getPlayer().getInventory()).thenReturn(playerInventory);
            EntityEquipment entityEquipment = mock(EntityEquipment.class);
            when(entityEquipment.getItemInMainHand()).thenReturn(bedrock);
            when(event.getPlayer().getEquipment()).thenReturn(entityEquipment);
            when(event.getPlayer().getInventory().getItemInMainHand()).thenReturn(bedrock);
            when(bedrock.getItemMeta()).thenReturn(itemMeta);
            when(bedrock.getItemMeta().getDisplayName()).thenReturn("6 minute jammer");

            assert !gameEngine.isCompassJammed();
            assertThrows(IllegalArgumentException.class, () -> interactClick.onClick(event));
            assert !gameEngine.isCompassJammed();

        }
    }

    @Test
    public void testCompassJammerWhenNotEnabled() {
        Player clickerPlayer = mock(Player.class);

        try (MockedStatic<Bukkit> mocked = mockStatic(Bukkit.class)) {
            gameEngine.getGameStateHandler().setAllowJamming(false);

//            mocked.when(Bukkit::getServer).thenReturn(mock(org.bukkit.Server.class));
//            mocked.when((MockedStatic.Verification) Bukkit.getItemFactory()).thenReturn(mock(org.bukkit.inventory.ItemFactory.class));

//            var scheduler = mock(BukkitScheduler.class);
//            mocked.when(Bukkit::getScheduler).thenReturn(scheduler);

            PlayerInventory playerInventory = mock(PlayerInventory.class);
            ItemStack bedrock = new ItemStack(Material.BEDROCK);
//            ItemMeta itemMeta = mock(ItemMeta.class);
//            itemMeta.setDisplayName("1 minute jammer");
//            bedrock.setItemMeta(itemMeta);


            when(event.getPlayer()).thenReturn(clickerPlayer);
            when(event.getPlayer().getInventory()).thenReturn(playerInventory);
            EntityEquipment entityEquipment = mock(EntityEquipment.class);
            when(entityEquipment.getItemInMainHand()).thenReturn(bedrock);
            when(event.getPlayer().getEquipment()).thenReturn(entityEquipment);
            when(event.getPlayer().getInventory().getItemInMainHand()).thenReturn(bedrock);
//            when(bedrock.getItemMeta()).thenReturn(itemMeta);
//            when(bedrock.getItemMeta().getDisplayName()).thenReturn("1 minute jammer");

            assert !gameEngine.isCompassJammed();
            assertThrows(IllegalArgumentException.class, () -> interactClick.onClick(event));
            assert !gameEngine.isCompassJammed();

        }
    }

}
