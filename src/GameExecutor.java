public class GameExecutor {
    private static final Logger logger = new Logger("GameExecutor");
    private final GameBoard board;
    private final TicTacToePlayerFactory currentPlayer;

    private BoardPlayer playerTurn;

    private GameResult result = GameResult.UNDETERMINED;

    public GameExecutor(){
        board = new GameBoard();
        currentPlayer = new TicTacToePlayerFactory(board);
    }

    public TicTacToeResult play(){
        while (result == GameResult.UNDETERMINED){
            /* 1. set the turn (who plays) */
            setTurn();

            /* 2. print current turn (who plays)*/
            printTurn();

            /* 3. execute turn according to player*/
            playByTurn();

            /* 4. print current board status */
            printBoard();

            /* 5. calculate game result */
            result = board.getGameResult();
        }

        /* 6. return results*/
        return new TicTacToeResult(result, 56494);
    }

    private void setTurn(){
        if(playerTurn == null){
            playerTurn = BoardPlayer.USER;
        } else{
            playerTurn = playerTurn == BoardPlayer.AI ? BoardPlayer.USER : BoardPlayer.AI;
        }
    }

    private void printTurn(){
        logger.info(currentPlayer.getTurnString(playerTurn));
    }

    private void playByTurn(){
        currentPlayer.movePlayer(playerTurn);
    }

    private void printBoard(){
        logger.info("Board:");
        System.out.println(board);
    }
}
