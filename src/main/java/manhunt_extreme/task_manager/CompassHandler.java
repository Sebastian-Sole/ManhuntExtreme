package manhunt_extreme.task_manager;

import manhunt_extreme.GameEngine;
import manhunt_extreme.PluginMain;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.CompassMeta;

import java.util.Map;

public class CompassHandler {
    private final GameEngine gameEngine;
    private PluginMain pluginMain;

    public CompassHandler(PluginMain pluginMain, GameEngine gameEngine) {
        this.pluginMain = pluginMain;
        this.gameEngine = gameEngine;
    }

    public void start() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(pluginMain, () -> {
            if (!gameEngine.isCompassJammed()) {
                for (Map.Entry<ManhuntPlayer, ManhuntPlayer> entry : gameEngine.getTargets().entrySet()) {
                    ManhuntPlayer compassUser = entry.getKey();
                    ManhuntPlayer target = entry.getValue();
                    if (compassUser == null || target == null) {
                        continue;
                    }

                    Inventory inv = compassUser.getPlayer().getInventory();
                    // If the compassUser and runner are not in the same world, point compass to the location of the portal (in the compassUser's world)
                    if (compassUser.getPlayer().getWorld().getEnvironment() != target.getPlayer().getWorld().getEnvironment()) {
                        Location portalLocation = null;
                        // If compassUser is in nether and runner is not in nether, show the portal in the nether
                        if (compassUser.getPlayer().getWorld().getEnvironment() == World.Environment.NETHER && target.getPlayer().getWorld().getEnvironment() != World.Environment.NETHER) {
                            portalLocation = gameEngine.getNetherPortals().get(target);
                        }
                        // If compassUser is in overworld, and runner is not in overworld
                        else if (compassUser.getPlayer().getWorld().getEnvironment() == World.Environment.NORMAL && target.getPlayer().getWorld().getEnvironment() != World.Environment.NORMAL) {
                            portalLocation = gameEngine.getOverworldPortals().get(target);
                        }
                        // If runner is in end, and compassUser isn't
                        if (target.getPlayer().getWorld().getEnvironment() == World.Environment.THE_END) {
                            if (compassUser.getPlayer().getWorld().getEnvironment() == World.Environment.NETHER) {
                                portalLocation = gameEngine.getNetherPortals().get(target);
                            } else if (compassUser.getPlayer().getWorld().getEnvironment() == World.Environment.NORMAL) {
                                portalLocation = gameEngine.getEndPortalLocation();
                            }
                        } else {
                            portalLocation = compassUser.getPlayer().getWorld().getSpawnLocation();
                        }
                        if (portalLocation != null) {
                            compassUser.getPlayer().setCompassTarget(portalLocation);
                        }
                        for (int j = 0; j < inv.getSize(); j++) {
                            var stack = inv.getItem(j);
                            if (stack == null) {
                                continue;
                            }
                            if (stack.getType() != Material.COMPASS) {
                                continue;
                            }
                            stack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
                            var meta = stack.getItemMeta();
                            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                            stack.setItemMeta(meta);
                        }

                    } else {
                        compassUser.getPlayer().setCompassTarget(target.getPlayer().getLocation());
                        for (int j = 0; j <= inv.getSize(); j++) {
                            var stack = inv.getItem(j);
                            if (stack == null)
                                continue;
                            if (stack.getType() != Material.COMPASS)
                                continue;
                            var meta = (CompassMeta) stack.getItemMeta();
                            meta.setLodestone(target.getPlayer().getLocation());
                            meta.setLodestoneTracked(false);
                            stack.setItemMeta(meta);
                        }
                    }

                }
            }
        }, 0L, 20L);


    }


}
