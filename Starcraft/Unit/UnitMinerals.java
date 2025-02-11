package Starcraft.Unit;

public enum UnitMinerals {
    // ---------- 테란 유닛 ----------
    MARINE_MINERAL(50),
    VULTURE_MINERAL(75),
    TANK_MINERAL(150),

    // ---------- 저그 유닛 ----------
    ZERGLING_MINERAL(50),
    HYDRALISK_MINERAL(75),
    MUTALISK_MINERAL(100),

    // ---------- 프로토스 유닛 ----------
    ZEALOT_MINERAL(100),
    DRAGOON_MINERAL(125),
    REAVER_MINERAL(200);

    private final int minerals; // ✅ 필드 추가

    // ✅ Enum 생성자에서 값 저장
    UnitMinerals(int minerals) {
        this.minerals = minerals;
    }

    // ✅ 미네랄 값을 반환하는 메서드
    public int getMinerals() {
        return minerals;
    }
}
