package Starcraft;

import Starcraft.Unit.Unit;

public class ComputerAiPlayerAction extends PlayerAction implements Runnable {
    private final Player enemy;

    public ComputerAiPlayerAction(Player enemy){
        this.enemy = enemy;
    }

    @Override
    public void run() {


        while(true){
            try {

                boolean actionSuccess = false;

                while(actionSuccess){
                    int action = (int)(Math.random()*4);
                    switch (action){
                        case 0:
//                            createUnits()
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                    }

                }


                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void createUnits(Player player, Unit unit, int numOfUnit) {
        super.createUnits(player, unit, numOfUnit);
    }

//    @Override
//    public void increaseGasRate(Player player) {
//        super.increaseGasRate(player);
//    }
//
//    @Override
//    public void increaseMineralRate(Player player) {
//        super.increaseMineralRate(player);
//    }
//
//    @Override
//    public void increasePopulation(Player player) {
//        super.increasePopulation(player);
//    }
}
