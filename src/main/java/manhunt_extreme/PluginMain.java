package manhunt_extreme;

import manhunt_extreme.commands.UserInput;
import manhunt_extreme.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public final class PluginMain extends JavaPlugin {

    private Logger logger = Logger.getLogger("manhunt_extreme.PluginMain");
    private World world;
    private GameEngine gameEngine;


//    protected PluginMain(JavaPluginLoader loader, PluginDescriptionFile descriptionFile, File dataFolder, File file) {
//        super(loader, descriptionFile, dataFolder, file);
//    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.gameEngine = new GameEngine(this);
        gameEngine.setWorld(world);
        gameEngine.setLogger(logger);
        logger.info("Manhunt Extreme Plugin Enabled!");
        setWorld();
        registerEvents();
        setCommandExecutor();
    }

    private void setCommandExecutor() {
        UserInput commands = new UserInput(gameEngine);
        for (String command : commands.getRegisteredCommands()) {
            this.getCommand(command).setExecutor(commands);
        }
        logger.info("Commands set");
        gameEngine.setCommands(commands);
    }

    private void registerEvents() {
//        getServer().getPluginManager().registerEvents(new Advancement(getGameEngine()), this);
        getServer().getPluginManager().registerEvents(new Autocomplete(getGameEngine()), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(getGameEngine()), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(getGameEngine()), this);
        getServer().getPluginManager().registerEvents(new EntityDeath(getGameEngine()), this);
        getServer().getPluginManager().registerEvents(new InteractClick(getGameEngine()), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(getGameEngine()), this);
        getServer().getPluginManager().registerEvents(new PiglinTrade(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawn(getGameEngine(), this), this);
        getServer().getPluginManager().registerEvents(new PortalEnter(getGameEngine()), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(getGameEngine()), this);
        getServer().getPluginManager().registerEvents(new QuitEvent(getGameEngine()), this);

        logger.info("Events registered");
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info("Manhunt Extreme Plugin Disabled!");
    }


    private void setWorld() {
        this.world = Bukkit.getWorld("world");
        if (world == null) {
            throw new IllegalArgumentException("WORLD IS NULL");
        }
        this.gameEngine.setWorld(this.world);
    }


    public GameEngine getGameEngine() {
        return this.gameEngine;
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
}
