package Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static View.SudokuView.*;

public class SudokuModel {

    public static final String RESET = "\u001B[0m";
    public static final int SUBGRID_SIZE = 3; // Taille de la sous-grille (3x3)
    public static final int SIZE = SUBGRID_SIZE * SUBGRID_SIZE; // Taille de la grille complète (9x9)
    public static final int[][][] board = new int[SUBGRID_SIZE][SUBGRID_SIZE][SIZE];

    private static final Random random = new Random();


    public static void initBoard() {
        int[][] tempBoard = new int[SIZE][SIZE];
        fillGrid(tempBoard);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i / SUBGRID_SIZE][j / SUBGRID_SIZE][(i % SUBGRID_SIZE) * SUBGRID_SIZE + (j % SUBGRID_SIZE)] = tempBoard[i][j];
            }
        }
    }

    private static boolean fillGrid(int[][] grid) {
        int count=0;
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) {
                    Collections.shuffle(numbers);
                    for (int num : numbers) {
                        if (isValidPlacement(grid, i, j, num)) {
                            grid[i][j] = num;
                            if (Math.random() < 0.15 && count < 5) {// 50% chance to leave the cell empty
                                count++;
                                continue;
                            }
                            if (fillGrid(grid)) {
                                return true;
                            } else {
                                grid[i][j] = 0;
                            }
                        }
                    }
                    count=0;
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValidPlacement(int[][] grid, int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (grid[row][i] == num || grid[i][col] == num) {
                return false;
            }
        }
        int startRow = (row / SUBGRID_SIZE) * SUBGRID_SIZE;
        int startCol = (col / SUBGRID_SIZE) * SUBGRID_SIZE;
        for (int i = 0; i < SUBGRID_SIZE; i++) {
            for (int j = 0; j < SUBGRID_SIZE; j++) {
                if (grid[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void displayBoard() {
        System.out.println("%  A  B  C  D  E  F  G  H  I");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                String color = ((i / SUBGRID_SIZE + j / SUBGRID_SIZE) % 2 == 0) ? DARK_GRAY : LIGHT_GRAY; // Alternating color pattern for sub-grids

                int value = board[i / SUBGRID_SIZE][j / SUBGRID_SIZE][(i % SUBGRID_SIZE) * SUBGRID_SIZE + (j % SUBGRID_SIZE)];
                if (value != 0) {

                    System.out.print(color + " " + BLACK +value + " " + color);
                } else {
                    System.out.print(color + "   ");
//                    System.out.print(color + " " +value+" ");
                }
                System.out.print(RESET);
            }
            System.out.println();
        }
    }

    public static void setCell(int[]tab,int value){
        int a = tab[0];
        int b = tab[1];
        int c = tab[2];
        System.out.println("COORDONNEE :");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        board[a][b][c] = value;
    }

}