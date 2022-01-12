package com.alanpatrik.navalbattle.entities;

import com.alanpatrik.navalbattle.enums.SquareStatus;

public class Board {
    Square[][] matrix;
    private final int sizeX;
    private final int sizeY;

    public Board(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        fillBoard(sizeX, sizeX);
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Square getSquare(int x, int y) {
        return matrix[x][y];
    }

    public Square[][] fillBoard(int x, int y) {
        matrix = new Square[sizeX][sizeY];
        for (int row = 0; row < x; row++) {
            for (int col = 0; col < y; col++) {
                matrix[row][col] = new Square(row, col, SquareStatus.OCEAN);
            }
        }
        return matrix;
    }

    public void biuilShip(Square square, Ship ship) {
        switch (ship.getShipType().label) {
            case 1:
                square.setSquareStatus(SquareStatus.SHIP);
                ship.add(square);
                break;

            case 2:
                square.setSquareStatus(SquareStatus.SHIP);
                ship.add(square);
                int x = square.getX();
                int y = square.getX();
                ship.add(new Square(x, y + 1, SquareStatus.SHIP));
                break;

            case 3:
                square.setSquareStatus(SquareStatus.SHIP);
                ship.add(square);
                x = square.getX();
                y = square.getX();
                ship.add(new Square(x, y + 1, SquareStatus.SHIP));
                ship.add(new Square(x, y + 2, SquareStatus.SHIP));
                break;

            case 4:
                square.setSquareStatus(SquareStatus.SHIP);
                ship.add(square);
                x = square.getX();
                y = square.getX();
                ship.add(new Square(x, y + 1, SquareStatus.SHIP));
                ship.add(new Square(x, y + 2, SquareStatus.SHIP));
                ship.add(new Square(x, y + 3, SquareStatus.SHIP));
                break;
        }
    }
}
