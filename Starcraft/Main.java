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

        Starcraft starcraft = Starcraft.getStarcraft(players,player);

        // AI 행동을 위한 스레드 실행
        Thread aiThread = new Thread(new ComputerAiPlayerAction(enemy));
        aiThread.start();

        BgmPlayer.playBgm(player.race);

        starcraft.startGame();
    }
}
