public class TicTacToePlayerFactory {
    private final UserPlayer userPlayer;
    private final AiPlayer aiPlayer;

    public TicTacToePlayerFactory(UserPlayer userPlayer, AiPlayer aiPlayer) {
        this.userPlayer = userPlayer;
        this.aiPlayer = aiPlayer;
    }

    public BoardNode movePlayer(BoardPlayer playerTurn) {
        return getPlayer(playerTurn).doMove();
    }

    public String getTurnString(BoardPlayer playerTurn){
        return playerTurn == BoardPlayer.USER ? "Your turn!" : "AI plays...";
    }

    public int getAIVisitedNodeCount(){
        return aiPlayer.getVisitedNodesCount();
    }

    private TicTacToePlayer getPlayer(BoardPlayer playerTurn){
        return playerTurn == BoardPlayer.USER ? userPlayer : aiPlayer;
    }
}
