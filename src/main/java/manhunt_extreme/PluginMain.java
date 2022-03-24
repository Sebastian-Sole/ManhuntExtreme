package manhunt_extreme;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;


public final class PluginMain extends JavaPlugin {

    Logger logger = Logger.getLogger("manhunt_extreme.PluginMain");

    @Override
    public void onEnable() {
        // Plugin startup logic
        logger.info("Plugin Enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info("Plugin Disabled!");
    }
}
