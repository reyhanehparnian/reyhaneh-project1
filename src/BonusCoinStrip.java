import java.util.Random;


/**
 * The BonusCoinStrip class represents a game board for Welter’s Game.
 *
 * @author rparnian
 * @version 1.0 Build 2024.02.18
 */
public class BonusCoinStrip {

    /**
     *  Positions of the coins on the board.
     */
    int[] coinPositions;

    /**
     * Number of coins in play.
     */
    int numCoins;

    /**
     * Total length of the board.
     */
    int boardLength;

    /**
     * Keeps track of turns to determine the current player.
     */
    int turnCounter;

    /**
     * Default constructor initializes the board with predefined values.
     */
    public BonusCoinStrip() {
        this.coinPositions = new int[]{1, 3, 6};
        this.numCoins = 3;
        this.boardLength = 7;
        this.turnCounter = 0;
    }

    /**
     * The constructor that initializes the game with a specified number of coins,
     * placing them randomly (ensuring valid positioning).
     *
     * @param numCoins The number of coins being placed on the board.
     */
    public BonusCoinStrip(int numCoins) {
        Random rand = new Random();
        boolean isValid = false;
        this.turnCounter = 0;

        while (!isValid) {
            this.numCoins = numCoins;
            this.coinPositions = new int[numCoins];
            this.coinPositions[0] = rand.nextInt(0, 6);

            for (int i = 1; i < numCoins; i++) {
                int lowerBound = this.coinPositions[i - 1] + 1;
                int upperBound = lowerBound + 5;
                int nextPlacement = rand.nextInt(lowerBound, upperBound);
                coinPositions[i] = nextPlacement;
            }

            for (int i = 1; i < numCoins; i++) {
                if (coinPositions[i] > coinPositions[i - 1] + 1) {
                    isValid = true;
                    break;
                }
            }
        }
        this.boardLength = this.coinPositions[numCoins - 1] + 1;
    }

    /**
     * Checks if a given move is legal according to the game rules.
     *
     * @param coinLabel The index of the coin being moved.
     * @param numSquares The number of spaces to move the coin.
     * @return true if the move is legal, false otherwise.
     */
    public boolean isMoveLegal(int coinLabel, int numSquares) {
        if (coinLabel < 0 || coinLabel >= this.numCoins){
            return false;
        }
        if (numSquares <= 0) {
            return false;
        }
        int newPosition = this.coinPositions[coinLabel] - numSquares;
        if (newPosition < 0) {
            return false;
        }

        for (int i = 0; i < this.numCoins; i++) {
            if (i != coinLabel && this.coinPositions[i] == newPosition) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the current player's turn.
     *
     * @return The current player's turn (string).
     */
    public String getTurn() {
        if (this.turnCounter % 2 == 0) {
            return "Player 1:";
        } else {
            return "Player 2:";
        }
    }

    /**
     * Makes a move if it is legal.
     *
     * @param coinLabel The index of the coin being moved.
     * @param numSquares The number of spaces to move the coin.
     * @return true if the move was successful, false otherwise.
     */
    public boolean makeMove(int coinLabel, int numSquares) {
        if (!isMoveLegal(coinLabel, numSquares)) {
            return false;
        }
        this.coinPositions[coinLabel] -= numSquares;
        this.turnCounter++;
        return true;
    }

    /**
     * Checks if the game is over.
     *
     * @return true if all coins are in the leftmost consecutive positions, false otherwise.
     */
    public boolean isGameOver() {
        for (int i = 0; i < this.numCoins; i++) {
            for (int move = 1; move <= coinPositions[i]; move++) {
                if (isMoveLegal(i, move)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Gets the winner of the game.
     *
     * @return "Player 1" if player 1 wins, "Player 2" if player 2 wins, null if the game is not over.
     */
    public String getWinner() {
        if (isGameOver()) {
            if (this.turnCounter % 2 == 0) {
                return "Player 2";
            } else {
                return "Player 1";
            }
        }
        return null;
    }

    /**
     * Returns the game board.
     *
     * @return A string displaying the board state with coin positions.
     */
    public String toString() {
        // Top border
        String topBorder = "+";
        for (int i = 0; i < boardLength; i++) {
            topBorder += "---+";
        }
        topBorder += "\n";

        // row
        String coinRow = "|";
        for (int i = 0; i < boardLength; i++) {
            boolean isCoinPresent = false;
            int coinLabel = -1;

            for (int j = 0; j < numCoins; j++) {
                if (coinPositions[j] == i) {
                    isCoinPresent = true;
                    coinLabel = j;
                }
            }

            if (isCoinPresent) {
                coinRow += " " + coinLabel + " ";
            } else {
                coinRow += "   ";
            }
            coinRow += "|";
        }
        coinRow += "\n";

        // Bottom border
        String bottomBorder = "+";
        for (int i = 0; i < boardLength; i++) {
            bottomBorder += "---+";
        }
        bottomBorder += "\n";

        return topBorder + coinRow + bottomBorder;
    }
}