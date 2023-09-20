import java.util.List;
import java.util.Random;

/**
 * Represents the artificial intelligence (AI) player in the Tic Tac Toe game.
 * This player uses the MiniMax algorithm to determine the best moves.
 */
public class AiPlayer extends TicTacToePlayer {

    // Logger to log debug messages.
    private static final Logger logger = new Logger("AiPlayer");

    // Random object used to generate random moves.
    private static final Random random = new Random();

    // Instance of the MiniMaxAlgorithm used to determine the best move.
    private final MiniMaxAlgorithm minimax = new MiniMaxAlgorithm();

    /**
     * Constructs an AI player with a specified game board.
     *
     * @param gameBoard The game board on which the player will make moves.
     */
    protected AiPlayer(GameBoard gameBoard) {
        super(gameBoard);
    }

    /**
     * Determines the next move of the AI.
     * If it's the first move, a random move is chosen.
     * Otherwise, the best move is determined using the MiniMax algorithm.
     *
     * @return The board node where the move was made.
     */
    @Override
    public BoardNode doMove() {
        List<BoardNode> emptySpots = gameBoard.getEmptyPositions();

        if (isFirstMove(emptySpots)) {
            return doRandomMove(emptySpots);
        } else {
            return doBestMove(emptySpots);
        }
    }

    /**
     * Chooses a random move from the list of empty spots on the board.
     *
     * @param emptySpots List of available positions on the board.
     * @return The board node where the random move was made.
     */
    public BoardNode doRandomMove(List<BoardNode> emptySpots) {
        logger.debug("Doing random move");

        BoardNode randomMove = emptySpots.get(random.nextInt(emptySpots.size() - 1));
        return gameBoard.makeMove(randomMove, BoardPlayer.AI);
    }

    /**
     * Determines the best move for the AI using the MiniMax algorithm.
     * Iterates through each possible move, computes its score using MiniMax,
     * and then chooses the move with the highest score.
     *
     * @param emptySpots List of available positions on the board.
     * @return The board node where the best move was made.
     */
    public BoardNode doBestMove(List<BoardNode> emptySpots) {
        logger.debug("Doing best move");

        int bestScore = Integer.MIN_VALUE; // Best score starts at the lowest score possible
        BoardNode bestMove = null;

        for (BoardNode node : emptySpots) {
            gameBoard.makeMove(node, BoardPlayer.AI);
            int score = minimax.minimax(gameBoard, node);
            gameBoard.resetNodePlayer(node);

            logger.debug("Move " + node + " got a score of " + score);

            if (score > bestScore) {
                bestScore = score;
                bestMove = node;
                logger.debug("Best move changed to " + node + " with score of " + bestScore);
            }
        }

        if (bestMove != null) {
            return gameBoard.makeMove(bestMove, BoardPlayer.AI);
        }

        return null;
    }

    /**
     * Returns the number of unique board states that have been visited during the search.
     *
     * @return The count of visited board states.
     */
    public int getVisitedNodesCount() {
        return minimax.getVisitedNodesCount();
    }

    /**
     * Checks if the current move is the first move of the game.
     *
     * @param emptySpots List of available positions on the board.
     * @return {@code true} if it's the first move, {@code false} otherwise.
     */
    private boolean isFirstMove(List<BoardNode> emptySpots) {
        return emptySpots.size() == gameBoard.size() - 1;
    }
}
