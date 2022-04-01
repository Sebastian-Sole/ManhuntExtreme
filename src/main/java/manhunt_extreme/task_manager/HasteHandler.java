package manhunt_extreme.task_manager;

import manhunt_extreme.GameEngine;
import manhunt_extreme.PluginMain;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Bukkit;
import org.bukkit.potion.PotionEffectType;

public class HasteHandler {

    private final PluginMain pluginMain;
    private GameEngine gameEngine;

    public HasteHandler(PluginMain pluginMain) {
        this.pluginMain = pluginMain;
        this.gameEngine = pluginMain.getGameEngine();
    }

    public void start() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(pluginMain, () -> giveHaste(), 10L, 1200L);
    }

    private void giveHaste() {
        for (ManhuntPlayer manhuntPlayer : gameEngine.getRunners()) {
            manhuntPlayer.getPlayer().addPotionEffect(PotionEffectType.FAST_DIGGING.createEffect(Integer.MAX_VALUE, 3));
        }
        for (ManhuntPlayer manhuntPlayer : gameEngine.getHunters()) {
            manhuntPlayer.getPlayer().addPotionEffect(PotionEffectType.FAST_DIGGING.createEffect(Integer.MAX_VALUE, 3));
        }
    }

}
