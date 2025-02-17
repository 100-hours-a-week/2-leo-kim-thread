package Starcraft;

public enum PopulationName {
    PROTOSS_POP("Pylon"),
    TERRAN_POP("Supply Depot"),
    ZERG_POP("Overload");

    private final String popName;

    private PopulationName(String popName) {
        this.popName = popName;
    }

    public String getPopName() {
        return popName;
    }
}
