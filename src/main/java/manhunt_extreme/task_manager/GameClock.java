package manhunt_extreme.task_manager;

import manhunt_extreme.PluginMain;
import org.bukkit.Bukkit;

public class GameClock {

    private int minutes;
    private PluginMain pluginMain;

    public GameClock(PluginMain pluginMain) {
        this.pluginMain = pluginMain;
    }

    public void start() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(pluginMain, () -> {
            minutes++;
        }, 1200L, 1200L);
    }


    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }


}
