package manhunt_extreme;

import manhunt_extreme.commands.UserInput;
import manhunt_extreme.listeners.*;
import manhunt_extreme.task_manager.TaskManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public final class PluginMain extends JavaPlugin {

    private Logger logger = Logger.getLogger("manhunt_extreme.PluginMain");
    private World world;
    private GameEngine gameEngine;

    @Override
    public void onEnable() {
        // Plugin startup logic
        TaskManager taskManager = new TaskManager(this);
        gameEngine = new GameEngine();
        gameEngine.setWorld(world);
        logger.info("Manhunt Extreme Plugin Enabled!");
        setWorld();
        registerEvents();
        setCommandExecutor();
    }

    private void setCommandExecutor() {
        UserInput commands = new UserInput(gameEngine);
        for (String command : UserInput.registeredCommands) {
            this.getCommand(command).setExecutor(commands);
        }
        logger.info("Commands set");
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new Advancement(), this);
        getServer().getPluginManager().registerEvents(new Autocomplete(), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new EntityDeath(), this);
        getServer().getPluginManager().registerEvents(new InteractClick(), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        getServer().getPluginManager().registerEvents(new PiglinTrade(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
        getServer().getPluginManager().registerEvents(new PortalEnter(), this);
        logger.info("Events registered");
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info("Manhunt Extreme Plugin Disabled!");
    }


    private void setWorld() {
        var worlds = Bukkit.getWorlds();
        if (worlds.size() < 1) {
            this.logger.warning("Could not detect main world! Plugin will not work");
        }
        this.world = worlds.get(0);
    }


    public GameEngine getGameEngine() {
        return gameEngine;
    }
}
