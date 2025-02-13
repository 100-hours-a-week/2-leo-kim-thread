package Starcraft.AiPlayer;

import Starcraft.GameProperty;
import Starcraft.Player.Player;
import Starcraft.Unit.Unit;

public class ComputerAiPlayer extends Player {
    public ComputerAiPlayer(String name, String race) {
        super(name, race);
    }

    @Override
    public String toString() {
        StringBuilder units = new StringBuilder();
        for(Unit unit : createdUnits.keySet()){
            units.append(unit.name).append(" : ").append(createdUnits.get(unit)).append("\n");
        }

        return this.name+"의 상황\n"+
                "미네랄 일꾼 수 : "+(this.mineralRate / GameProperty.INCREASE_MINERAL_RATE.getPlayerProperty()) +"\n"+
                "가스 일꾼 수 : "+(this.gasRate / GameProperty.INCREASE_GAS_RATE.getPlayerProperty())+"\n"+
                "유닛 현황 : \n"+units.toString()+"\n";
    }
}
