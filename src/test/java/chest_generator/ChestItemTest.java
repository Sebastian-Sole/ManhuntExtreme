package chest_generator;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import manhunt_extreme.PluginMain;
import manhunt_extreme.chest_generator.ChestItem;
import org.bukkit.Material;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChestItemTest {

    private ServerMock server;
    private PluginMain plugin;

    @BeforeEach
    public void setUp() {
        // Start the mock server
        server = MockBukkit.mock();
        // Load your plugin
        WorldMock worldMock = server.addSimpleWorld("world");
        plugin = MockBukkit.load(PluginMain.class);
    }

    @AfterEach
    public void tearDown() {
        // Stop the mock server
        MockBukkit.unmock();
    }

    @Test
    public void testPotion() {
        ChestItem chestItem = new ChestItem(true);
        var generatedItemStack = chestItem.createItemStack();
        assert generatedItemStack.getType().equals(Material.POTION);
    }

    @Test
    public void testBook() {
        // Todo: Can't do this with MockBukkit because enchants
    }

    // Todo: Test jammer

}
