package manhunt_extreme;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public final class PluginMain extends JavaPlugin {

    Logger logger = Logger.getLogger("manhunt_extreme.PluginMain");
    GameEngine gameEngine;

    @Override
    public void onEnable() {
        // Plugin startup logic
        gameEngine = new GameEngine();
        logger.info("Plugin Enabled!");
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info("Plugin Disabled!");
    }
}
