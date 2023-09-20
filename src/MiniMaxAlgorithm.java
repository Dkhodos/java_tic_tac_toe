import java.util.HashSet;
import java.util.List;
import static java.lang.Math.*;

/**
 * The {@code MiniMaxAlgorithm} class implements the MiniMax algorithm to determine the best
 * move for the AI in a Tic Tac Toe game. The algorithm prioritizes
 * the shortest path to victory by considering the depth of the game tree during evaluation.
 * Also, Alpha-beta pruning is used to cut off branches in the search tree, optimizing the search process.
 */
public class MiniMaxAlgorithm {

    /**
     * A set to store previously computed board states (represented as Strings), to count the visited nodes
     */
    private final HashSet<String> visitedNodes = new HashSet<>();

    /**
     * Initiates the MiniMax algorithm to compute the best score for the given board state.
     * isMaximizing=false, its the player move we will analyze now (AI - maximizing, player minimizing)
     *
     * @param gameBoard The current game board.
     * @param lastMove The last move made on the board.
     * @return The best possible score for the given board state.
     */
    public int minimax(GameBoard gameBoard, BoardNode lastMove){
        return performMinimax(gameBoard, lastMove, false, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Recursively computes the best score for the given board state using the MiniMax algorithm with alpha-beta pruning.
     * The algorithm considers the depth of the game tree to prioritize shorter paths to victory.
     * Alpha-beta pruning is used to cut off branches in the search tree, optimizing the search process.
     *
     * @param gameBoard The current game board.
     * @param lastMove The last move made on the board.
     * @param isMaximizing {@code true} if the current player is trying to maximize the score, {@code false} otherwise.
     * @param depth The current depth of the game tree.
     * @param alpha The best score that the maximizing player is assured of.
     * @param beta The best score that the minimizing player is assured of.
     * @return The best possible score for the given board state.
     */
    private int performMinimax(GameBoard gameBoard, BoardNode lastMove, boolean isMaximizing, int depth,
                               int alpha, int beta){
        // Check if the last move resulted in a game-ending state (win/loss/draw).
        GameResult result = gameBoard.getGameResult(lastMove);
        if(result != GameResult.UNDETERMINED){
            return getBoardScore(result, depth);
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
            int currentScore = performMinimax(gameBoard, node, !isMaximizing, depth + 1, alpha, beta);

            // Store the visited node.
            visitedNodes.add(gameBoard.toHash());

            // Revert the move to explore other possibilities.
            gameBoard.resetNodePlayer(node);

            // Update the best score and alpha/beta values based on whether we are maximizing or minimizing.
            if(isMaximizing){
                bestScore = max(currentScore, bestScore);
                alpha = max(alpha, bestScore);
            } else {
                bestScore = min(currentScore, bestScore);
                beta = min(beta, bestScore);
            }

            // Alpha-beta pruning: if alpha is greater than or equal to beta, break out of the loop.
            if (alpha >= beta) {
                break;
            }
        }

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
