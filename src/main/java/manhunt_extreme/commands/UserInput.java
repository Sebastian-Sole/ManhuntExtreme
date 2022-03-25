package manhunt_extreme.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class UserInput implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (label) {
            case "hunter" -> {
                new HunterCommand();
            }
        }

        return false;
    }

}
