package manhunt_extreme.listeners;

import manhunt_extreme.GameEngine;
import manhunt_extreme.GameStateHandler;
import manhunt_extreme.calculators.ChestOddsCalculator;
import manhunt_extreme.calculators.CutCleanCalculator;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;
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

    private final GameEngine gameEngine;
    private final GameStateHandler gameStateHandler;
    private final Random random = new Random();
    private final ChestOddsCalculator chestOddsCalculator;
    private final CutCleanCalculator cutCleanCalculator;

    public BlockBreak(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        gameStateHandler = gameEngine.getGame().getGameStateHandler();
        chestOddsCalculator = gameEngine.getGameBalancingCalculator().getChestOddsCalculator();
        cutCleanCalculator = gameEngine.getGameBalancingCalculator().getCutCleanCalculator();
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!gameEngine.getGame().isRunning()) {
            event.getPlayer().sendMessage("Cannot break blocks before game has started");
            event.setCancelled(true);
            return;
        }
        if (gameStateHandler.isChestGenerate()) {
            ManhuntPlayer manhuntPlayer = gameEngine.getManhuntPlayerFromPlayer(event.getPlayer());
            int playerOdds = chestOddsCalculator.getPlayerChestOdds(manhuntPlayer);
            int numberGenerated = random.nextInt(playerOdds);
            if (numberGenerated == 69) {
                Location blockBrokenLocation = event.getBlock().getLocation();
                gameEngine.getChestGenerator().generateChest(blockBrokenLocation, event, event.getPlayer().getWorld());
            }
            Bukkit.broadcastMessage("Gen Number: " + numberGenerated);
            Bukkit.broadcastMessage("Your Score: " + manhuntPlayer.getPlayerScore());
            Bukkit.broadcastMessage("Player odds: " + playerOdds);

        }
        if (gameStateHandler.isCutClean()) {
            ManhuntPlayer manhuntPlayer = gameEngine.getManhuntPlayerFromPlayer(event.getPlayer());
            double odds = cutCleanCalculator.calculateOdds(manhuntPlayer);
            if (random.nextDouble() <= odds) {
                handleCutClean(event);
            }
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
