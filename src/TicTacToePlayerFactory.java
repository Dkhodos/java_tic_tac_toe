/**
 * Factory class for managing the Tic Tac Toe players (User and AI).
 * Provides methods to execute player moves, retrieve the current turn's message,
 * and get the number of unique board states visited by the AI during its search.
 */
public class TicTacToePlayerFactory {

    // The user player instance.
    private final UserPlayer userPlayer;

    // The AI player instance.
    private final AiPlayer aiPlayer;

    /**
     * Constructs a TicTacToePlayerFactory with specified User and AI player instances.
     *
     * @param userPlayer The instance representing the user player.
     * @param aiPlayer The instance representing the AI player.
     */
    public TicTacToePlayerFactory(UserPlayer userPlayer, AiPlayer aiPlayer) {
        this.userPlayer = userPlayer;
        this.aiPlayer = aiPlayer;
    }

    /**
     * Executes the move for the given player.
     *
     * @param playerTurn The current player (either USER or AI).
     * @return The board node where the move was made.
     */
    public BoardNode movePlayer(BoardPlayer playerTurn) {
        return getPlayer(playerTurn).doMove();
    }

    /**
     * Returns the number of unique board states that have been visited by the AI
     * during its search using the MiniMax algorithm.
     *
     * @return The count of visited board states by the AI.
     */
    public int getAIVisitedNodesCount() {
        return aiPlayer.getVisitedNodesCount();
    }

    /**
     * Retrieves the TicTacToePlayer instance (either User or AI) based on the given player turn.
     *
     * @param playerTurn The current player (either USER or AI).
     * @return The TicTacToePlayer instance corresponding to the given player.
     */
    private TicTacToePlayer getPlayer(BoardPlayer playerTurn) {
        return playerTurn == BoardPlayer.USER ? userPlayer : aiPlayer;
    }
}
