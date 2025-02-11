package Starcraft.Unit;

public class ZergUnits extends Unit{
    public ZergUnits(String name, String owner, int hpMax, int minerals, int gases, int population, int damage) {
        super(name, owner, hpMax, minerals, gases, population, damage);
    }

    public void regenerateHp(){
        if(hp != hpMax)
            hp++;
    }
}
