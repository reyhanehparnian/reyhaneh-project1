import java.util.Scanner;

/**
 * The PlayGame class serves as the main program for playing through a version of the silver dollar game.
 *
 * @author rparnian
 * @version 1.0 Build 2024.02.14
 */
public class PlayGame {
    public static void main(String[] args) {
        System.out.print("Enter the number of coins: ");
        Scanner input = new Scanner(System.in);
        int numCoins = input.nextInt();

        // Prompt user for valid number of coins
        while (numCoins < 2 || numCoins > 20) {
            System.out.println("Illegal input (Try again You must have at least 2/at most 20 coins).");
            System.out.print("Enter the number of coins:");
            numCoins = input.nextInt();
        }

        CoinStrip strip1 = new CoinStrip(numCoins);
        System.out.println(strip1);

        // Main game loop
        while (!strip1.isGameOver()) {
            System.out.print(strip1.getTurn() + " Enter your move: ");
            int[] move = getValidMove(input, strip1);
            boolean moveSuccess = strip1.makeMove(move[0], move[1]);
            if (!moveSuccess) {
                System.out.println("Illegal move! Try again.");
            } else {
                System.out.println(strip1);
            }
        }
        System.out.println(strip1.getWinner() + " wins!");
    }

    /**
     * Gets a valid move input from the player.
     *
     * @param input Scanner object for user input.
     * @param strip1 The CoinStrip game instance.
     * @return An array with two items: the coin index and the number of squares to move.
     */
    private static int[] getValidMove(Scanner input, CoinStrip strip1) {
        while (true) {
            String move = input.nextLine();

            if (move.isEmpty()) {
                continue;
            }

            String[] parts = move.split(" ");
            if (parts.length == 2) {
                int coinLabel = Integer.parseInt(parts[0]);
                int numSquares = Integer.parseInt(parts[1]);

                if (strip1.isMoveLegal(coinLabel, numSquares)) {
                    return new int[]{coinLabel, numSquares};
                } else {
                    System.out.print("Illegal move! Try again.\n");
                    System.out.print(strip1.getTurn() + " Enter your move: ");
                }
            } else {
                System.out.print("Illegal format! Try again.\n");
                System.out.print(strip1.getTurn() + " Enter your move: ");
            }
        }
    }
}
