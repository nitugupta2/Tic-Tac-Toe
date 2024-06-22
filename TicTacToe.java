import java.util.Scanner;
import java.util.InputMismatchException;

public class TicTacToe 
{
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) 
    {
        initializeBoard();
        printBoard();

        while (true) 
        {
            playerMove();
            printBoard();
            if (isWinner()) 
            {
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            } 
            else if (isBoardFull()) 
            {
                System.out.println("The game is a draw!");
                break;
            }
            switchPlayer();
        }
    }

    private static void initializeBoard()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++) 
            {
                board[i][j] = '-';
            }
        }
    }

    private static void printBoard()
    {
        System.out.println("Board:");
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 3; j++) 
            {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("-----");
        }
    }

    private static void playerMove()
    {
        Scanner scanner = new Scanner(System.in);
        int row = -1, col = -1;
        boolean validInput = false;

        while (!validInput)
        {
            System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");
            try
            {
                row = scanner.nextInt() - 1;               // Adjusting for 0-based indexing
                col = scanner.nextInt() - 1;               // Adjusting for 0-based indexing
                if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-')
                {
                    board[row][col] = currentPlayer;
                    validInput = true;
                }
                else 
                {
                    System.out.println("This move is not valid");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid input. Please enter numbers for row and column.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }

    private static boolean isWinner()
    {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    private static boolean checkRows() 
    {
        for (int i = 0; i < 3; i++) 
        {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkColumns() 
    {
        for (int j = 0; j < 3; j++) 
        {
            if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkDiagonals() 
    {
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
            (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) 
        {
            return true;
        }
        return false;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void switchPlayer() 
    {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}
