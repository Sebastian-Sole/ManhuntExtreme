package manhunt_extreme.commands;

import manhunt_extreme.GameEngine;
import manhunt_extreme.manhunt_player.ManhuntPlayer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CompassCommand {

    private ManhuntPlayer manhuntPlayer;
    private String[] args;
    private GameEngine gameEngine;

    public CompassCommand(ManhuntPlayer manhuntPlayer, String[] args, GameEngine gameEngine) {
        this.manhuntPlayer = manhuntPlayer;
        this.args = args;
        this.gameEngine = gameEngine;
    }

    public boolean execute() {
        if (args.length != 0) {
            manhuntPlayer.getPlayer().sendMessage("Illegal format, please use /compass");
            return true;
        }
        manhuntPlayer.getPlayer().getInventory().addItem(new ItemStack(Material.COMPASS, 1));
        manhuntPlayer.getPlayer().sendMessage("Here you go!");
        return true;
    }
}
