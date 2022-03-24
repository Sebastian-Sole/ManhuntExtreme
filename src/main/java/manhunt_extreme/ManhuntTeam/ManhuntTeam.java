package manhunt_extreme.ManhuntTeam;

import manhunt_extreme.ManhuntPlayer;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ManhuntTeam {

    private ArrayList<ManhuntPlayer> playerList;
    private HashMap<ManhuntPlayer, Integer> playerDeath = new HashMap<>();

    public ArrayList<ManhuntPlayer> getPlayerList() {
        return playerList;
    }

    public HashMap<ManhuntPlayer, Integer> getPlayerDeath() {
        return playerDeath;
    }

    public void addPlayer(ManhuntPlayer manhuntPlayer) {
        playerList.add(manhuntPlayer);
        playerDeath.put(manhuntPlayer, 0);
        manhuntPlayer.setTeam(this);
    }

    public void removePlayer(ManhuntPlayer manhuntPlayer) {
        playerList.remove(manhuntPlayer);
        playerDeath.remove(manhuntPlayer);
    }
}
