package manhunt_extreme.task_manager;

import manhunt_extreme.PluginMain;

public class TaskManager {
    private final PluginMain pluginMain;

    //Todo: Compass object
    //Todo: BlazeSpawner object
    //Todo: Actionbar object
    //Todo: Supply drop object
    //Todo: Haste object

    //Todo: GameClock
    private GameClock gameClock;
    private ActionBarHandler actionBarHandler;
    private BlazeSpawnerHandler blazeSpawnerHandler;
    private CompassHandler compassHandler;
    private HasteHandler hasteHandler;
    private SupplyDropHandler supplyDropHandler;

    public TaskManager(PluginMain pluginMain) {
        this.pluginMain = pluginMain;
        this.gameClock = new GameClock(pluginMain);
        this.actionBarHandler = new ActionBarHandler(pluginMain);
        this.blazeSpawnerHandler = new BlazeSpawnerHandler(pluginMain);
        this.compassHandler = new CompassHandler(pluginMain);
        this.hasteHandler = new HasteHandler(pluginMain);
        this.supplyDropHandler = new SupplyDropHandler(pluginMain);
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
}
