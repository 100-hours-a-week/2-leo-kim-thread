package Starcraft;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        BeforeStart beforeStart = new BeforeStart();
        beforeStart.play();
        Player player = beforeStart.player;
        Player enemy = beforeStart.enemy;

        Map<String, Player> players = new HashMap<>();
        players.put(player.name, player);
        players.put(enemy.name, enemy);

        Starcraft starcraft = Starcraft.getStarcraft(players);
        starcraft.startGame();
    }
}
