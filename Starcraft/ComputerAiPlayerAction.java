package Starcraft;

import Starcraft.Unit.EnumUnitFactory;
import Starcraft.Unit.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComputerAiPlayerAction extends PlayerAction implements Runnable {
    private final Player enemy;

    public ComputerAiPlayerAction(Player enemy){
        this.enemy = enemy;
    }

    @Override
    public void run() {
        // 코드 단순화를 위한 유닛 종족별, 종족별 인덱스 부여
        List<String>[] unitList = new ArrayList[3];
        for (int i = 0; i < 3; i++) {
            unitList[i] = new ArrayList<>();
        }
        Map<String, Integer> races = new HashMap<>();
        races.put("Terran", 0);
        races.put("Zerg",1);
        races.put("Protoss",2);

        unitList[0].addAll(List.of("MARINE", "VULTURE", "TANK"));
        unitList[1].addAll(List.of("ZERGLING", "HYDRALISK", "MUTALISK"));
        unitList[2].addAll(List.of("ZEALOT", "DRAGOON", "REAVER"));

        while(true){
            try {

                boolean actionSuccess = false;
                Thread.sleep(500);

                // Random을 이용한 컴퓨터 AI 움직임
                while(!actionSuccess){

                    // 돈이 없으면 무한루프 돌아버리는 것 같아 예외처리(아무것도 할 수 없는 상황 : mineral < 50)
                    if(enemy.minerals < 50) {
                        Thread.sleep(5000);
                        continue;
                    }

                    if(enemy.curPopulation == GameProperty.LIMIT_POPULATION.getPlayerProperty()){
                        // TODO : 인구수가 가득찼을떄 어떻게 처리할까?
                        continue;
                    }

                    if(enemy.curPopulation == enemy.maxPopulation){
                        boolean resultPop = increasePopulation(enemy);
                        if (resultPop) {
                            actionSuccess = true;
                        }
                        continue;
                    }

                    int action = (int)(Math.random()*3);
                    switch (action){

                        // EnumFactory를 이용해 enemy 소유의 unit을 만들어 PlayerAction의 createUnits 호출
                        case 0:
                            int randomUnitIdx = (int)(Math.random()*unitList[races.get(enemy.race)].size());
                            String unitName = unitList[races.get(enemy.race)].get(randomUnitIdx);
                            EnumUnitFactory unitFactory = EnumUnitFactory.valueOf(unitName);
                            Unit unit = unitFactory.createUnit(enemy);

                            // 미네랄 및 가스 조건 확인 후 생성
                            if (unitFactory.checkMineral(enemy) && unitFactory.checkGas(enemy) && unitFactory.checkPopulation(enemy)) {
                                createUnits(enemy, unit, 1);
                                enemy.curPopulation += unit.population;
                                enemy.minerals -= unit.minerals;
                                enemy.gases -= unit.gases;
                                actionSuccess = true;
                            }
                            break;

                        // Mineral Rate를 올리는 logic을 PlayerAction의 increaseMineralRate호출
                        case 1:
                            int resultMineral = increaseMineralRate(enemy);
                            if (resultMineral == 0) {
                                actionSuccess = true;
                            }
                            break;

                        // Gas Rate를 올리는 logic을 PlayerAction의 increaseGasRate호출
                        case 2:
                            int resultGas = increaseGasRate(enemy);
                            if (resultGas == 0) {
                                actionSuccess = true;
                            }
                            break;

                    }

                }

                // 5초마다 행동
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
