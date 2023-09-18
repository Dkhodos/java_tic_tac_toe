public class TicTacToePlayerFactory {
    private final TicTacToePlayer userPlayer;
    private final TicTacToePlayer aiPlayer;

    public TicTacToePlayerFactory(UserPlayer userPlayer, AiPlayer aiPlayer) {
        this.userPlayer = userPlayer;
        this.aiPlayer = aiPlayer;
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
