package Starcraft;

import Starcraft.Unit.*;

import java.util.*;

public class Starcraft {
    private final Map<String, Player> players;
    private final PlayerAction playerAction = PlayerAction.getInstance();
    private final GameMenu gameMenu = GameMenu.getInstance();
    public static Timer resourceTimer;
    public static Timer unitAbilityTimer;
    private static Starcraft starcraft;

    Starcraft(Map<String, Player> playerList) {
        players = new HashMap<>();
        for(String name : playerList.keySet()){
            players.put(name, playerList.get(name));
        }
    }


    public static Starcraft getInstance(Map<String, Player> playerList) {
        if (starcraft == null) {
            starcraft = new Starcraft(playerList);
        }
        return starcraft;
    }

    public Map<String, Player> getPlayers(){
        return players;
    }


    public void startGame() {
        // 자원 수집 타이머 시작
        for(String playerName : players.keySet()) {
            resourceTimer = new Timer();
            resourceTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    players.get(playerName).minerals += players.get(playerName).mineralRate;
                    players.get(playerName).gases += players.get(playerName).gasRate;
                }
            }, 0, 5000); // 1초마다 실행

            // 유닛 특수 능력 타이머 시작
            unitAbilityTimer = new Timer();
            unitAbilityTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    // 각 종족별 특수 능력 실행
                    for (Unit unit : players.get(playerName).createdUnits.keySet()) {
                        if (unit instanceof ZergUnits) {
                            ((ZergUnits) unit).regenerateHp();
                        } else if (unit instanceof ProtossUnits) {
                            ((ProtossUnits) unit).regenerateShield();
                        } else if (unit instanceof TerranUnits) {
                            if (unit.hp < unit.hpMax * 0.3) { // 체력 30% 미만
                                unit.hp = Math.max(0, unit.hp - 1);
                            }
                        }
                    }
                }
            }, 0, 5000); // 5초마다 실행

            gameMenu.run(players.get(playerName));
        }
    }


}