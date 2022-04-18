package manhunt_extreme.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class PiglinTrade implements Listener {

    private final Random random = new Random();
    private Entity entity;

    @EventHandler
    public void onPiglinTrade(EntityDropItemEvent event) {
        if (event.getEntity().getType() == EntityType.PIGLIN) {
            this.entity = event.getEntity();
            runItemDropRandomizer();
        }
    }

    private void runItemDropRandomizer() {
        if (random.nextInt(10) == 1) {
            dropItem(Material.GOLD_INGOT);
            return;
        }
        if (random.nextInt(15) == 1) {
            dropItem(Material.GOLDEN_CARROT, random.nextInt(10) + 2);
            return;
        }
        if (random.nextInt(27) == 1) {
            dropItem(Material.ENDER_PEARL, random.nextInt(2) + 1);
        }
        if (random.nextInt(17) == 1) {
            spawnPiglin();
        }
        if (random.nextInt(38) == 1) {
            dropItem(Material.BLAZE_ROD, random.nextInt(2) + 1);
        }

    }

    private void spawnPiglin() {
        this.entity.getWorld().spawnEntity(this.entity.getLocation(), EntityType.PIGLIN);
    }

    private void dropItem(Material material, int i) {
        this.entity.getWorld().dropItem(this.entity.getLocation(), new ItemStack(material, i));
    }

    private void dropItem(Material material) {
        this.entity.getWorld().dropItem(this.entity.getLocation(), new ItemStack(material));
    }
}
