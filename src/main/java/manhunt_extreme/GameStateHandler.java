package manhunt_extreme;

public class GameStateHandler {

    private boolean extraDrops = true;
    private boolean chestGenerate = true;
    private boolean runnerHelp = true;
    private boolean hunterHelp = true;
    private boolean hasteBoost = true;
    private boolean cutClean = true;
    private double health = 40.0;
    private double borderSize = 1300.0;
    private boolean supplyDrops = true;
    private double headStart = 30.0;

    public GameStateHandler() {

    }

    public boolean isExtraDrops() {
        return extraDrops;
    }

    public void setExtraDrops(boolean extraDrops) {
        this.extraDrops = extraDrops;
    }

    public boolean isChestGenerate() {
        return chestGenerate;
    }

    public void setChestGenerate(boolean chestGenerate) {
        this.chestGenerate = chestGenerate;
    }

    public boolean isRunnerHelp() {
        return runnerHelp;
    }

    public void setRunnerHelp(boolean runnerHelp) {
        this.runnerHelp = runnerHelp;
    }

    public boolean isHunterHelp() {
        return hunterHelp;
    }

    public void setHunterHelp(boolean hunterHelp) {
        this.hunterHelp = hunterHelp;
    }

    public boolean isHasteBoost() {
        return hasteBoost;
    }

    public void setHasteBoost(boolean hasteBoost) {
        this.hasteBoost = hasteBoost;
    }

    public boolean isCutClean() {
        return cutClean;
    }

    public void setCutClean(boolean cutClean) {
        this.cutClean = cutClean;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(double borderSize) {
        this.borderSize = borderSize;
    }

    public boolean isSupplyDrops() {
        return supplyDrops;
    }

    public void setSupplyDrops(boolean supplyDrops) {
        this.supplyDrops = supplyDrops;
    }

    public double getHeadStart() {
        return headStart;
    }

    public void setHeadStart(double headStart) {
        this.headStart = headStart;
    }
}
