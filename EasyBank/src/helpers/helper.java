package helpers;

import java.util.Scanner;

public class helper {
    public static int getInputAsInt(Scanner scanner) {
        int userInput = 0;

        boolean inputValid = false;
        while (!inputValid) {
            String input = scanner.nextLine();

            try {
                userInput = Integer.parseInt(input);
                inputValid = true; // La conversion en int a réussi, l'entrée est valide
            } catch (NumberFormatException e) {
                System.out.println("Entrée non valide. Veuillez entrer un nombre entier.");
            }
        }

        return userInput;
    }

}
