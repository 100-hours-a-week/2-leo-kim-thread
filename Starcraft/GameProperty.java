package Starcraft;

public enum GameProperty {
    INIT_TERRAN_MAX_POPULATION(10),
    INIT_NON_TERRAN_MAX_POPULATION(9),
    INIT_POPULATION(4),
    LIMIT_POPULATION(200),
    INIT_MINERALS(200),
    INIT_MINERAL_RATE(32),
    INCREASE_POP(8),
    INCREASE_POP_MINERAL(100),
    INCREASE_MINERAL_RATE(8),
    INCREASE_MINERAL_RATE_MINERAL(50),
    INCREASE_MINERAL_RATE_POP(1),
    INCREASE_GAS_RATE(8),
    INCREASE_GAS_RATE_MINERAL(50),
    INCREASE_GAS_RATE_POP(1)
    ;

    private final int playerPropertyInt;

    private GameProperty(int playerProperty) {
       this.playerPropertyInt = playerProperty;
    }

    public int getPlayerProperty(){
        return playerPropertyInt;
    }
}
