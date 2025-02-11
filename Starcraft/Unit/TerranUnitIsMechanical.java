package Starcraft.Unit;

public enum TerranUnitIsMechanical {
    MARINE_ISMECHANICAL(false),
    VULTURE_ISMECHANICAL(true),
    TANK_ISMECHANICAL(true),;

    private final boolean isMechanical;

    private TerranUnitIsMechanical(boolean isMechanical) {
        this.isMechanical = isMechanical;
    }

    public boolean isMechanical(){
        return isMechanical;
    }
}
