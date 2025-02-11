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

    Player(String name, String race) {
        this.name = name;
        this.race = race;
        minerals = 200;
        mineralRate = 32;
        curPopulation = 4;
        if (race.equals("Terran")) {
            maxPopulation = 10;
        } else {
            maxPopulation = 9;
        }
        createdUnits = new HashMap<>();
    }
}
