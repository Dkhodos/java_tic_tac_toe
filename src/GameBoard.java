import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class GameBoard {
    private static final String NODE_SEPARATOR = " ";
    private static final String ROW_SEPARATOR = "\n";

    private BoardNode[][] board;
    private final TicTacToeRule ticTacToeRule;

    GameBoard() {
        initBoard();
        ticTacToeRule = new TicTacToeRule();
    }

    public int size() {
        return board.length * board.length;
    }

    @Override
    public String toString() {
        StringBuilder boardText = new StringBuilder();
        for (BoardNode[] row : board) {
            boardText.append(String.join(NODE_SEPARATOR, (CharSequence[]) row));
            boardText.append(ROW_SEPARATOR);
        }
        return boardText.toString();
    }

    public String toHash() {
        return Arrays.stream(board)
                .flatMap(Arrays::stream)
                .map(BoardNode::getSymbol)
                .collect(Collectors.joining());
    }

    public List<BoardNode> getEmptyPositions() {
        List<BoardNode> positions = new ArrayList<>();
        for (BoardNode[] row : board) {
            for (BoardNode node : row) {
                if (node.isEmpty()) {
                    positions.add(node);
                }
            }
        }
        return positions;
    }

    public void clearNode(BoardNode node) {
        node.clear();
    }

    public boolean isNodeEmpty(int i, int j) {
        return board[i][j].isEmpty();
    }

    public BoardNode makeMove(int i, int j, BoardPlayer state) {
        return makeMove(board[i][j], state);
    }

    public BoardNode makeMove(BoardNode node, BoardPlayer state) {
        node.setState(state);
        return node;
    }

    public GameResult getGameResult(BoardNode lastMove) {
        return ticTacToeRule.determineWinner(board, getEmptyPositions(), lastMove);
    }

    private void initBoard() {
        board = new BoardNode[Settings.BOARD_SIZE][Settings.BOARD_SIZE];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new BoardNode(i, j);
            }
        }
    }
}
