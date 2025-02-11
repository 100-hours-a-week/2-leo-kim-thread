package Starcraft.Unit.Factory;

import Starcraft.Unit.Unit;

public abstract class UnitFactory {
    public abstract Unit createUnit(String owner);
}
