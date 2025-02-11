package Starcraft.Unit;

public class Unit {
    public String name;
    public String owner;
    public int hpMax;
    public int hp;
    public int minerals;
    public int gases;
    public int population;
    public int damage;

    public Unit(String name, String owner, int hpMax, int minerals, int gases, int population, int damage){
        this.name = name;
        this.owner = owner;
        this.hpMax = hpMax;
        this.hp = hpMax;
        this.minerals = minerals;
        this.gases = gases;
        this.population = population;
        this.damage = damage;


    }

    void attack(){

    }

    void die(){

    }

    // createdUnit 맵을 사용하기 위한 equals, hashcode 매서드
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Unit unit = (Unit) obj;
        return name.equals(unit.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
