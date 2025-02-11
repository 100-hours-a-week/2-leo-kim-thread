package Starcraft;

import Starcraft.Unit.Unit;

public class PlayerAction {
    private static final PlayerAction playerAction = new PlayerAction();

    public void increasePopulation(Player player){
        if(player.minerals > 100){
            player.minerals -= 100;
            player.maxPopulation = Math.min((player.maxPopulation + 8), 200);
            System.out.println("성공적으로 인구수를 증가시켰습니다.");
            System.out.println("현재 인구수 : "+player.maxPopulation);
            return;
        }
        System.out.println("미네랄이 부족합니다.");
    }

    public static PlayerAction getInstance(){
        return playerAction;
    }

    public void createUnits(Player player, Unit unit, int numOfUnit){
        player.createdUnits.put(unit, player.createdUnits.getOrDefault(unit,0)+numOfUnit);
    }

    public void increaseMineralRate(Player player) {
        if (player.minerals >= 50 && player.curPopulation < player.maxPopulation) {
            player.minerals -= 50;
            player.mineralRate += 8;
            player.curPopulation++;
            String worker = "";
            if (player.race.equals("Terran")) {
                worker = "SCV";
            } else if (player.race.equals("Zerg")) {
                worker = "Drone";
            } else {
                worker = "Probe";
            }
            System.out.println("성공적으로 " + worker + "를 추가하였습니다.");
            System.out.println("현재 미네랄 채취율 : " + player.mineralRate + "/sec");
            return;
        }
        if (player.minerals < 50) {
            System.out.println("일꾼을 뽑을 미네랄이 부족합니다.");
            return;
        }

        String supply = "";
        if (player.race.equals("Terran")) {
            supply = "Supply Depot";
        } else if (player.race.equals("Zerg")) {
            supply = "Overload";
        } else {
            supply = "Pylon";
        }
        System.out.println("최대 인구수입니다. " + supply + "를 더 추가하십시오.");
    }

    public void increaseGasRate(Player player) {
        if (player.minerals >= 50 && player.curPopulation < player.maxPopulation) {
            player.minerals -= 50;
            player.gasRate += 8;
            player.curPopulation++;
            String worker = "";
            if (player.race.equals("Terran")) {
                worker = "SCV";
            } else if (player.race.equals("Zerg")) {
                worker = "Drone";
            } else {
                worker = "Probe";
            }
            System.out.println("성공적으로 " + worker + "를 추가하였습니다.");
            System.out.println("현재 가스 채취율 : " + player.gasRate + "/sec");
            return;
        }
        if (player.minerals < 50) {
            System.out.println("일꾼을 뽑을 미네랄이 부족합니다.");
            return;
        }

        String supply = "";
        if (player.race.equals("Terran")) {
            supply = "Supply Depot";
        } else if (player.race.equals("Zerg")) {
            supply = "Overload";
        } else {
            supply = "Pylon";
        }
        System.out.println("최대 인구수입니다. " + supply + "를 더 추가하십시오.");
    }

}
