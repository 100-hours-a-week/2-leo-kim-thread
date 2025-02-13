package Starcraft.Unit.UnitProperties;

public enum UnitDamage {
    MARINE_DAMAGE(6),
    VULTURE_DAMAGE(20),
    TANK_DAMAGE(30),

    ZERGLING_DAMAGE(5),
    HYDRALISK_DAMAGE(10),
    MUTALISK_DAMAGE(9),

    ZEALOT_DAMAGE(16),
    DRAGOON_DAMAGE(20),
    REAVER_DAMAGE(100);

    private final int damage;

    private UnitDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
