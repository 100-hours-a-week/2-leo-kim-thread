package Starcraft.Unit.Factory;

import Starcraft.Unit.ProtossUnits;
import Starcraft.Unit.TerranUnits;
import Starcraft.Unit.Unit;
import Starcraft.Unit.ZergUnits;

public enum EnumUnitFactory {
    MARINE{
        public Unit createUnit(String owner) {
            return new TerranUnits("Marine", owner, 40, 50, 0, 1, 6, false);
        }
    },
    VULTURE{
        public Unit createUnit(String owner) {
            return new TerranUnits("Vulture", owner, 80, 75, 0, 2, 20, true);
        }

    },
    TANK{
        public Unit createUnit(String owner){
            return new TerranUnits("Tank", owner, 150, 150, 100, 2, 30, true);
        }

    },
    // --------- 여기까지 테란 유닛 -----------

    ZERGLING{
        public Unit createUnit(String owner) {
            return new ZergUnits("Zergling", owner, 35, 50, 0, 1, 5);
        }
    },
    HYDRALISK{
        public Unit createUnit(String owner) {
            return new ZergUnits("Hydralisk", owner, 80, 75, 25, 1, 10);
        }

    },
    MUTALISK{
        public Unit createUnit(String owner) {
            return new ZergUnits("Mutalisk", owner, 120, 100, 100, 2, 9);
        }

    },

    // -------- 여기까지 테란 유닛 -----------
    ZEALOT {
        public Unit createUnit(String owner) {
            return new ProtossUnits("Zealot", owner, 100, 100, 0, 2, 16, 60);
        }

    },
    DRAGOON{
        public Unit createUnit(String owner) {
            return new ProtossUnits("Dragoon", owner, 100, 125, 50, 2, 20, 80);
        }
    },
    REAVER{
        public Unit createUnit(String owner) {
            return new ProtossUnits("Reaver", owner, 100, 200, 100, 4, 100, 80);
        }
    },

    // ---------- 여기까지 프로토스 유닛 ----------
}

