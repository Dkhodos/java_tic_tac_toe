abstract class TicTacToePlayer {
    protected GameBoard gameBoard;

    protected TicTacToePlayer(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    abstract public BoardNode doMove();
}
