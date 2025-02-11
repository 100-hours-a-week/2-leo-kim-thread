package Starcraft.Unit;

public enum ProtossUnitMaxShield {
    ZEALOT_MAXSHIELD(60),
    DRAGOON_MAXSHIELD(80),
    REAVER_MAXSHIELD(80)
    ;

    private final int maxShield;

    private ProtossUnitMaxShield(int maxShield) {
        this.maxShield = maxShield;
    }

    public int getMaxShield(){
        return maxShield;
    }
}
