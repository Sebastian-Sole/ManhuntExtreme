package listeners;

import manhunt_extreme.GameEngine;
import manhunt_extreme.PluginMain;
import manhunt_extreme.listeners.EntityDeath;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EntityDeathTest {

    GameEngine gameEngine;
    EntityDeathEvent event;
    EntityDeath entityDeath;

    @BeforeEach
    public void setUp() {
        PluginMain pluginMain = mock(PluginMain.class);
        gameEngine = new GameEngine(pluginMain);
        entityDeath = new EntityDeath(gameEngine);
        gameEngine.setRunning(true);
        event = mock(EntityDeathEvent.class);
    }

    @AfterEach
    public void tearDown() {
        Mockito.reset(event);
    }

    @Test
    public void testBlazeDeath() {
        Blaze blaze = mock(Blaze.class);
        when(blaze.getType()).thenReturn(EntityType.BLAZE);

        when(event.getEntity()).thenReturn(blaze);

//        Random random = Mockito.spy(Random.class);
//        when(random.nextDouble()).thenReturn(0.5);
////        var random = PowerMockito.mock(Random.class);
////        PowerMockito.doReturn(0.5).when(random.nextDouble());
//
//        assert random.nextDouble() == 0.5;

        World world = mock(World.class);

        when(blaze.getLocation()).thenReturn(new Location(world, 10, 10, 10));
        when(blaze.getWorld()).thenReturn(world);

        Location dropLocation = new Location(world, 10, 10, 10);
        world.dropItem(dropLocation, new ItemStack(Material.BLAZE_ROD));

        entityDeath.onEntityDeath(event);

//        Assertions.assertTrue(world.getNearbyEntities(dropLocation, 100, 100, 100).stream().anyMatch(entity -> entity.getType()));
        Mockito.reset(blaze);

    }

    @Test
    public void testChickenDeath() {
        Chicken chicken = mock(Chicken.class);
        when(chicken.getType()).thenReturn(EntityType.CHICKEN);

        when(event.getEntity()).thenReturn(chicken);
        ArrayList<ItemStack> drops = new ArrayList<>();
        drops.add(new ItemStack(Material.CHICKEN));
        when(event.getDrops()).thenReturn(drops);

        World world = mock(World.class);

        when(chicken.getLocation()).thenReturn(new Location(world, 10, 10, 10));
        when(chicken.getWorld()).thenReturn(world);

        Assertions.assertSame(event.getEntity().getType(), EntityType.CHICKEN);

        entityDeath.onEntityDeath(event);

        Assertions.assertTrue(event.getDrops().stream().anyMatch(itemStack -> itemStack.getType().equals(Material.COOKED_CHICKEN)));
        Assertions.assertEquals(event.getDrops(), drops);

        Mockito.reset(chicken);

    }

    @Test
    public void testCowDeath() {
        Cow cow = mock(Cow.class);
        when(cow.getType()).thenReturn(EntityType.COW);

        when(event.getEntity()).thenReturn(cow);
        ArrayList<ItemStack> drops = new ArrayList<>();
        drops.add(new ItemStack(Material.BEEF));
        when(event.getDrops()).thenReturn(drops);

        World world = mock(World.class);

        when(cow.getLocation()).thenReturn(new Location(world, 10, 10, 10));
        when(cow.getWorld()).thenReturn(world);

        Assertions.assertSame(event.getEntity().getType(), EntityType.COW);

        entityDeath.onEntityDeath(event);

        Assertions.assertTrue(event.getDrops().stream().anyMatch(itemStack -> itemStack.getType().equals(Material.COOKED_BEEF)));
        Assertions.assertEquals(event.getDrops(), drops);

        Mockito.reset(cow);

    }

    @Test
    public void testPigDeath() {
        Pig pig = mock(Pig.class);
        when(pig.getType()).thenReturn(EntityType.PIG);

        when(event.getEntity()).thenReturn(pig);
        ArrayList<ItemStack> drops = new ArrayList<>();
        drops.add(new ItemStack(Material.PORKCHOP));
        when(event.getDrops()).thenReturn(drops);

        World world = mock(World.class);

        when(pig.getLocation()).thenReturn(new Location(world, 10, 10, 10));
        when(pig.getWorld()).thenReturn(world);

        Assertions.assertSame(event.getEntity().getType(), EntityType.PIG);

        entityDeath.onEntityDeath(event);

        Assertions.assertTrue(event.getDrops().stream().anyMatch(itemStack -> itemStack.getType().equals(Material.COOKED_PORKCHOP)));
        Assertions.assertEquals(event.getDrops(), drops);

        Mockito.reset(pig);

    }

    @Test
    public void testHoglinDeath() {
        Hoglin hoglin = mock(Hoglin.class);
        when(hoglin.getType()).thenReturn(EntityType.HOGLIN);

        when(event.getEntity()).thenReturn(hoglin);
        ArrayList<ItemStack> drops = new ArrayList<>();
        drops.add(new ItemStack(Material.PORKCHOP));
        when(event.getDrops()).thenReturn(drops);

        World world = mock(World.class);

        when(hoglin.getLocation()).thenReturn(new Location(world, 10, 10, 10));
        when(hoglin.getWorld()).thenReturn(world);

        Assertions.assertSame(event.getEntity().getType(), EntityType.HOGLIN);
        entityDeath.onEntityDeath(event);

        Assertions.assertTrue(event.getDrops().stream().anyMatch(itemStack -> itemStack.getType().equals(Material.COOKED_PORKCHOP)));
        Assertions.assertEquals(event.getDrops(), drops);

        Mockito.reset(hoglin);

    }

    @Test
    public void testRabbitDeath() {
        Rabbit rabbit = mock(Rabbit.class);
        when(rabbit.getType()).thenReturn(EntityType.RABBIT);

        when(event.getEntity()).thenReturn(rabbit);
        ArrayList<ItemStack> drops = new ArrayList<>();
        drops.add(new ItemStack(Material.RABBIT));
        when(event.getDrops()).thenReturn(drops);

        World world = mock(World.class);

        when(rabbit.getLocation()).thenReturn(new Location(world, 10, 10, 10));
        when(rabbit.getWorld()).thenReturn(world);

        Assertions.assertSame(event.getEntity().getType(), EntityType.RABBIT);
        entityDeath.onEntityDeath(event);

        Assertions.assertTrue(event.getDrops().stream().anyMatch(itemStack -> itemStack.getType().equals(Material.COOKED_RABBIT)));
        Assertions.assertEquals(event.getDrops(), drops);

        Mockito.reset(rabbit);

    }

    @Test
    public void testSheepDeath() {
        Sheep sheep = mock(Sheep.class);
        when(sheep.getType()).thenReturn(EntityType.SHEEP);

        when(event.getEntity()).thenReturn(sheep);
        ArrayList<ItemStack> drops = new ArrayList<>();
        drops.add(new ItemStack(Material.MUTTON));
        when(event.getDrops()).thenReturn(drops);

        World world = mock(World.class);

        when(sheep.getLocation()).thenReturn(new Location(world, 10, 10, 10));
        when(sheep.getWorld()).thenReturn(world);

        Assertions.assertSame(event.getEntity().getType(), EntityType.SHEEP);
        entityDeath.onEntityDeath(event);

        Assertions.assertTrue(event.getDrops().stream().anyMatch(itemStack -> itemStack.getType().equals(Material.COOKED_MUTTON)));
        Assertions.assertEquals(event.getDrops(), drops);

        Mockito.reset(sheep);

    }


}
