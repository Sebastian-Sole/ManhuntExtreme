package manhunt_extreme.listeners;

import manhunt_extreme.GameEngine;
import manhunt_extreme.GameStateHandler;
import manhunt_extreme.listeners.death_drop.DeathDropGenerator;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import manhunt_extreme.manhunt_team.HunterTeam;
import manhunt_extreme.manhunt_team.RunnerTeam;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffectType;

public class PlayerDeath implements Listener {


    private GameEngine gameEngine;
    private GameStateHandler gameStateHandler = gameEngine.getGame().getGameStateHandler();

    public PlayerDeath(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (gameEngine.getGame().isRunning()) {
            gameEngine.getLogger().info("Player died, but game is not running");
            return;
        }

        ManhuntPlayer manhuntPlayerKilled = gameEngine.getManhuntPlayerFromPlayer(event.getEntity().getPlayer());
        ManhuntPlayer killer = gameEngine.getManhuntPlayerFromPlayer(event.getEntity().getKiller());

        // If hunter is killed
        if (manhuntPlayerKilled.getTeam() instanceof HunterTeam) {
            if (killer.getTeam() instanceof RunnerTeam) {
                manhuntPlayerKilled.addDeath();
                killer.addKill();
                if (gameStateHandler.isRunnerHelp()) {
                    killer.getPlayer().setMaxHealth(killer.getPlayer().getMaxHealth() + gameStateHandler.getHealth() / 5);
                    killer.getPlayer().setHealthScale(killer.getPlayer().getHealthScale() + gameStateHandler.getHealth() / 5);
                    killer.getPlayer().addPotionEffect(PotionEffectType.REGENERATION.createEffect(150, 1));
                }
                if (gameStateHandler.isExtraDrops()) {
                    var itemToBeDropped = DeathDropGenerator.createItem();
                    event.getDrops().add(itemToBeDropped);
                }

            }
        }

        if (manhuntPlayerKilled.getTeam() instanceof RunnerTeam) {
            if (killer.getTeam() instanceof HunterTeam) {
                manhuntPlayerKilled.addDeath();
                killer.addKill();
                if (!gameEngine.getHuntersTeam().getPlayerDeaths().values().contains(0)) {
                    Bukkit.broadcastMessage("Game over! Hunters win!");
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "end");
                }
            }
        }

        // If runner is killed


    }

}
