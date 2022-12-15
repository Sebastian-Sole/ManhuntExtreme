package task_manager;

import manhunt_extreme.GameEngine;
import manhunt_extreme.PluginMain;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class CompassJammerTest {

    GameEngine gameEngine;

    @BeforeEach
    public void setUp() {
        PluginMain pluginMain = mock(PluginMain.class);
        gameEngine = new GameEngine(pluginMain);
    }

    @Test
    public void testThrowWhenNotEnabled() {
        try (MockedStatic<Bukkit> mocked = mockStatic(Bukkit.class)) {
            mocked.when(Bukkit::getServer).thenReturn(mock(org.bukkit.Server.class));
            mocked.when(Bukkit::getScheduler).thenReturn(mock(BukkitScheduler.class));
            gameEngine.getGameStateHandler().setAllowJamming(false);
            assert gameEngine.getTaskManager() != null;
            assert gameEngine.getTaskManager().getCompassJammer() != null;
            assert !gameEngine.getGameStateHandler().isAllowJamming();
            assertThrows(IllegalArgumentException.class, () -> gameEngine.getTaskManager().getCompassJammer().jamCompass(10));
        }

    }

    @Test
    public void testValidJam() {
        try (MockedStatic<Bukkit> mocked = mockStatic(Bukkit.class)) {
            mocked.when(Bukkit::getServer).thenReturn(mock(org.bukkit.Server.class));
            mocked.when(Bukkit::getScheduler).thenReturn(mock(BukkitScheduler.class));

            gameEngine.getGameStateHandler().setAllowJamming(true);
            gameEngine.getTaskManager().getCompassJammer().jamCompass(1200L);
            assert gameEngine.isCompassJammed();
        }


    }

}
