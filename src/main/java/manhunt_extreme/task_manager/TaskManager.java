package manhunt_extreme.task_manager;

import manhunt_extreme.GameEngine;
import manhunt_extreme.PluginMain;

public class TaskManager {
    private final PluginMain pluginMain;
    private GameClock gameClock;
    private ActionBarHandler actionBarHandler;
    private BlazeSpawnerHandler blazeSpawnerHandler;
    private CompassHandler compassHandler;
    private HasteHandler hasteHandler;
    private SupplyDropHandler supplyDropHandler;
    private CompassJammer compassJammer;

    public TaskManager(PluginMain pluginMain, GameEngine gameEngine) {
        this.pluginMain = pluginMain;
        this.gameClock = new GameClock(pluginMain);
        this.actionBarHandler = new ActionBarHandler(pluginMain, gameEngine);
        this.blazeSpawnerHandler = new BlazeSpawnerHandler(pluginMain);
        this.compassHandler = new CompassHandler(pluginMain, gameEngine);
        this.hasteHandler = new HasteHandler(pluginMain, gameEngine);
        this.supplyDropHandler = new SupplyDropHandler(pluginMain, gameEngine);
        this.compassJammer = new CompassJammer(pluginMain, gameEngine);
    }


    public GameClock getGameClock() {
        return gameClock;
    }

    public void setGameClock(GameClock gameClock) {
        this.gameClock = gameClock;
    }

    public ActionBarHandler getActionBarHandler() {
        return actionBarHandler;
    }

    public void setActionBarHandler(ActionBarHandler actionBarHandler) {
        this.actionBarHandler = actionBarHandler;
    }

    public BlazeSpawnerHandler getBlazeSpawnerHandler() {
        return blazeSpawnerHandler;
    }

    public void setBlazeSpawnerHandler(BlazeSpawnerHandler blazeSpawnerHandler) {
        this.blazeSpawnerHandler = blazeSpawnerHandler;
    }

    public CompassHandler getCompassHandler() {
        return compassHandler;
    }

    public void setCompassHandler(CompassHandler compassHandler) {
        this.compassHandler = compassHandler;
    }

    public HasteHandler getHasteHandler() {
        return hasteHandler;
    }

    public void setHasteHandler(HasteHandler hasteHandler) {
        this.hasteHandler = hasteHandler;
    }

    public SupplyDropHandler getSupplyDropHandler() {
        return supplyDropHandler;
    }

    public void setSupplyDropHandler(SupplyDropHandler supplyDropHandler) {
        this.supplyDropHandler = supplyDropHandler;
    }

    public CompassJammer getCompassJammer() {
        return compassJammer;
    }

    public void setCompassJammer(CompassJammer compassJammer) {
        this.compassJammer = compassJammer;
    }
}
