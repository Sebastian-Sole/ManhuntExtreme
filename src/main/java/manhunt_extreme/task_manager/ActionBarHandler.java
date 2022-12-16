package manhunt_extreme.task_manager;

import manhunt_extreme.GameEngine;
import manhunt_extreme.PluginMain;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import manhunt_extreme.manhunt_team.ManhuntTeam;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ActionBarHandler {
    private PluginMain pluginMain;
    private GameEngine gameEngine;

    public ActionBarHandler(PluginMain pluginMain, GameEngine gameEngine) {
        this.pluginMain = pluginMain;
        this.gameEngine = gameEngine;
    }

    public void start() {
        if (gameEngine.isRunning()) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(pluginMain, () -> {
                for (ManhuntPlayer manhuntPlayer : gameEngine.getRunners()) {
                    updateActionBar(manhuntPlayer, manhuntPlayer.getTeam());
                }
                for (ManhuntPlayer manhuntPlayer : gameEngine.getHunters()) {
                    updateActionBar(manhuntPlayer, manhuntPlayer.getTeam());
                }
            }, 0L, 20L);
        }
    }

    private void updateActionBar(ManhuntPlayer manhuntPlayer, ManhuntTeam team) {
        Player player = manhuntPlayer.getPlayer();
        double playerX = player.getLocation().getX();
        double playerZ = player.getLocation().getZ();
        var teammateLocation = closestTeammateCoords(playerX, playerZ, team);

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                "" + ChatColor.BOLD + ChatColor.RED
                        + "x: " + (int) teammateLocation.getX()
                        + ", y: " + (int) teammateLocation.getY()
                        + ", z: " + (int) teammateLocation.getZ()
                        + "    |    "
                        + ChatColor.GREEN + "Chest Odds: 1/" + gameEngine.getGameBalancingCalculator().getChestOddsCalculator().getPlayerChestOdds(manhuntPlayer)
                        + " , Player Score: " + manhuntPlayer.getPlayerScore()
        ));

    }

    private Location closestTeammateCoords(double playerX, double playerZ, ManhuntTeam team) {
        Location closestLocation = null;
        double closestDistance = Double.MAX_VALUE;
        // If the player has no teammates
        if (team.size() == 1) {
            return pluginMain.getGameEngine().getWorld().getSpawnLocation();
        }
        // Find the closest teammate
        for (ManhuntPlayer manhuntPlayer : team.getPlayerList()) {

            var xAxisDifference = manhuntPlayer.getPlayer().getLocation().getX() - playerX;
            var zAxisDifference = manhuntPlayer.getPlayer().getLocation().getZ() - playerZ;
            var totalDifference = xAxisDifference + zAxisDifference;

            if (totalDifference < closestDistance && totalDifference != 0.0) {
                closestLocation = manhuntPlayer.getPlayer().getLocation();
                closestDistance = totalDifference;
            }
        }

        return closestLocation;

    }
}
