package manhunt_extreme.commands;

import manhunt_extreme.Game;
import manhunt_extreme.GameEngine;
import manhunt_extreme.GameStateHandler;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class StartCommand {

    private ManhuntPlayer manhuntPlayer;
    private String[] args;
    private GameEngine gameEngine;
    private Game game;
    private GameStateHandler gameStateHandler;

    public StartCommand(ManhuntPlayer manhuntPlayer, String[] args, GameEngine gameEngine) {
        this.manhuntPlayer = manhuntPlayer;
        this.args = args;
        this.gameEngine = gameEngine;
        this.game = gameEngine.getGame();
        this.gameStateHandler = game.getGameStateHandler();
    }

    public boolean execute() {
        if (game.isRunning()) {
            manhuntPlayer.getPlayer().sendMessage("Game is in progress. Use /end to end the game before using /start");
            return true;
        }
        if (gameEngine.getRunnersTeam().size() < 1) {
            //Todo: Add a debugging mode and validation for hunters team size
            manhuntPlayer.getPlayer().sendMessage("Not enough speedrunners to start");
            return true;
        }
        if (args.length != 0) {
            manhuntPlayer.getPlayer().sendMessage("Illegal format. Use /start");
            return true;
        }
        game.getTargets().clear();
        updateWorld();
        runnersState();
        huntersState();
        //Todo: Compass task
        //Todo: Haste task
        //Todo: Supplydrops task

        game.setRunning(true);
        Bukkit.broadcastMessage("" + ChatColor.DARK_RED + "Manhunt Started!");

        return true;
    }

    private void huntersState() {
        for (ManhuntPlayer manhuntPlayer : gameEngine.getManhuntPlayers()) {
            startState(manhuntPlayer);
            Player player = manhuntPlayer.getPlayer();
            int headStartDuration = gameStateHandler.getHeadStart();
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * headStartDuration, 5));
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * headStartDuration, 3));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 20 * headStartDuration, 10));
            //Todo: ACTION BAR
        }
    }

    private void runnersState() {
        for (ManhuntPlayer manhuntPlayer : gameEngine.getRunners()) {
            startState(manhuntPlayer);
            //Todo: ACTION BAR
            var speedPotion = createSpeedPotion();
            manhuntPlayer.getPlayer().getInventory().addItem(speedPotion);
        }
    }

    private ItemStack createSpeedPotion() {
        var speedPotion = new ItemStack(Material.POTION, 1);
        PotionMeta meta = (PotionMeta) speedPotion.getItemMeta();
        meta.setBasePotionData(new PotionData(PotionType.SPEED));
        speedPotion.setItemMeta(meta);
        return speedPotion;
    }

    @SuppressWarnings("deprecation")
    private void startState(ManhuntPlayer manhuntPlayer) {
        Player player = manhuntPlayer.getPlayer();
        player.setGameMode(GameMode.SURVIVAL);
        player.setHealthScale(gameStateHandler.getHealth());
        player.setHealth(gameStateHandler.getHealth());
        player.setMaxHealth(gameStateHandler.getHealth());
        player.getInventory().clear();
        player.setExp(0F);
        player.setLevel(0);
        player.getInventory().addItem(new ItemStack(Material.COMPASS, 1));
        player.setFoodLevel(20);

    }

    private void updateWorld() {
        var worldBorderSize = gameStateHandler.getBorderSize();
        var world = gameEngine.getWorld();
        var worldBorder = world.getWorldBorder();
        worldBorder.setCenter(world.getSpawnLocation());
        worldBorder.setSize(worldBorderSize);
        world.setTime(0);
        world.setClearWeatherDuration(199999999);
    }
}
