package Starcraft.Player;

import Starcraft.GameProperty;
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

    public Player(String name, String race) {
        this.name = name;
        this.race = race;
        minerals = GameProperty.INIT_MINERALS.getPlayerProperty();
        mineralRate = GameProperty.INIT_MINERAL_RATE.getPlayerProperty();
        curPopulation = GameProperty.INIT_POPULATION.getPlayerProperty();
        if (race.equals("Terran")) {
            maxPopulation = GameProperty.INIT_TERRAN_MAX_POPULATION.getPlayerProperty();
        } else {
            maxPopulation = GameProperty.INIT_NON_TERRAN_MAX_POPULATION.getPlayerProperty();
        }
        createdUnits = new HashMap<>();
    }
}
