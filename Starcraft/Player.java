package Starcraft;

import Starcraft.Unit.Unit;

import java.util.HashMap;
import java.util.Map;

public class Player {
    public String name;
    public String race;
    public int minerals;
    public int gases;
    public int mineralRate;
    public int gasRate;
    public int maxPopulation;
    public int curPopulation;
    public Map<Unit, Integer> createdUnits;

    Player(String name, String race){
        this.name = name;
        this.race = race;
        minerals = 200;
        mineralRate = 32;
        curPopulation = 4;
        if(race.equals("Terran")){
            maxPopulation = 10;
        }
        else{
            maxPopulation = 9;
        }
        createdUnits = new HashMap<>();
    }

    public void increasePopulation(){
        if(minerals > 100){
            minerals -= 100;
            maxPopulation = Math.min((maxPopulation + 8), 200);
            System.out.println("성공적으로 인구수를 증가시켰습니다.");
            System.out.println("현재 인구수 : "+maxPopulation);
            return;
        }
        System.out.println("미네랄이 부족합니다.");
    }


    void createUnits(Unit unit, int numOfUnit){
        createdUnits.put(unit, createdUnits.getOrDefault(unit,0)+numOfUnit);
    }

    public void increaseMineralRate() {
        if(minerals >= 50 && curPopulation < maxPopulation){
            minerals -= 50;
            mineralRate += 8;
            curPopulation++;
            String worker = "";
            if(race.equals("Terran")){
                worker = "SCV";
            }
            else if(race.equals("Zerg")){
                worker = "Drone";
            }
            else{
                worker = "Probe";
            }
            System.out.println("성공적으로 "+worker+"를 추가하였습니다.");
            System.out.println("현재 미네랄 채취율 : "+mineralRate+"/sec");
            return;
        }
        if(minerals < 50){
            System.out.println("일꾼을 뽑을 미네랄이 부족합니다.");
            return;
        }

        String supply = "";
        if(race.equals("Terran")){
            supply = "Supply Depot";
        }
        else if(race.equals("Zerg")){
            supply = "Overload";
        }
        else{
            supply = "Pylon";
        }
        System.out.println("최대 인구수입니다. "+supply+"를 더 추가하십시오.");
    }

    public void increaseGasRate() {
        if(minerals >= 50 && curPopulation < maxPopulation){
            minerals -= 50;
            gasRate += 8;
            curPopulation++;
            String worker = "";
            if(race.equals("Terran")){
                worker = "SCV";
            }
            else if(race.equals("Zerg")){
                worker = "Drone";
            }
            else{
                worker = "Probe";
            }
            System.out.println("성공적으로 "+worker+"를 추가하였습니다.");
            System.out.println("현재 가스 채취율 : "+gasRate+"/sec");
            return;
        }
        if(minerals < 50){
            System.out.println("일꾼을 뽑을 미네랄이 부족합니다.");
            return;
        }

        String supply = "";
        if(race.equals("Terran")){
            supply = "Supply Depot";
        }
        else if(race.equals("Zerg")){
            supply = "Overload";
        }
        else{
            supply = "Pylon";
        }
        System.out.println("최대 인구수입니다. "+supply+"를 더 추가하십시오.");
    }
}
