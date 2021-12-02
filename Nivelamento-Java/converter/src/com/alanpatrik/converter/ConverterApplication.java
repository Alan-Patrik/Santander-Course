package com.alanpatrik.converter;

import javax.naming.BinaryRefAddr;
import java.util.Locale;
import java.util.Scanner;

public class ConverterApplication {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("|===============================================|");
        System.out.println("|                                               |");
        System.out.println("|             CONVERSOR DE CELSIOS              |");
        System.out.println("|                                               |");
        System.out.println("|===============================================|");
        System.out.println("| Opções para conversão:                        |");
        System.out.println("|                                               |");
        System.out.println("|         [1] FAHRENHEIT    [2] KELVIN          |");
        System.out.println("|         [3] RÉAUMUR       [4] RANKINE         |");
        System.out.println("|         [5] Todos         [0] Cancel          |");
        System.out.println("|                                               |");
        System.out.println("|===============================================|");

        int option = scan.nextInt();

        switch (option) {
            case 1:
                System.out.println("| Digite a temperature Cº: " + "                     |");
                float c1 = scan.nextFloat();

                System.out.println("|-----------------------------------------------|");
                System.out.println("| Sua temperatura em FAHRENHEIT é...            |");
                System.out.printf("| " + fahrenheit(c1) + "F");
                System.out.print("                                       |\n");
                System.out.println("|                                               |");
                System.out.println("|-----------------------------------------------|");
                System.out.println("|                                               |");
                System.out.println("|                [Volte sempre!]                |");
                System.out.println("|_______________________________________________|");
                break;

            case 2:
                System.out.println("| Digite a temperature Cº: " + "                     |");
                float c2 = scan.nextFloat();

                System.out.println("|-----------------------------------------------|");
                System.out.println("| Sua temperatura em KELVIN é...                |");
                System.out.printf("| " + kelvin(c2) + "K");
                System.out.print("                                      |\n");
                System.out.println("|                                               |");
                System.out.println("|-----------------------------------------------|");
                System.out.println("|                                               |");
                System.out.println("|                [Volte sempre!]                |");
                System.out.println("|_______________________________________________|");
                break;

            case 3:
                System.out.println("| Digite a temperature Cº: " + "                     |");
                float c3 = scan.nextFloat();

                System.out.println("|-----------------------------------------------|");
                System.out.println("| Sua temperatura em REAUMUR é...               |");
                System.out.printf("| " + reaumur(c3) + "RE");
                System.out.print("                                      |\n");
                System.out.println("|                                               |");
                System.out.println("|-----------------------------------------------|");
                System.out.println("|                                               |");
                System.out.println("|                [Volte sempre!]                |");
                System.out.println("|_______________________________________________|");
                break;

            case 4:
                System.out.println("| Digite a temperature Cº: " + "                     |");
                float c4 = scan.nextFloat();

                System.out.println("|-----------------------------------------------|");
                System.out.println("| Sua temperatura em RANKINE é...               |");
                System.out.printf("| " + rankine(c4) + "RA");
                System.out.print("                                     |\n");
                System.out.println("|                                               |");
                System.out.println("|-----------------------------------------------|");
                System.out.println("|                                               |");
                System.out.println("|                [Volte sempre!]                |");
                System.out.println("|_______________________________________________|");
                break;

            case 5:
                System.out.println("| Digite a temperature Cº: " + "                     |");
                float c5 = scan.nextFloat();

                System.out.println("|-----------------------------------------------|");
                System.out.println("| Suas temperaturas são...                       |");
                System.out.print("| " + fahrenheit(c5) + "F");
                System.out.print("                                       |\n");
                System.out.print("| " + kelvin(c5) + "K");
                System.out.print("                                      |\n");
                System.out.print("| " + reaumur(c5) + "RE");
                System.out.print("                                      |\n");
                System.out.print("| " + rankine(c5) + "RA");
                System.out.print("                                     |\n");
                System.out.println("|                                               |");
                System.out.println("|-----------------------------------------------|");
                System.out.println("|                                               |");
                System.out.println("|                [Volte sempre!]                |");
                System.out.println("|_______________________________________________|");
                break;

            case 0:
                System.out.println("| Obrigado por utilizar nosso aplicativo!       |");
                System.out.println("|_______________________________________________|");
                System.out.println("|                                               |");
                System.out.println("|                [Volte sempre!]                |");
                System.out.println("|_______________________________________________|");
                break;

            default:
                System.out.println("| Oops.. Opção inválida                         |");
                System.out.println("| Tente novamente mais tarde!                   |");
                System.out.println("|_______________________________________________|");
                System.out.println("|                                               |");
                System.out.println("|                [Volte sempre!]                |");
                System.out.println("|_______________________________________________|");
        }

    private static String fahrenheit(float c) {
        return String.format(Locale.ENGLISH, "%.2f", (c * 1.8f) + 32);
    }

    public static String kelvin(float c) {
        return String.format(Locale.ENGLISH, "%.2f", c + 273.15f);
    }

    public static String reaumur(float c) {
        return String.format(Locale.ENGLISH, "%.2f", c * 0.8f);
    }

    public static String rankine(float c) {
        return String.format(Locale.ENGLISH, "%.2f", (c * 1.8f) + 491);
    }
}
