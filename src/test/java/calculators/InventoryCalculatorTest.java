package calculators;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import manhunt_extreme.PluginMain;
import manhunt_extreme.calculators.InventoryCalculator;
import manhunt_extreme.calculators.PlayerScoreCalculator;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryCalculatorTest {

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

    @Test
    public void testInventoryClick() {
        PlayerMock playerMock = server.addPlayer();
        ManhuntPlayer manhuntPlayer = plugin.getGameEngine().getManhuntPlayerFromPlayer(playerMock);
        manhuntPlayer.setPlayerScoreCalculator(new PlayerScoreCalculator(manhuntPlayer, plugin.getGameEngine().getTaskManager().getGameClock()));
        plugin.getGameEngine().getRunnersTeam().addPlayer(manhuntPlayer);

        InventoryCalculator inventoryCalculator = new InventoryCalculator(manhuntPlayer);
        PlayerInventory inv = manhuntPlayer.getPlayer().getInventory();

        inv.setHelmet(new ItemStack(Material.IRON_HELMET));
        assertEquals(3.0, inventoryCalculator.calculateInventoryScore());

        inv.setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
        assertEquals(12.0, inventoryCalculator.calculateInventoryScore());

        inv.setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));
        assertEquals(17.0, inventoryCalculator.calculateInventoryScore());

        inv.setBoots(new ItemStack(Material.LEATHER_BOOTS));
        assertEquals(18.0, inventoryCalculator.calculateInventoryScore());

        inv.clear();

        inv.setBoots(new ItemStack(Material.NETHERITE_BOOTS));
        assertEquals(5.0, inventoryCalculator.calculateInventoryScore());

        inv.clear();

        inv.addItem(new ItemStack(Material.DIAMOND_SWORD));
        assertEquals(3.0, inventoryCalculator.calculateInventoryScore());

        inv.addItem(new ItemStack(Material.DIAMOND_AXE));
        assertEquals(6.6, inventoryCalculator.calculateInventoryScore());

        inv.addItem(new ItemStack(Material.BOW));
        assertEquals(10.6, inventoryCalculator.calculateInventoryScore());

        inv.clear();
        inv.addItem(new ItemStack(Material.WOODEN_AXE));
        assertEquals(2.5, inventoryCalculator.calculateInventoryScore());

        inv.clear();
        inv.addItem(new ItemStack(Material.STONE_AXE));
        assertEquals(3.0, inventoryCalculator.calculateInventoryScore());

        inv.clear();
        inv.addItem(new ItemStack(Material.IRON_AXE));
        assertEquals(3.3, inventoryCalculator.calculateInventoryScore());

        inv.clear();
        inv.addItem(new ItemStack(Material.GOLDEN_AXE));
        assertEquals(2.5, inventoryCalculator.calculateInventoryScore());

        inv.clear();
        inv.addItem(new ItemStack(Material.NETHERITE_AXE));
        assertEquals(4.0, inventoryCalculator.calculateInventoryScore());

        inv.clear();
        inv.addItem(new ItemStack(Material.CROSSBOW));
        assertEquals(4.5, inventoryCalculator.calculateInventoryScore());
    }


}
