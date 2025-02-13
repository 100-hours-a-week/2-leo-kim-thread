package Starcraft.Unit;

import Starcraft.Player.Player;
import Starcraft.Unit.UnitProperties.*;

public enum EnumUnitFactory {
    // ---------- 테란 유닛 ----------
    MARINE {
        public Unit createUnit(Player player) {
            return new TerranUnits("Marine", player.name,
                    UnitHpMax.MARINE_HPMAX.getHpMax(),
                    UnitMinerals.MARINE_MINERAL.getMinerals(),
                    UnitGases.MARINE_GAS.getGases(),
                    UnitPopulation.MARINE_POP.getPopulation(),
                    UnitDamage.MARINE_DAMAGE.getDamage(),
                    TerranUnitIsMechanical.MARINE_ISMECHANICAL.isMechanical());
        }

        @Override
        public boolean checkMineral(Player player) {
            return player.minerals >= UnitMinerals.MARINE_MINERAL.getMinerals();
        }

        @Override
        public boolean checkGas(Player player) {
            return player.gases >= UnitGases.MARINE_GAS.getGases();
        }

        @Override
        public boolean checkPopulation(Player player) {
            return player.curPopulation + UnitPopulation.MARINE_POP.getPopulation() <= player.maxPopulation;
        }
    },
    VULTURE {
        public Unit createUnit(Player player) {
            return new TerranUnits("Vulture", player.name,
                    UnitHpMax.VULTURE_HPMAX.getHpMax(),
                    UnitMinerals.VULTURE_MINERAL.getMinerals(),
                    UnitGases.VULTURE_GAS.getGases(),
                    UnitPopulation.VULTURE_POP.getPopulation(),
                    UnitDamage.VULTURE_DAMAGE.getDamage(),
                    TerranUnitIsMechanical.VULTURE_ISMECHANICAL.isMechanical());
        }

        @Override
        public boolean checkMineral(Player player) {
            return player.minerals >= UnitMinerals.VULTURE_MINERAL.getMinerals();
        }

        @Override
        public boolean checkGas(Player player) {
            return player.gases >= UnitGases.VULTURE_GAS.getGases();
        }

        @Override
        public boolean checkPopulation(Player player) {
            return player.curPopulation + UnitPopulation.VULTURE_POP.getPopulation() <= player.maxPopulation;
        }
    },
    TANK {
        public Unit createUnit(Player player) {
            return new TerranUnits("Tank", player.name,
                    UnitHpMax.TANK_HPMAX.getHpMax(),
                    UnitMinerals.TANK_MINERAL.getMinerals(),
                    UnitGases.TANK_GAS.getGases(),
                    UnitPopulation.TANK_POP.getPopulation(),
                    UnitDamage.TANK_DAMAGE.getDamage(),
                    TerranUnitIsMechanical.TANK_ISMECHANICAL.isMechanical());
        }

        @Override
        public boolean checkMineral(Player player) {
            return player.minerals >= UnitMinerals.TANK_MINERAL.getMinerals();
        }

        @Override
        public boolean checkGas(Player player) {
            return player.gases >= UnitGases.TANK_GAS.getGases();
        }

        @Override
        public boolean checkPopulation(Player player) {
            return player.curPopulation + UnitPopulation.TANK_POP.getPopulation() <= player.maxPopulation;
        }
    },

    // ---------- 저그 유닛 ----------
    ZERGLING {
        public Unit createUnit(Player player) {
            return new ZergUnits("Zergling", player.name,
                    UnitHpMax.ZERGLING_HPMAX.getHpMax(),
                    UnitMinerals.ZERGLING_MINERAL.getMinerals(),
                    UnitGases.ZERGLING_GAS.getGases(),
                    UnitPopulation.ZERGLING_POP.getPopulation(),
                    UnitDamage.ZERGLING_DAMAGE.getDamage());
        }

        @Override
        public boolean checkMineral(Player player) {
            return player.minerals >= UnitMinerals.ZERGLING_MINERAL.getMinerals();
        }

        @Override
        public boolean checkGas(Player player) {
            return player.gases >= UnitGases.ZERGLING_GAS.getGases();
        }

        @Override
        public boolean checkPopulation(Player player) {
            return player.curPopulation + UnitPopulation.ZERGLING_POP.getPopulation() <= player.maxPopulation;
        }
    },
    HYDRALISK {
        public Unit createUnit(Player player) {
            return new ZergUnits("Hydralisk", player.name,
                    UnitHpMax.HYDRALISK_HPMAX.getHpMax(),
                    UnitMinerals.HYDRALISK_MINERAL.getMinerals(),
                    UnitGases.HYDRALISK_GAS.getGases(),
                    UnitPopulation.HYDRALISK_POP.getPopulation(),
                    UnitDamage.HYDRALISK_DAMAGE.getDamage());
        }

        @Override
        public boolean checkMineral(Player player) {
            return player.minerals >= UnitMinerals.HYDRALISK_MINERAL.getMinerals();
        }

        @Override
        public boolean checkGas(Player player) {
            return player.gases >= UnitGases.HYDRALISK_GAS.getGases();
        }

        @Override
        public boolean checkPopulation(Player player) {
            return player.curPopulation + UnitPopulation.HYDRALISK_POP.getPopulation() <= player.maxPopulation;
        }
    },
    MUTALISK {
        public Unit createUnit(Player player) {
            return new ZergUnits("Mutalisk", player.name,
                    UnitHpMax.MUTALISK_HPMAX.getHpMax(),
                    UnitMinerals.MUTALISK_MINERAL.getMinerals(),
                    UnitGases.MUTALISK_GAS.getGases(),
                    UnitPopulation.MUTALISK_POP.getPopulation(),
                    UnitDamage.MUTALISK_DAMAGE.getDamage());
        }

        @Override
        public boolean checkMineral(Player player) {
            return player.minerals >= UnitMinerals.MUTALISK_MINERAL.getMinerals();
        }

        @Override
        public boolean checkGas(Player player) {
            return player.gases >= UnitGases.MUTALISK_GAS.getGases();
        }

        @Override
        public boolean checkPopulation(Player player) {
            return player.curPopulation + UnitPopulation.MUTALISK_POP.getPopulation() <= player.maxPopulation;
        }
    },

    // ---------- 프로토스 유닛 ----------
    ZEALOT {
        public Unit createUnit(Player player) {
            return new ProtossUnits("Zealot", player.name,
                    UnitHpMax.ZEALOT_HPMAX.getHpMax(),
                    UnitMinerals.ZEALOT_MINERAL.getMinerals(),
                    UnitGases.ZEALOT_GAS.getGases(),
                    UnitPopulation.ZEALOT_POP.getPopulation(),
                    UnitDamage.ZEALOT_DAMAGE.getDamage(),
                    ProtossUnitMaxShield.ZEALOT_MAXSHIELD.getMaxShield());
        }

        @Override
        public boolean checkMineral(Player player) {
            return player.minerals >= UnitMinerals.ZEALOT_MINERAL.getMinerals();
        }

        @Override
        public boolean checkGas(Player player) {
            return player.gases >= UnitGases.ZEALOT_GAS.getGases();
        }

        @Override
        public boolean checkPopulation(Player player) {
            return player.curPopulation + UnitPopulation.ZEALOT_POP.getPopulation() <= player.maxPopulation;
        }
    },
    DRAGOON {
        public Unit createUnit(Player player) {
            return new ProtossUnits("Dragoon", player.name,
                    UnitHpMax.DRAGOON_HPMAX.getHpMax(),
                    UnitMinerals.DRAGOON_MINERAL.getMinerals(),
                    UnitGases.DRAGOON_GAS.getGases(),
                    UnitPopulation.DRAGOON_POP.getPopulation(),
                    UnitDamage.DRAGOON_DAMAGE.getDamage(),
                    ProtossUnitMaxShield.DRAGOON_MAXSHIELD.getMaxShield());
        }

        @Override
        public boolean checkMineral(Player player) {
            return player.minerals >= UnitMinerals.DRAGOON_MINERAL.getMinerals();
        }

        @Override
        public boolean checkGas(Player player) {
            return player.gases >= UnitGases.DRAGOON_GAS.getGases();
        }

        @Override
        public boolean checkPopulation(Player player) {
            return player.curPopulation + UnitPopulation.DRAGOON_POP.getPopulation() <= player.maxPopulation;
        }
    },
    REAVER {
        public Unit createUnit(Player player) {
            return new ProtossUnits("Reaver", player.name,
                    UnitHpMax.REAVER_HPMAX.getHpMax(),
                    UnitMinerals.REAVER_MINERAL.getMinerals(),
                    UnitGases.REAVER_GAS.getGases(),
                    UnitPopulation.REAVER_POP.getPopulation(),
                    UnitDamage.REAVER_DAMAGE.getDamage(),
                    ProtossUnitMaxShield.REAVER_MAXSHIELD.getMaxShield());
        }

        @Override
        public boolean checkMineral(Player player) {
            return player.minerals >= UnitMinerals.REAVER_MINERAL.getMinerals();
        }

        @Override
        public boolean checkGas(Player player) {
            return player.gases >= UnitGases.REAVER_GAS.getGases();
        }

        @Override
        public boolean checkPopulation(Player player) {
            return player.curPopulation + UnitPopulation.REAVER_POP.getPopulation() <= player.maxPopulation;
        }
    };

    public abstract Unit createUnit(Player player);
    public abstract boolean checkMineral(Player player);
    public abstract boolean checkGas(Player player);
    public abstract boolean checkPopulation(Player player);
}
