import java.util.List;
import java.util.HashMap;
import java.util.Map;
import static java.lang.Math.*;

/**
 * The {@code MiniMaxAlgorithm} class implements the MiniMax algorithm to determine the best
 * move for the AI in a Tic Tac Toe game. It uses a memoization technique to store previously
 * computed board states and their results to optimize the search.
 */
public class MiniMaxAlgorithm {

    /**
     * A map to store previously computed board states (represented as Strings)
     * and their associated best scores. This memoization helps in reducing
     * redundant calculations, optimizing the search process.
     */
    private final Map<String, Integer> visitedNodes = new HashMap<>();

    /**
     * Determines the best score for the given board state using the MiniMax algorithm.
     *
     * @param gameBoard The current game board.
     * @param lastMove The last move made on the board.
     * @param isMaximizing {@code true} if the current player is trying to maximize the score, {@code false} otherwise.
     * @return The best possible score for the given board state.
     */
    public int minimax(GameBoard gameBoard, BoardNode lastMove, boolean isMaximizing){
        // Check if the last move resulted in a game-ending state (win/loss/draw).
        GameResult result = gameBoard.getGameResult(lastMove);
        if(result != GameResult.UNDETERMINED){
            return getBoardScore(result);
        }

        // Convert the current board state to its string representation.
        String boardState = gameBoard.toHash();

        // If this board state has been previously computed, return the stored result.
        if (visitedNodes.containsKey(boardState)) {
            return visitedNodes.get(boardState);
        }

        // Initialize the best score based on whether the current player is maximizing or minimizing.
        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        // Get all possible moves (empty spots) for the current board state.
        List<BoardNode> emptySpots = gameBoard.getEmptyPositions();

        // Iterate through each possible move and recursively compute the score.
        for(BoardNode node: emptySpots){
            // Make the move on the board.
            gameBoard.makeMove(node, isMaximizing ? BoardPlayer.AI : BoardPlayer.USER);

            // Recursively compute the score for this move.
            int currentScore = minimax(gameBoard, node, !isMaximizing);

            // Revert the move to explore other possibilities.
            gameBoard.clearNode(node);

            // Update the best score based on whether we are maximizing or minimizing.
            bestScore = isMaximizing ? max(currentScore, bestScore) : min(currentScore, bestScore);
        }

        // Store the computed best score for this board state to avoid redundant calculations in the future.
        visitedNodes.put(boardState, bestScore);

        return bestScore;
    }

    /**
     * Returns the number of unique board states that have been visited during the search.
     *
     * @return The count of visited board states.
     */
    public int getVisitedNodesCount() {
        return visitedNodes.size();
    }

    /**
     * Maps the game result to its respective score.
     *
     * @param result The game outcome (AI_WINS, USER_WINS, DRAW, or UNDETERMINED).
     * @return The score associated with the game outcome.
     */
    private int getBoardScore(GameResult result){
        return switch (result) {
            case UNDETERMINED, DRAW -> 0;
            case AI_WINS -> 1;
            case USER_WINS -> -1;
        };
    }
}
