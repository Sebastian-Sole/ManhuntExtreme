package manhunt_extreme.listeners;

import manhunt_extreme.GameEngine;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;

import java.util.List;

public class Autocomplete implements Listener {

    private GameEngine gameEngine;


    public Autocomplete(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @EventHandler
    public void onAutocomplete(TabCompleteEvent event) {
        String buffer = event.getBuffer();
        if (!buffer.startsWith("/")) return;
        String[] args = buffer.split(" ");

        List<String> completions = gameEngine.getCommands().getCompletions(args, event.getCompletions());

        event.setCompletions(completions);
    }
}
