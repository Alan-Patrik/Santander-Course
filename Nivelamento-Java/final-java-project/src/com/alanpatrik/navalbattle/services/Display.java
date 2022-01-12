package com.alanpatrik.navalbattle.services;

import com.alanpatrik.navalbattle.entities.Board;

public class Display {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";

    public Display() {}

    public void printMenu() {
        System.out.println("Battleship is starting .............");
        System.out.println("\n" + "# #  ( )\n" +
                "                                  ___#_#___|__\n" +
                "                              _  |____________|  _\n" +
                "                       _=====| | |            | | |==== _\n" +
                "                 =====| |.---------------------------. | |====\n" +
                "   <--------------------'   .  .  .  .  .  .  .  .   '--------------/\n" +
                "     \\                                                             /\n" +
                "      \\_______________________________________________WWS_________/\n" +
                "  wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n" +
                "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww\n" +
                "   wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww \n"
        );
    }

    public void printMessages(String message) {
        System.out.println(message);
    }

    public void printMainMenuOptions() {
        System.out.println("press: \n" +
        "\t 0 - Play \n" +
        "\t 1 - Print game rules \n" +
        "\t 2 - Exit game \n");
    }

    public void prinExitMessage() {
        System.out.println("Check back often!");
    }

    public void gameRules() {
        System.out.println("This implementation will be finished soon!!!");
    }

    public void printBoard(Board board) {
        System.out.print("   ");
        for (int i = 0; i < board.getSizeX(); i++) {
            if (i < 10) {
                System.out.print(i + "  ");
            } else {
                System.out.print(i + " ");
            }
        }

        System.out.println();
        for (int row = 0; row < board.getSizeX(); row++) {
            if (row < 10) {
                System.out.print(row + "  ");
            } else {
                System.out.print(row + " ");
            }

            for (int col = 0; col < board.getSizeY(); col++) {
                switch (board.getSquare(row, col).getCharacter()) {
                    case 'O':
                        System.out.print(ANSI_BLUE_BACKGROUND + " " +ANSI_RESET + " ");
                        break;
                    case 'H':
                        System.out.print(ANSI_RED_BACKGROUND + " " +ANSI_RESET + " ");
                        break;
                    case 'S':
                        System.out.print(ANSI_YELLOW_BACKGROUND + " " +ANSI_RESET + " ");
                        break;
                    case 'M':
                        System.out.print(ANSI_BLACK_BACKGROUND + " " +ANSI_RESET + " ");
                        break;
                    case 'E':
                        System.out.print(ANSI_CYAN_BACKGROUND + " " +ANSI_RESET + " ");
                        break;
                }
            }

            System.out.println();
        }
    }
}
