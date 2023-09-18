import java.util.HashSet;

class GameBoard {
    private BoardNode[][] board;
    private HashSet<BoardNode> emptyNodes;
    private final TicTacToeRule ticTacToeRule;
    private BoardNode lastPlayed = null;

    GameBoard() {
        initBoard();
        ticTacToeRule = new TicTacToeRule();
    }

    public int size(){
        return board.length;
    }

    @Override
    public String toString() {
       StringBuilder boardText = new StringBuilder();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++){
                boardText.append(board[i][j].getSymbol());
                if(j < board.length - 1){
                    boardText.append(" ");
                }
            }
            boardText.append("\n");
        }

        return  boardText.toString();
    }

    public boolean doMove(int i, int j, BoardPlayer state){
        if(board[i][j].setState(state)){
            emptyNodes.remove(board[i][j]);
            lastPlayed=board[i][j];
            return true;
        }
        return false;
    }

    public GameResult getGameResult(){
        return ticTacToeRule.determineWinner(board, emptyNodes, lastPlayed);
    }

    private void initBoard(){
        board = new BoardNode[Settings.BOARD_SIZE][Settings.BOARD_SIZE];
        emptyNodes = new HashSet<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++){
                BoardNode node = new BoardNode(i, j);
                board[i][j] = node;
                emptyNodes.add(node);
            }
        }
    }
}