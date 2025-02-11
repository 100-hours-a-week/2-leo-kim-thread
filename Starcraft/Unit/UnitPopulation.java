package Starcraft.Unit;

public enum UnitPopulation {
    MARINE_POP(1),
    VULTURE_POP(2),
    TANK_POP(2),

    ZERGLING_POP(1),
    HYDRALISK_POP(1),
    MUTALISK_POP(2),

    ZEALOT_POP(2),
    DRAGOON_POP(2),
    REAVER_POP(4);

    private final int population;

    private UnitPopulation(int population) {
        this.population = population;
    }

    public int getPopulation(){
        return population;
    }
}
