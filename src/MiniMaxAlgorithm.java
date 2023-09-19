import java.util.List;
import java.util.HashMap;
import java.util.Map;
import static java.lang.Math.*;

/**
 * The {@code MiniMaxAlgorithm} class implements the MiniMax algorithm to determine the best
 * move for the AI in a Tic Tac Toe game. It uses a memoization technique to store previously
 * computed board states and their results to optimize the search. The algorithm also prioritizes
 * the shortest path to victory by considering the depth of the game tree during evaluation.
 */
public class MiniMaxAlgorithm {

    /**
     * A map to store previously computed board states (represented as Strings)
     * and their associated best scores. This memoization reduces
     * redundant calculations, optimizing the search process.
     */
    private final Map<String, Integer> visitedNodes = new HashMap<>();

    /**
     * Initiates the MiniMax algorithm to compute the best score for the given board state.
     *
     * @param gameBoard The current game board.
     * @param lastMove The last move made on the board.
     * @return The best possible score for the given board state.
     */
    public int minimax(GameBoard gameBoard, BoardNode lastMove){
        return performMinimax(gameBoard, lastMove, false, 0);
    }

    /**
     * Recursively computes the best score for the given board state using the MiniMax algorithm,
     * taking into account the depth of the game tree to prioritize shorter paths to victory.
     *
     * @param gameBoard The current game board.
     * @param lastMove The last move made on the board.
     * @param isMaximizing {@code true} if the current player is trying to maximize the score, {@code false} otherwise.
     * @param depth The current depth of the game tree.
     * @return The best possible score for the given board state.
     */
    private int performMinimax(GameBoard gameBoard, BoardNode lastMove, boolean isMaximizing, int depth){
        // Check if the last move resulted in a game-ending state (win/loss/draw).
        GameResult result = gameBoard.getGameResult(lastMove);
        if(result != GameResult.UNDETERMINED){
            return getBoardScore(result, depth);
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
            int currentScore = performMinimax(gameBoard, node, !isMaximizing, depth + 1);

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
     * Maps the game result to its respective score, adjusting the score based on the depth
     * of the game tree to prioritize shorter paths to victory or delay losses.
     *
     * @param result The game outcome (AI_WINS, USER_WINS, DRAW, or UNDETERMINED).
     * @param depth The current depth of the game tree.
     * @return The score associated with the game outcome.
     */
    private int getBoardScore(GameResult result, int depth){
        return switch (result) {
            case DRAW -> 0;
            case AI_WINS -> 10 - depth;  // AI should win as soon as possible
            case USER_WINS -> depth - 10;  // AI should prevent USER from winning as long as possible
            default -> throw new IllegalStateException("Unexpected value: " + result);
        };
    }
}
