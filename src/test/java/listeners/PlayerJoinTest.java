package listeners;

import manhunt_extreme.GameEngine;
import manhunt_extreme.PluginMain;
import manhunt_extreme.listeners.PlayerJoin;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class PlayerJoinTest {

    GameEngine gameEngine;
    PlayerJoinEvent event;
    PlayerJoin playerJoin;

    @BeforeEach
    public void setUp() {
        PluginMain pluginMain = mock(PluginMain.class);
        gameEngine = new GameEngine(pluginMain);
        event = mock(PlayerJoinEvent.class);
        playerJoin = new PlayerJoin(gameEngine);
    }

    @Test
    public void testOnPlayerJoin() {
        Player player = mock(Player.class);
        playerJoin.onPlayerJoin(event);
        Assertions.assertEquals(1, gameEngine.getManhuntPlayers().size());
    }
}
