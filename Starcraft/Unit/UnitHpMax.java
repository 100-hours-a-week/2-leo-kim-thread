package Starcraft.Unit;

public enum UnitHpMax {
    MARINE_HPMAX(40),
    VULTURE_HPMAX(80),
    TANK_HPMAX(150),

    ZERGLING_HPMAX(35),
    HYDRALISK_HPMAX(80),
    MUTALISK_HPMAX(120),

    ZEALOT_HPMAX(100),
    DRAGOON_HPMAX(100),
    REAVER_HPMAX(100);

    private final int hpMax;

    private UnitHpMax(int hpMax) {
        this.hpMax = hpMax;
    }

    public int getHpMax(){
        return hpMax;
    }
}
