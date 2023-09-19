/**
 * The main class responsible for executing and managing the Tic Tac Toe game.
 * Orchestrates the game flow, switches between user and AI turns, prints the board state,
 * and determines when the game ends.
 */
public class GameExecutor {
    // Logger instance for logging game events and actions.
    private static final Logger logger = new Logger("GameExecutor");

    // Represents the game board where moves are made.
    private final GameBoard board;

    // Factory for getting the current player (either User or AI) to play.
    private final TicTacToePlayerFactory currentPlayer;

    // Holds the current player's turn.
    private BoardPlayer playerTurn;

    // Holds the game result (WIN, DRAW, or UNDETERMINED).
    private GameResult result = GameResult.UNDETERMINED;

    /**
     * Constructs a GameExecutor with a new game board and initializes player factory.
     */
    public GameExecutor(){
        board = new GameBoard();
        currentPlayer = new TicTacToePlayerFactory(new UserPlayer(board), new AiPlayer(board));
    }

    /**
     * Starts the Tic Tac Toe game, manages the game flow, and returns the result when the game ends.
     *
     * @return The final result of the game, along with the number of unique board states visited by the AI.
     */
    public TicTacToeResult play(){
        while (result == GameResult.UNDETERMINED){
            /* 1. set the turn (who plays) */
            setTurn();

            /* 2. print current turn (who plays) */
            printTurn();

            /* 3. execute turn according to player */
            BoardNode node = playByTurn();

            /* 4. print current board status */
            printBoard();

            /* 5. calculate game result */
            result = board.getGameResult(node);
        }

        /* 6. return results */
        return new TicTacToeResult(result, currentPlayer.getAIVisitedNodesCount());
    }

    /**
     * Sets the turn of the player (either User or AI).
     */
    private void setTurn(){
        if(playerTurn == null){
            playerTurn = BoardPlayer.USER;
        } else{
            playerTurn = playerTurn == BoardPlayer.AI ? BoardPlayer.USER : BoardPlayer.AI;
        }
    }

    /**
     * Prints the message indicating whose turn it is.
     */
    private void printTurn(){
        logger.info(playerTurn == BoardPlayer.USER ? "Your turn!" : "AI plays...");
    }

    private BoardNode playByTurn(){
        return currentPlayer.movePlayer(playerTurn);
    }

    private void printBoard(){
        logger.info("Board:");
        System.out.println(board);
    }
}
