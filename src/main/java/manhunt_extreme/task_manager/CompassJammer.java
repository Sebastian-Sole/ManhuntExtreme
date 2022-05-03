package manhunt_extreme.task_manager;

import manhunt_extreme.GameEngine;
import manhunt_extreme.PluginMain;
import org.bukkit.Bukkit;

public class CompassJammer {

    private PluginMain pluginMain;
    private GameEngine gameEngine;

    public CompassJammer(PluginMain pluginMain, GameEngine gameEngine) {
        this.pluginMain = pluginMain;
        this.gameEngine = gameEngine;
    }

    public void jamCompass(long duration) {
        if (!gameEngine.getGameStateHandler().isAllowJamming()) {
            Bukkit.broadcastMessage("Jammers are inactive this game");
            throw new IllegalArgumentException("Jammers are inactive");
        }
        gameEngine.setCompassJammed(true);
        Bukkit.broadcastMessage("Compasses have been jammed for " + (duration / (float) 1200) + " minutes!");
        Bukkit.getScheduler().scheduleSyncDelayedTask(pluginMain, () -> {
            gameEngine.setCompassJammed(false);
            Bukkit.broadcastMessage("Compasses are active again!");
        }, duration);
    }


}
