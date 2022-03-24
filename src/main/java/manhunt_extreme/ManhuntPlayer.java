package manhunt_extreme;

import org.bukkit.entity.Player;

public class ManhuntPlayer {

    final private Player player;
    private double achievementScore = 0.0;
    private double deaths = 0.0;
    private double kills = 0.0;
    private double chestsGenerated = 0.0;

    public ManhuntPlayer(Player player) {
        this.player = player;
    }

    
}
