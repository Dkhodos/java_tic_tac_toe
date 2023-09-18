import java.util.List;

/**
 * The {@code TicTacToeRule} class determines the outcome of a Tic Tac Toe game.
 * The rule emphasizes efficiency by focusing the win analysis around the last move made.
 * This is because the only potential change in game state that could result in a win
 * would be centered around the latest move.
 */
public class TicTacToeRule {

    /**
     * Determines the game result based on the current board state and the last move made.
     *
     * @param board The current game board.
     * @param emptyNodes The list of empty nodes on the board.
     * @param lastPlayedNode The last move made.
     * @return The game result, which can be USER_WINS, AI_WINS, DRAW, or UNDETERMINED.
     */

    public GameResult determineWinner(BoardNode[][] board, List<BoardNode> emptyNodes, BoardNode lastPlayedNode) {
        /* No move was made, nothing to check */
        if(lastPlayedNode == null){
            return GameResult.UNDETERMINED;
        }

        /* Not enough moves were made for a win, nothing to check  */
        int totalMovesMade = getTotalMoves(board, emptyNodes);
        if (totalMovesMade < board.length * 2 - 1) {
            return GameResult.UNDETERMINED;
        }

        /* We know who moved, test his impact on the game result */
        int x = lastPlayedNode.getI();
        int y = lastPlayedNode.getJ();
        BoardPlayer currentPlayer = lastPlayedNode.getPlayer();
        if (isWinningMove(x, y, currentPlayer, board)) {
            return currentPlayer == BoardPlayer.USER ? GameResult.USER_WINS : GameResult.AI_WINS;
        }

        /* Game is still  UNDETERMINED, but if we have no empty cells it's a draw*/
        if(emptyNodes.isEmpty()) {
            return GameResult.DRAW;
        }

        /* Game is still  UNDETERMINED, but we have more moves to do */
        return GameResult.UNDETERMINED;
    }

    /**
     * Checks if the latest move resulted in a win for the player.
     *
     * @param x The x-coordinate of the last move.
     * @param y The y-coordinate of the last move.
     * @param currentPlayer The player who made the last move.
     * @param board The current game board.
     * @return {@code true} if the move resulted in a win, {@code false} otherwise.
     */
    private boolean isWinningMove(int x, int y, BoardPlayer currentPlayer, BoardNode[][] board) {
        return allNodesInRowEqual(x, currentPlayer, board) ||
                allNodesInColEqual(y, currentPlayer, board) ||
                (x == y && allNodesInLeftDiagonalEqual(currentPlayer, board)) ||
                (x + y == board.length - 1 && allNodesInRightDiagonalEqual(currentPlayer, board));
    }

    /**
     * Calculates the total number of moves made on the board.
     *
     * @param board The current game board.
     * @param emptyNodes The list of empty nodes on the board.
     * @return The total number of moves made.
     */
    private int getTotalMoves(BoardNode[][] board, List<BoardNode> emptyNodes){
        return board.length * board.length - emptyNodes.size();
    }

    /**
     * Checks if all nodes in the specified row are occupied by the given player.
     *
     * @param row The row index to check.
     * @param player The player to compare with.
     * @param board The current game board.
     * @return {@code true} if all nodes in the row are occupied by the given player, {@code false} otherwise.
     */
    private boolean allNodesInRowEqual(int row, BoardPlayer player, BoardNode[][] board) {
        for (int j = 0; j < board.length; j++) {
            if (board[row][j].getPlayer() != player) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if all nodes in the specified column are occupied by the given player.
     *
     * @param col The column index to check.
     * @param player The player to compare with.
     * @param board The current game board.
     * @return {@code true} if all nodes in the column are occupied by the given player, {@code false} otherwise.
     */
    private boolean allNodesInColEqual(int col, BoardPlayer player, BoardNode[][] board) {
        for (BoardNode[] boardNodes : board) {
            if (boardNodes[col].getPlayer() != player) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if all nodes in the left diagonal (from top-left to bottom-right) are occupied by the given player.
     *
     * @param player The player to compare with.
     * @param board The current game board.
     * @return {@code true} if all nodes in the left diagonal are occupied by the given player, {@code false} otherwise.
     */
    private boolean allNodesInLeftDiagonalEqual(BoardPlayer player, BoardNode[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][i].getPlayer() != player) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if all nodes in the right diagonal (from top-right to bottom-left) are occupied by the given player.
     *
     * @param player The player to compare with.
     * @param board The current game board.
     * @return {@code true} if all nodes in the right diagonal are occupied by the given player, {@code false} otherwise.
     */
    private boolean allNodesInRightDiagonalEqual(BoardPlayer player, BoardNode[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][board.length - 1 - i].getPlayer() != player) {
                return false;
            }
        }
        return true;
    }
}
