package Control;

import Model.SudokuModel;

import java.util.Scanner;

import static Model.SudokuModel.*;


public class SudokuControl {

    public static void start(){
        initBoard();
        displayBoard();

        do {
            actionPlayer();
            displayBoard();
        }while(true);
    }

    private static void actionPlayer(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choissisez l'abscisse' :");
        char cell = scanner.next().charAt(0);

        System.out.println("Choissisez l'ordonnée' :");
        int num = scanner.nextInt();

        System.out.println("Choissisez le chiffre :");
        int value = scanner.nextInt();

        System.out.println("Case : "+cell+num+" → "+value);

        setCell(convertCoordonnate(cell,num),value);
    }

    private static int[] convertCoordonnate(char letter, int col) {
        int row = letter - 'A';
        int COL = row / SUBGRID_SIZE;
        int ROW = (col / SUBGRID_SIZE);
        int INDEX =((col % 3)*3)+((row % 3));
        System.out.println("Coordonate → "+ROW+COL+INDEX);
        return new int[]{ROW,COL,  INDEX};
    }

}
