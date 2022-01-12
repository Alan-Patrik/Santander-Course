package com.alanpatrik.navalbattle.enums;

public enum ShipType {
    CARRIER(1),
    CRUISER(2),
    BATTLESHIP(2),
    DESTROYER(4),
    SUBMARINE(5);

    public final Integer label;

    ShipType(Integer label) {
        this.label = label;
    }
}
