package Starcraft;

import Starcraft.Player.Player;

import java.util.*;

public class Starcraft {
    private final Map<String, Player> players;
    private final GameMenu gameMenu = GameMenu.getInstance();
    public static Timer resourceTimer;
    public static Timer unitAbilityTimer;
    private static Starcraft starcraft;
    private final Player you;

    private Starcraft(Map<String, Player> playerList, Player you) {
        players = new HashMap<>();
        for (String name : playerList.keySet()) {
            players.put(name, playerList.get(name));
        }
        this.you = you;
    }

    public static Starcraft getStarcraft(Map<String, Player> playerList, Player you) {
        if (starcraft == null) {
            starcraft = new Starcraft(playerList, you);
        } else {
            // 기존 객체가 있더라도 플레이어 목록을 업데이트
            starcraft.players.clear();
            starcraft.players.putAll(playerList);
        }
        return starcraft;
    }

    public void startGame() {
        gameMenu.setPlayers(players);
        resourceTimer = new Timer();
        resourceTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                synchronized (players) {  // 동기화 추가 (Thread-safe)
                    for(String player : players.keySet()) {
                        players.get(player).minerals += players.get(player).mineralRate;
                        players.get(player).gases += players.get(player).gasRate;
                    }
                }
            }
        }, 0, 5000); // 5초마다 실행


        gameMenu.run(you);


        // TODO : 유닛 특수 능력 적용
//            // 유닛 특수 능력 타이머 시작
//            unitAbilityTimer = new Timer();
//            unitAbilityTimer.scheduleAtFixedRate(new TimerTask() {
//                @Override
//                public void run() {
//                    // 각 종족별 특수 능력 실행
//                    for (Unit unit : players.get(playerName).createdUnits.keySet()) {
//                        if (unit instanceof ZergUnits) {
//                            ((ZergUnits) unit).regenerateHp();
//                        } else if (unit instanceof ProtossUnits) {
//                            ((ProtossUnits) unit).regenerateShield();
//                        } else if (unit instanceof TerranUnits) {
//                            if (unit.hp < unit.hpMax * 0.3) { // 체력 30% 미만
//                                unit.hp = Math.max(0, unit.hp - 1);
//                            }
//                        }
//                    }
//                }
//            }, 0, 5000); // 5초마다 실행
//

    }


}