public class TicTacToePlayerFactory {
    private final TicTacToePlayer userPlayer;
    private final TicTacToePlayer aiPlayer;

    public TicTacToePlayerFactory(GameBoard board) {
        this.userPlayer = new UserPlayer(board);
        this.aiPlayer = new AiPlayer(board);
    }

    public void movePlayer(BoardPlayer playerTurn) {
        getPlayer(playerTurn).doMove();
    }

    public String getTurnString(BoardPlayer playerTurn){
        return playerTurn == BoardPlayer.USER ? "Your turn!" : "AI plays...";
    }

    private TicTacToePlayer getPlayer(BoardPlayer playerTurn){
        return playerTurn == BoardPlayer.USER ? userPlayer : aiPlayer;
    }
}
