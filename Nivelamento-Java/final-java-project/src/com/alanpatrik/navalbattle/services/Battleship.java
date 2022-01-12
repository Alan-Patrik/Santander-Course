package com.alanpatrik.navalbattle.services;

import com.alanpatrik.navalbattle.entities.Input;
import com.alanpatrik.navalbattle.services.Display;
import com.alanpatrik.navalbattle.services.Game;

public class Battleship {

    private Display display;
    private Game game = new Game();
    private Input input;

    public Battleship() {
        display = new Display();
    }

    public void start() {
        display.printMenu();
        mainMenu();
    }

    private void mainMenu() {
        int choice;
        boolean exit = false;
        input = new Input();

        while (!exit) {
            display.printMainMenuOptions();
            System.out.print("Enter your choice? ");
            choice = input.getIntegerMenuOption();
            System.out.println();
            switch (choice) {
                case 0:
                    display.printMessages("you choose to play the game \n");
                    game.gameLogic();
                    break;
                case 1:
                    System.out.flush();
                    display.gameRules();
                    break;
                case 2:
                    display.printMessages("you choose to exit");
                    exitGame();
                    break;
            }
        }
    }

    private void exitGame() {
        display.prinExitMessage();
        System.exit(0);
    }

}
