package manhunt_extreme.listeners;

import manhunt_extreme.GameEngine;
import manhunt_extreme.GameStateHandler;
import manhunt_extreme.PluginMain;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import manhunt_extreme.manhunt_team.HunterTeam;
import manhunt_extreme.manhunt_team.RunnerTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffectType;

public class PlayerRespawn implements Listener {

    private final GameEngine gameEngine;
    private final PluginMain pluginMain;
    private final GameStateHandler gameStateHandler;

    public PlayerRespawn(GameEngine gameEngine, PluginMain pluginMain) {
        this.gameEngine = gameEngine;
        this.pluginMain = pluginMain;
        this.gameStateHandler = gameEngine.getGameStateHandler();
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (!gameEngine.isRunning()) {
            return;
        }
        ManhuntPlayer respawnedPlayer = gameEngine.getManhuntPlayerFromPlayer(event.getPlayer());
        if (respawnedPlayer.getTeam() instanceof RunnerTeam) {
            respawnRunner(respawnedPlayer);
        } else if (respawnedPlayer.getTeam() instanceof HunterTeam) {
            respawnHunter(respawnedPlayer);
        }
        respawnedPlayer.reset();
        Bukkit.broadcastMessage("Player score: " + respawnedPlayer.getPlayerScore());
    }

    private void respawnHunter(ManhuntPlayer respawnedPlayer) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(pluginMain, () -> {
            int speedDuration;
            if (respawnedPlayer.getPlayer().getBedSpawnLocation() == null) {
                speedDuration = 4800;
            } else {
                //Todo: Set speedDuration by the game calculator
                speedDuration = 200;
            }
            respawnedPlayer.getPlayer().addPotionEffect(PotionEffectType.SPEED.createEffect(speedDuration, 2));
            if (gameStateHandler.isHunterHelp()) {
                gameEngine.getRespawnHandler().giveItems(respawnedPlayer);
                Bukkit.getLogger().info("Hunter help is true, items have been given");
            }
        }, 60L);
    }

    private void respawnRunner(ManhuntPlayer respawnedPlayer) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(pluginMain, () -> {
            String runnerName = respawnedPlayer.getPlayer().getName();
            if (respawnedPlayer.getDeaths() > 0) {
                respawnedPlayer.getPlayer().sendMessage("You are eliminated, keep your teammate alive!");
                Bukkit.broadcastMessage(ChatColor.BOLD.toString() + ChatColor.RED + runnerName + "IS ELIMINATED");
                Bukkit.broadcastMessage(ChatColor.BOLD.toString() + ChatColor.RED + "Eliminate remaining runners to win!");
                respawnedPlayer.getPlayer().sendTitle(ChatColor.DARK_RED.toString() + "ELIMINATED", ChatColor.AQUA.toString() + "Help the runner team win!", 20, 60, 20);
            } else {
                respawnedPlayer.getPlayer().sendMessage("You're still in the game! Get to your teammate quickly before the hunters eliminate you!");
            }
            int speedDuration = respawnedPlayer.getPlayer().getBedSpawnLocation() == null ? 4800 : 240;
            respawnedPlayer.getPlayer().addPotionEffect(PotionEffectType.SPEED.createEffect(
                    speedDuration, 2
            ));
            if (gameStateHandler.isRunnerHelp()) {
                gameEngine.getRespawnHandler().giveItems(respawnedPlayer);
            }

        }, 60L);
    }


}
