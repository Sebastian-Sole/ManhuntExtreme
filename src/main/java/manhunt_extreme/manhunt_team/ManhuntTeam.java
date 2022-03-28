package manhunt_extreme.manhunt_team;

import manhunt_extreme.manhunt_player.ManhuntPlayer;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ManhuntTeam {

    private ArrayList<ManhuntPlayer> playerList;
    private HashMap<ManhuntPlayer, Integer> playerDeaths = new HashMap<>();

    public void addPlayerDeath(ManhuntPlayer manhuntPlayer) {
        var deathCount = playerDeaths.get(manhuntPlayer);
        playerDeaths.put(manhuntPlayer, deathCount + 1);
    }

    public ArrayList<ManhuntPlayer> getPlayerList() {
        return playerList;
    }

    public HashMap<ManhuntPlayer, Integer> getPlayerDeaths() {
        return playerDeaths;
    }

    public void addPlayer(ManhuntPlayer manhuntPlayer) {
        playerList.add(manhuntPlayer);
        playerDeaths.put(manhuntPlayer, 0);
        manhuntPlayer.setTeam(this);
    }

    public void removePlayer(ManhuntPlayer manhuntPlayer) {
        playerList.remove(manhuntPlayer);
        playerDeaths.remove(manhuntPlayer);
    }
}
