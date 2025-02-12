package Starcraft;

import Starcraft.Unit.Unit;

import java.util.Random;

public class ComputerAiPlayer extends Player{
    ComputerAiPlayer(String name, String race) {
        super(name, race);
    }

    @Override
    public String toString() {
        StringBuilder units = new StringBuilder();
        for(Unit unit : createdUnits.keySet()){
            units.append(unit.name).append(" : ").append(createdUnits.get(unit)).append("\n");
        }

        return this.name+"의 상황\n"+
                "미네랄 채취율 : "+this.mineralRate+"\n"+
                "가스 채취율 : "+this.gasRate+"\n"+
                "유닛 현황 : \n"+units.toString()+"\n";
    }
}
