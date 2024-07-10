package Control;

import Model.SudokuModel;

import java.util.Scanner;

import static Model.SudokuModel.*;


public class SudokuControl {

    public static void start(){
        initBoard();

        do {
            displayBoard();
            actionPlayer();

        }while(!correctBoard(board));
        System.out.println("I'D WIN");
    }

    public static void actionPlayer() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Écrivez la coordonnée (ex: A1):");
            String input = scanner.next().toUpperCase();
            char cell = input.charAt(0);


            int num = Character.getNumericValue(input.charAt(1));
            if (existInList((cell),num)){
                throw new IllegalArgumentException("Coordonnée Interdite - Cette Case ne peut pas acceuillir un nouveau chiffre");
            }

            if (cell < 'A' || cell > 'I' || num < 0 || num > 9) {
                throw new IllegalArgumentException("Coordonnées invalide.");
            } else {
                lastSelectedCell = new int[]{cell- 'A', num,1};
                displayBoard();

            }


            System.out.println("Choisissez le chiffre (1-9) :");
            int value = scanner.nextInt();


            if (value < 1 || value > 9) {
                throw new IllegalArgumentException("Valeur invalide.");
            }

            System.out.println("Case : " + cell + num + " → " + value);
            coords = convertCoordonnate(cell, num);
            setCell(coords, value);
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    public static int[] convertCoordonnate(char letter, int col) {
        int row = letter - 'A';
        int COL = row / SUBGRID_SIZE;
        int ROW = col / SUBGRID_SIZE;
        int INDEX =( (col % 3) * 3) + (row % 3);
//        System.out.println("Coordonate → "+ROW+COL+INDEX);
        return new int[]{ROW,COL,  INDEX};
    }

}
