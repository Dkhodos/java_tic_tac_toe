import java.util.Scanner;

/**
 * Represents a human player in the Tic Tac Toe game.
 * This player takes input from the console to decide on moves.
 */
public class UserPlayer extends TicTacToePlayer {

    // Scanner object to read user input.
    private static final Scanner scanner = new Scanner(System.in);

    // Templates for user prompts to get row and column choices.
    private static final String GET_X_PROMPT_TEMPLATE = "choose row (0-%d)";
    private static final String GET_Y_PROMPT_TEMPLATE = "choose column (0-%d)";
    private static final String INVALID_INPUT_OUT_OF_RANGE_TEMPLATE = "Invalid input, value %d is outside of the game board!";
    private static final String INVALID_INPUT_TEMPLATE = "Invalid move, this is area already occupied!";

    /**
     * Constructs a human player with a specified game board.
     *
     * @param gameBoard The game board on which the player will make moves.
     */
    protected UserPlayer(GameBoard gameBoard) {
        super(gameBoard);
    }

    /**
     * Prompts the user to make a move by choosing a row and column.
     * Continues prompting until a valid unoccupied cell is chosen.
     *
     * @return The board node where the move was made.
     */
    @Override
    public BoardNode doMove() {
        int bound = Settings.BOARD_SIZE - 1;

        while (true) {
            int x = getUserMove(String.format(GET_X_PROMPT_TEMPLATE, bound));
            int y = getUserMove(String.format(GET_Y_PROMPT_TEMPLATE, bound));

            if (gameBoard.isNodeEmpty(x, y)) {
                return gameBoard.makeMove(x, y, BoardPlayer.USER);
            }

            System.out.println(INVALID_INPUT_TEMPLATE);
        }
    }

    /**
     * Prompts the user for a move based on the given prompt string.
     * Continues prompting until a valid input within the board bounds is provided.
     *
     * @param prompt The prompt string to be displayed to the user.
     * @return The user's chosen move.
     */
    private int getUserMove(String prompt) {
        while (true) {
            System.out.println(prompt);
            int input = scanner.nextInt();
            if (isMoveInBound(input)) {
                return input;
            }
            System.out.printf((INVALID_INPUT_OUT_OF_RANGE_TEMPLATE) + "%n", input);
        }
    }

    /**
     * Checks if the given move is within the valid bounds of the game board.
     *
     * @param input The move value to be checked.
     * @return {@code true} if the move is within bounds, {@code false} otherwise.
     */
    private boolean isMoveInBound(int input) {
        return input >= 0 && input < gameBoard.getBoardLength();
    }
}
