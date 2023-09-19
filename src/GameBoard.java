import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the game board of Tic Tac Toe.
 * The board is a grid of BoardNodes, each representing a cell which can be empty or occupied by a player.
 */
class GameBoard {

    // Constants for formatting the board's string representation.
    private static final String NODE_SEPARATOR = " ";
    private static final String ROW_SEPARATOR = "\n";

    // Shared rule evaluator for determining the game outcome.
    private static final TicTacToeRule ticTacToeRule = new TicTacToeRule();

    // The 2D grid representing the board.
    private final BoardNode[][] board;

    /**
     * Constructs a new empty game board.
     */
    GameBoard() {
        board = new BoardNode[Settings.BOARD_SIZE][Settings.BOARD_SIZE];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new BoardNode(i, j);
            }
        }
    }

    /**
     * Returns the total number of cells on the board.
     *
     * @return The total number of cells.
     */
    public int size() {
        return board.length * board.length;
    }

    /**
     * Returns the length (number of cells in one dimension) of the board.
     *
     * @return The board's length.
     */
    public int getBoardLength() {
        return board.length;
    }

    /**
     * Returns a string representation of the game board.
     * Each cell is represented by its player's symbol or a placeholder for empty cells.
     *
     * @return The board's string representation.
     */
    @Override
    public String toString() {
        StringBuilder boardText = new StringBuilder();
        for (BoardNode[] row : board) {
            String[] rowSymbols = Arrays.stream(row)
                    .map(BoardNode::getPlayerSymbol)
                    .toArray(String[]::new);
            boardText.append(String.join(NODE_SEPARATOR, rowSymbols));
            boardText.append(ROW_SEPARATOR);
        }
        return boardText.toString();
    }

    /**
     * Returns a hash representation of the game board.
     * This is used for memoization in the MiniMax algorithm.
     *
     * @return The board's hash representation.
     */
    public String toHash() {
        return Arrays.stream(board)
                .flatMap(Arrays::stream)
                .map(BoardNode::getPlayerSymbol)
                .collect(Collectors.joining());
    }

    /**
     * Returns a list of BoardNodes representing all empty positions on the board.
     *
     * @return List of empty positions.
     */
    public List<BoardNode> getEmptyPositions() {
        List<BoardNode> positions = new ArrayList<>();
        for (BoardNode[] row : board) {
            for (BoardNode node : row) {
                if (node.isEmpty()) {
                    positions.add(node);
                }
            }
        }
        return positions;
    }

    /**
     * Resets the state of the specified node to empty.
     *
     * @param node The node to reset.
     */
    public void resetNodePlayer(BoardNode node) {
        node.clearPlayer();
    }

    /**
     * Checks if the node at the specified position is empty.
     *
     * @param i The row index.
     * @param j The column index.
     * @return true if the node is empty, false otherwise.
     */
    public boolean isNodeEmpty(int i, int j) {
        return board[i][j].isEmpty();
    }

    /**
     * Places a player's move at the specified position.
     *
     * @param i      The row index.
     * @param j      The column index.
     * @param player  The player making the move.
     * @return The node where the move was made.
     */
    public BoardNode makeMove(int i, int j, BoardPlayer player) {
        return makeMove(board[i][j], player);
    }

    /**
     * Places a player's move on the specified node.
     *
     * @param node   The node to place the move on.
     * @param player  The player making the move.
     * @return The node where the move was made.
     */
    public BoardNode makeMove(BoardNode node, BoardPlayer player) {
        node.setPlayer(player);
        return node;
    }

    /**
     * Determines the result of the game based on the current board state and the last move made.
     *
     * @param lastMove The last move made on the board.
     * @return The result of the game (e.g., AI_WINS, USER_WINS, DRAW).
     */
    public GameResult getGameResult(BoardNode lastMove) {
        return ticTacToeRule.determineWinner(board, getEmptyPositions(), lastMove);
    }
}
