package manhunt_extreme;

import manhunt_extreme.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public final class PluginMain extends JavaPlugin {

    Logger logger = Logger.getLogger("manhunt_extreme.PluginMain");
    World world;
    GameEngine gameEngine;

    @Override
    public void onEnable() {
        // Plugin startup logic
        gameEngine = new GameEngine(logger);
        logger.info("Plugin Enabled!");
        setWorld();
        registerEvents();
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new Advancement(), this);
        getServer().getPluginManager().registerEvents(new Autocomplete(), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new Death(), this);
        getServer().getPluginManager().registerEvents(new EntityDeath(), this);
        getServer().getPluginManager().registerEvents(new InteractClick(), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        getServer().getPluginManager().registerEvents(new PiglinTrade(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
        getServer().getPluginManager().registerEvents(new PortalEnter(), this);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info("Plugin Disabled!");
    }


    private void setWorld() {
        var worlds = Bukkit.getWorlds();
        if (worlds.size() < 1) {
            this.logger.warning("Could not detect main world! Plugin will not work");
        }
        this.world = worlds.get(0);
    }

}
