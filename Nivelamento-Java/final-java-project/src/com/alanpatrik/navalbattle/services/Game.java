package com.alanpatrik.navalbattle.services;

import com.alanpatrik.navalbattle.entities.Board;
import com.alanpatrik.navalbattle.entities.Input;
import com.alanpatrik.navalbattle.entities.Player;
import com.alanpatrik.navalbattle.entities.Ship;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Ship> shipsPlayer1 = new ArrayList<>();
    private List<Ship> shipsPlayer2 = new ArrayList<>();
    List<Board> boards;

    public void gameLogic() {
        Input board1 = new Input();
        boards = board1.getBoards();
        Board boardPlayer1 = boards.get(0);
        Board boardPlayer2 = boards.get(1);
        for (int i = 0; i < 2; i++) {
            Ship one = board1.createShip(0);
            while (one.isPlacementOk(one, shipsPlayer1, boardPlayer1) == false) {
                one = board1.createShip(0);
            }
            shipsPlayer1.add(one);
        }
        for (int i = 0; i < 2; i++) {
            Ship one = board1.createShip(1);
            while (one.isPlacementOk(one, shipsPlayer2, boardPlayer2) == false) {
                one = board1.createShip(1);
            }
            shipsPlayer2.add(one);
        }

        Player player1 = new Player(shipsPlayer1, boardPlayer2);
        Player player2 = new Player(shipsPlayer2, boardPlayer1);
        boolean gameOne = true;
        Display display = new Display();
        System.out.println("<<<<<<<<<<<Player 1 Board>>>>>>>>>>>");
        display.printBoard(boardPlayer1);
        System.out.println("------------------------------------");
        System.out.println("<<<<<<<<<<<Player 2 Board>>>>>>>>>>>");
        display.printBoard(boardPlayer2);
        int numberOfShipsPlayer1 = player1.numberOfSquaresOfShips(shipsPlayer1);
        int numberOfShipsPlayer2 = player2.numberOfSquaresOfShips(shipsPlayer2);

        while (gameOne) {
            int[] shootCoordinates;
            shootCoordinates = board1.shoot(0);
            if (player2.handleShot(shootCoordinates[0], shootCoordinates[1])) {
                display.printBoard(player2.getBoard());
                numberOfShipsPlayer2--;
                if (numberOfShipsPlayer2 != 0) {
                    System.out.println("\nThere are now " + numberOfShipsPlayer2 + " of the Player1 opponent left\n");
                }else {
                    System.out.println("\nYou've hit all of Player1's ships!!\n");
                }

            } else {
                display.printBoard(player2.getBoard());
            }

            if (numberOfShipsPlayer2 == 0) {
                display.printBoard(player2.getBoard());
                System.out.println("\nplayer 1 wins!");
                break;
            }

            shootCoordinates = board1.shoot(1);
            if (player1.handleShot(shootCoordinates[0], shootCoordinates[1])) {
                display.printBoard(player1.getBoard());
                numberOfShipsPlayer1--;
                if (numberOfShipsPlayer1 != 0) {
                    System.out.println("\nThere are now " + numberOfShipsPlayer1 + " of the Player2 opponent left\n");
                }else {
                    System.out.println("\nYou've hit all of Player2's ships!!\n");
                }
            } else {
                display.printBoard(player1.getBoard());
            }

            if (numberOfShipsPlayer1 == 0) {
                display.printBoard(player1.getBoard());
                System.out.println("\nplayer 2 wins!");
                break;
            }
        }
    }
}
