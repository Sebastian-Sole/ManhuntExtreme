package manhunt_extreme.listeners;

import manhunt_extreme.GameEngine;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.ServerOperator;

import java.util.Random;

public class EntityDeath implements Listener {

    private GameEngine gameEngine;


    public EntityDeath(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getType() == EntityType.BLAZE) {
            handleBlazeDeath(event.getEntity());
        } else if (event.getEntity().getType() == EntityType.ENDER_DRAGON) {
            for (ManhuntPlayer manhuntPlayer : gameEngine.getHunters()) {
                manhuntPlayer.getPlayer().sendTitle(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "YOU LOSE!", "Sucks to suck!!", 20, 60, 20);
            }
            for (ManhuntPlayer manhuntPlayer : gameEngine.getRunners()) {
                manhuntPlayer.getPlayer().sendTitle(ChatColor.GREEN.toString() + ChatColor.BOLD + "YOU WIN!", ChatColor.MAGIC + "Bernothy loves you!", 20, 60, 20);
            }
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getOnlinePlayers().stream().filter(ServerOperator::isOp).toList().get(0),
                    "/end"
            );
            Bukkit.broadcastMessage("Game over");
        }

        if (gameEngine.getGameStateHandler().isCutClean()) {
            handleCutClean(event);
        }
    }

    private void handleCutClean(EntityDeathEvent event) {
        //TODO: Calculate odds of drops
        Entity entity = event.getEntity();
        if (entity instanceof Chicken) {
            for (ItemStack item : event.getDrops()) {
                if (item.getType().equals(Material.CHICKEN)) {
                    item.setType(Material.COOKED_CHICKEN);
                }
            }
        } else if (entity instanceof Cow) {
            for (ItemStack drop : event.getDrops()) {
                if (drop.getType().equals(Material.BEEF)) {
                    drop.setType(Material.COOKED_BEEF);
                }
            }
        } else if (entity instanceof Pig || entity instanceof Hoglin) {
            for (ItemStack item : event.getDrops()) {
                if (item.getType().equals(Material.PORKCHOP)) {
                    item.setType(Material.COOKED_PORKCHOP);
                }
            }
        } else if (entity instanceof Rabbit) {
            for (ItemStack item : event.getDrops()) {
                if (item.getType().equals(Material.RABBIT)) {
                    item.setType(Material.COOKED_RABBIT);
                }
            }
        } else if (entity instanceof Sheep) {
            for (ItemStack item : event.getDrops()) {
                if (item.getType().equals(Material.MUTTON)) {
                    item.setType(Material.COOKED_MUTTON);
                }
            }
        }
    }

    private void handleBlazeDeath(LivingEntity entity) {
        Random random = new Random();
        if (random.nextDouble() <= 0.45) {
            entity.getWorld().dropItem(entity.getLocation(), new ItemStack(Material.GOLD_INGOT));
        }
        if (random.nextDouble() <= 0.45) {
            entity.getWorld().dropItem(entity.getLocation(), new ItemStack(Material.BLAZE_ROD));
        }
        if (random.nextDouble() <= 0.3) {
            entity.getWorld().dropItem(entity.getLocation(), new ItemStack(Material.ENDER_PEARL));
        }
        if (random.nextDouble() <= 0.04) {
            entity.getWorld().dropItem(entity.getLocation(), new ItemStack(Material.ENDER_PEARL));
        }
        if (random.nextDouble() <= 0.0095) {
            entity.getWorld().dropItem(entity.getLocation(), new ItemStack(Material.NETHERITE_SCRAP));
        }
    }
}
