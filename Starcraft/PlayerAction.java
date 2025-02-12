package Starcraft;

import Starcraft.Unit.Unit;

public class PlayerAction {
    private static final PlayerAction playerAction = new PlayerAction();

    public static PlayerAction getInstance(){
        return playerAction;
    }

    public boolean increasePopulation(Player player){
        if(player.minerals > GameProperty.INCREASE_POP_MINERAL.getPlayerProperty()){
            player.minerals -= GameProperty.INCREASE_POP_MINERAL.getPlayerProperty();
            player.maxPopulation = Math.min((player.maxPopulation + GameProperty.INCREASE_POP.getPlayerProperty()), GameProperty.LIMIT_POPULATION.getPlayerProperty());
            return true;
        }
        return false;
    }

    public void createUnits(Player player, Unit unit, int numOfUnit){
        player.createdUnits.put(unit, player.createdUnits.getOrDefault(unit,0)+numOfUnit);
    }

    public int increaseMineralRate(Player player) {
        if (player.minerals >= GameProperty.INCREASE_MINERAL_RATE_MINERAL.getPlayerProperty()
                && player.curPopulation < player.maxPopulation) {
            player.minerals -= GameProperty.INCREASE_MINERAL_RATE_MINERAL.getPlayerProperty();
            player.mineralRate += GameProperty.INCREASE_MINERAL_RATE.getPlayerProperty();
            player.curPopulation += GameProperty.INCREASE_MINERAL_RATE_POP.getPlayerProperty();
            return 0;
        }

        if (player.minerals < GameProperty.INCREASE_MINERAL_RATE_MINERAL.getPlayerProperty()) {
            return 1;
        }

        return 2;
    }

    public int increaseGasRate(Player player) {
        if (player.minerals >= GameProperty.INCREASE_GAS_RATE_MINERAL.getPlayerProperty()
                && player.curPopulation < player.maxPopulation) {
            player.minerals -= GameProperty.INCREASE_GAS_RATE_MINERAL.getPlayerProperty();
            player.gasRate += GameProperty.INCREASE_GAS_RATE.getPlayerProperty();
            player.curPopulation += GameProperty.INCREASE_GAS_RATE_POP.getPlayerProperty();
            return 0;
        }
        if (player.minerals < GameProperty.INCREASE_GAS_RATE_MINERAL.getPlayerProperty()) {
            return 1;
        }

        return 2;
    }

}
