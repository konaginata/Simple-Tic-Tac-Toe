package tictactoe;

import java.util.Scanner;

public class Main {

    private static boolean xWins = false;
    private static boolean oWins = false;
    private static int emptyCount = 9;
    static boolean inputCondition = true;
    static char[][] matrix = {{'_', '_', '_'}, {'_', '_', '_'}, {'_', '_', '_'}};

    public static void main(String[] args) {

        showGrid(matrix);
        char currentPlayerX = 'X';
        while (inputCondition) {
            if (currentPlayerX == 'X') {
                makingTurn(matrix, 'X');
                currentPlayerX = 'O';
            } else {
                makingTurn(matrix, 'O');
                currentPlayerX = 'X';
            }
            showGrid(matrix);
            findWinner(matrix);
            if (decisionMaker() != null) {
                inputCondition = false;
                System.out.println(decisionMaker());
            }
        }
    }

    static void showGrid(char[][] matrix) {
        System.out.println("---------");
        System.out.println("| " + matrix[0][0] + " " + matrix[0][1] + " " + matrix[0][2] + " |");
        System.out.println("| " + matrix[1][0] + " " + matrix[1][1] + " " + matrix[1][2] + " |");
        System.out.println("| " + matrix[2][0] + " " + matrix[2][1] + " " + matrix[2][2] + " |");
        System.out.println("---------");
    }

    static char[][] makingTurn(char[][] matrix, char c) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coordinates: ");
        int cord1;
        int cord2;
        String move = scanner.nextLine();
        if (!isDigit(move)) {
            return makingTurn(matrix, c);
        } else {
            String[] moveArray = move.split(" ");
            cord1 = Integer.parseInt(moveArray[0]);
            cord2 = Integer.parseInt(moveArray[1]);
        }
        if (cord1 < 1 || cord1 > 3 || cord2 < 1 || cord2 > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            makingTurn(matrix, c);
        } else if (matrix[cord1 - 1][cord2 - 1] != '_') {
            System.out.println("This cell is occupied! Choose another one!");
            makingTurn(matrix, c);
        } else {
            matrix[cord1 - 1][cord2 - 1] = c;
            emptyCount--;
        }
        return matrix;
    }

    static boolean isDigit(String s) {
        String[] a = s.split(" ");
        try {
            Integer.valueOf(a[0]);
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            return false;
        }
        return true;
    }

    static void findWinnerInDiagonals(char[][] matrix) {
        if (((matrix[0][0] == matrix[1][1] && matrix[0][0] == matrix[2][2]) ||
                (matrix[0][2] == matrix[1][1] && matrix[0][2] == matrix[2][0])) && matrix[1][1] == 'X') {
            xWins = true;
        } else if (((matrix[0][0] == matrix[1][1] && matrix[0][0] == matrix[2][2]) ||
                (matrix[0][2] == matrix[1][1] && matrix[0][2] == matrix[2][0])) && matrix[0][2] == 'O') {
            oWins = true;
        }
    }

    static void findWinner(char[][] matrix) {
        for (var i = 0; i < 3; i++) {
            if ((matrix[i][0] == matrix[i][1] && matrix[i][0] == matrix[i][2] && matrix[i][0] == 'X') ||
                    (matrix[0][i] == matrix[1][i] && matrix[0][i] == matrix[2][i] && matrix[0][i] == 'X')) {
                xWins = true;
            } else if ((matrix[i][0] == matrix[i][1] && matrix[i][0] == matrix[i][2] && matrix[i][0] == 'O') ||
                    (matrix[0][i] == matrix[1][i] && matrix[0][i] == matrix[2][i] && matrix[0][i] == 'O')) {
                oWins = true;
            }
        }
        findWinnerInDiagonals(matrix);
    }

    static String decisionMaker() {
        if (xWins && !oWins) {
            return "X wins";
        } else if (oWins && !xWins) {
            return "O wins";
        } else if (emptyCount == 0) {
            return "Draw";
        } else {
            return null;
        }
    }
}


