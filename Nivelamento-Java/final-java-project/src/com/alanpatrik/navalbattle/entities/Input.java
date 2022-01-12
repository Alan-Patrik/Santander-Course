package com.alanpatrik.navalbattle.entities;

import com.alanpatrik.navalbattle.entities.Board;
import com.alanpatrik.navalbattle.entities.Ship;
import com.alanpatrik.navalbattle.entities.Square;
import com.alanpatrik.navalbattle.enums.ShipType;
import com.alanpatrik.navalbattle.enums.SquareStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    private Scanner scanner = new Scanner(System.in);
    private List<Board> boards = new ArrayList<>();
    int choise;
    List<Integer> coordinatesAndShipTypes = new ArrayList<>();

    public List<Board> getBoards() {
        generateBoard();
        return boards;
    }

    public void generateBoard() {
        System.out.print("Select height: ");
        int x = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Select width: ");
        int y = scanner.nextInt();
        scanner.nextLine();
        Board board1 = new Board(x, y);
        Board board2 = new Board(x, y);
        boards.add(board1);
        boards.add(board2);
    }

    public Input() {
    }

    public int getIntegerMenuOption() {
        choise = scanner.nextInt();
        scanner.nextLine();
        return choise;
    }

    private List<Integer> askCoordForShipAndType() {
        try {
            this.coordinatesAndShipTypes = new ArrayList<>();
            System.out.print("Select row: ");
            int row = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Select col: ");
            int col = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            System.out.print("Select ship: \n"
                    + "1. CARRIER \n"
                    + "2.CRUISER \n"
                    + "3. BATTLESHIP \n"
                    + "4. DESTROYER \n"
                    + "5. SUBMARINE \n");
            int shipType = scanner.nextInt();
            coordinatesAndShipTypes.add(row);
            coordinatesAndShipTypes.add(col);
            coordinatesAndShipTypes.add(shipType);
            System.out.println();

            return coordinatesAndShipTypes;
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Error: Error inserting ship in the indicated coordinates. Please respect the size of the board!");
        }
        return null;
    }

    public Ship createShip(int player) {
        int gamePlayer = player +1;
        Square shipPart;
        Ship ship;
        System.out.println("Player " + gamePlayer + " place ship");
        coordinatesAndShipTypes = askCoordForShipAndType();
        int row = coordinatesAndShipTypes.get(0);
        int col = coordinatesAndShipTypes.get(1);
        int shipType = coordinatesAndShipTypes.get(2);
        shipPart = new Square(row, col, SquareStatus.SHIP);
        ship = new Ship(new ArrayList<>(), ShipType.values()[shipType-1]);
        boards.get(player).biuilShip(shipPart, ship);

        return ship;
    }

    public int[] shoot(int player) {
        try {
            int gamePlayer = player +1;
            System.out.println("Player " + gamePlayer + " shoot");
            System.out.println("Select row: ");
            int row = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Select col: ");
            int col = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            return new int[]{row, col};
        } catch (ArrayIndexOutOfBoundsException exception) {
            exception.printStackTrace();
            System.out.println("Error: Error inserting ship in the indicated coordinates. Please respect the size of the board!");
        }
        return null;

    }
}












