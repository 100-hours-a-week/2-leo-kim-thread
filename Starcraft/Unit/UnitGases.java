package Starcraft.Unit;

public enum UnitGases {
    MARINE_GAS(0),
    VULTURE_GAS(0),
    TANK_GAS(100),

    ZERGLING_GAS(0),
    HYDRALISK_GAS(25),
    MUTALISK_GAS(100),

    ZEALOT_GAS(0),
    DRAGOON_GAS(50),
    REAVER_GAS(100),;

    private final int gases;

    private UnitGases(int gases) {
        this.gases = gases;
    }

    public int getGases(){
        return gases;
    }
}
