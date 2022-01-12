package com.alanpatrik.navalbattle.entities;

import com.alanpatrik.navalbattle.enums.ShipType;

import java.util.List;

public class Ship {
    private final List<Square> newShip;
    private final ShipType shipType;

    public Ship(List<Square> newShip, ShipType shipType) {
        this.newShip = newShip;
        this.shipType = shipType;
    }

    public List<Square> getFields() {
        return newShip;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void add(Square square) {
        newShip.add(square);
    }

    public boolean isPlacementOk(Ship ship1, List<Ship> ships, Board board) {
        int count = 0;
        for (int i = 0; i < ship1.getFields().size(); i++) {
            if (ship1.getFields().get(i).getY() > board.getSizeY() ||
                    ship1.getFields().get(i).getX() > board.getSizeX()) {
                count++;
            }
            for (int k = 0; k < ships.size(); k++) {
                for (int z = 0; z < ships.get(k).getFields().size(); z++) {
                    if (ship1.getFields().get(i).getX() == ships.get(k).getFields().get(z).getX() &&
                            ship1.getFields().get(i).getY() == ships.get(k).getFields().get(z).getY()) {
                        count++;
                    }
                }
            }
        }
        if (count == 0) {
            return true;
        }
        return true;
    }
}
