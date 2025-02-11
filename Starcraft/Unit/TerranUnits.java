package Starcraft.Unit;

public class TerranUnits extends Unit{
    boolean isMechanical;

    public TerranUnits(String name, String owner, int hpMax, int minerals, int gases, int population, int damage, boolean isMechanical) {
        super(name, owner, hpMax, minerals, gases, population, damage);
        this.isMechanical = isMechanical;
    }

    void repair(){
        if(isMechanical) {
            int mineralCost = (int) (minerals * (hpMax - hp) / (double) hpMax);
            int gasCost = (int) (gases * (hpMax - hp) / (double) hpMax);
            hp = hpMax;
        }
    }
}
