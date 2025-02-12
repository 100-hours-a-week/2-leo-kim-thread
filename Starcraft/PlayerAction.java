package Starcraft;

import Starcraft.Unit.Unit;

public class PlayerAction {
    private static final PlayerAction playerAction = new PlayerAction();

    public static PlayerAction getInstance(){
        return playerAction;
    }

    public void increasePopulation(Player player){
        if(player.minerals > GameProperty.INCREASE_POP_MINERAL.getPlayerProperty()){
            player.minerals -= GameProperty.INCREASE_POP_MINERAL.getPlayerProperty();
            player.maxPopulation = Math.min((player.maxPopulation + GameProperty.INCREASE_POP.getPlayerProperty()), GameProperty.LIMIT_POPULATION.getPlayerProperty());
            System.out.println("성공적으로 인구수를 증가시켰습니다.");
            System.out.println("현재 인구수 : "+player.maxPopulation);
            return;
        }
        System.out.println("미네랄이 부족합니다.");
    }

    public void createUnits(Player player, Unit unit, int numOfUnit){
        player.createdUnits.put(unit, player.createdUnits.getOrDefault(unit,0)+numOfUnit);
    }

    public void increaseMineralRate(Player player) {
        if (player.minerals >= GameProperty.INCREASE_MINERAL_RATE_MINERAL.getPlayerProperty()
                && player.curPopulation < player.maxPopulation) {
            player.minerals -= GameProperty.INCREASE_MINERAL_RATE_MINERAL.getPlayerProperty();
            player.mineralRate += GameProperty.INCREASE_MINERAL_RATE.getPlayerProperty();
            player.curPopulation += GameProperty.INCREASE_MINERAL_RATE_POP.getPlayerProperty();
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

        if (player.minerals < GameProperty.INCREASE_MINERAL_RATE_MINERAL.getPlayerProperty()) {
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
        if (player.minerals >= GameProperty.INCREASE_GAS_RATE_MINERAL.getPlayerProperty()
                && player.curPopulation < player.maxPopulation) {
            player.minerals -= GameProperty.INCREASE_GAS_RATE_MINERAL.getPlayerProperty();
            player.gasRate += GameProperty.INCREASE_GAS_RATE.getPlayerProperty();
            player.curPopulation += GameProperty.INCREASE_GAS_RATE_POP.getPlayerProperty();
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
        if (player.minerals < GameProperty.INCREASE_GAS_RATE_MINERAL.getPlayerProperty()) {
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
