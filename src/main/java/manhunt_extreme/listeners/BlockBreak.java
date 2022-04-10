package manhunt_extreme.listeners;

import manhunt_extreme.GameEngine;
import manhunt_extreme.GameStateHandler;
import manhunt_extreme.manhunt_team.HunterTeam;
import manhunt_extreme.manhunt_team.RunnerTeam;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BlockBreak implements Listener {

    private GameEngine gameEngine;
    private GameStateHandler gameStateHandler = gameEngine.getGame().getGameStateHandler();
    private Random random = new Random();

    public BlockBreak(GameEngine gameEngine) {

        this.gameEngine = gameEngine;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!gameEngine.getGame().isRunning()) {
            //Todo: Cannot break blocks
            event.getPlayer().sendMessage("Cannot break blocks before game has started");
            event.setCancelled(true);
            return;
        }
        if (gameStateHandler.isChestGenerate()) {
            //Todo: Implement odds calculator
            int numberGenerated = 0;
            if (gameEngine.getManhuntPlayerFromPlayer(event.getPlayer()).getTeam() instanceof HunterTeam) {
                numberGenerated = random.nextInt(625); // 625
            } else if (gameEngine.getManhuntPlayerFromPlayer(event.getPlayer()).getTeam() instanceof RunnerTeam) {
                numberGenerated = random.nextInt(525); // 525
            }
            if (numberGenerated == 69) {
                Location blockBrokenLocation = event.getBlock().getLocation();
                gameEngine.getChestGenerator().generateChest(blockBrokenLocation, event, event.getPlayer().getWorld());
            }

        }
        if (gameStateHandler.isCutClean()) {
            // Todo: Implement cut clean calculator
            handleCutClean(event);
        }
    }

    private void handleCutClean(BlockBreakEvent event) {
        Block blockBroken = event.getBlock();
        World world = blockBroken.getWorld();
        Location location = blockBroken.getLocation();
        ExperienceOrb orb;
        switch (blockBroken.getType()) {
            case IRON_ORE, DEEPSLATE_IRON_ORE:
                event.setCancelled(true);
                blockBroken.setType(Material.AIR);
                world.dropItemNaturally(location, new ItemStack(Material.IRON_INGOT));
                orb = world.spawn(location, ExperienceOrb.class);
                orb.setExperience(2);
            case GOLD_ORE, DEEPSLATE_GOLD_ORE:
                event.setCancelled(true);
                blockBroken.setType(Material.AIR);
                world.dropItemNaturally(location, new ItemStack(Material.GOLD_INGOT));
                orb = world.spawn(location, ExperienceOrb.class);
                orb.setExperience(2);
            case COPPER_ORE, DEEPSLATE_COPPER_ORE:
                event.setCancelled(true);
                blockBroken.setType(Material.AIR);
                world.dropItemNaturally(location, new ItemStack(Material.COPPER_INGOT));
                orb = world.spawn(location, ExperienceOrb.class);
                orb.setExperience(2);
            case POTATO, POTATOES:
                event.setCancelled(true);
                blockBroken.setType(Material.AIR);
                world.dropItemNaturally(location, new ItemStack(Material.BAKED_POTATO));
                orb = world.spawn(location, ExperienceOrb.class);
                orb.setExperience(1);
        }
    }
}
