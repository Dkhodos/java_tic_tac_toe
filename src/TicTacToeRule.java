import java.util.HashSet;

public class TicTacToeRule {
    public GameResult determineWinner(BoardNode[][] board, HashSet<BoardNode> emptyNodes, BoardNode lastPlayedNode) {
        /* No move was made, nothing to check */
        if(lastPlayedNode == null){
            return GameResult.UNDETERMINED;
        }

        /* Not enough moved were made for a win, nothing to check  */
        int totalMovesMade = getTotalMoves(board, emptyNodes);
        int minMovesRequired = getMinMovesRequired(board);
        if (totalMovesMade < minMovesRequired) {
            return GameResult.UNDETERMINED;
        }

        /* We know who moved, test his impact on the game result */
        int x = lastPlayedNode.getI();
        int y = lastPlayedNode.getJ();
        BoardPlayer currentPlayer = lastPlayedNode.getPlayer();
        if (isWinningMove(x, y, currentPlayer, board)) {
            return currentPlayer == BoardPlayer.USER ? GameResult.USER_WINS : GameResult.AI_WINS;
        }

        /* Game is still  UNDETERMINED, but if we have no empty cells it's a draw*/
        if(emptyNodes.isEmpty()) {
            return GameResult.DRAW;
        }

        /* Game is still  UNDETERMINED, but we have more moves to do */
        return GameResult.UNDETERMINED;
    }

    private boolean isWinningMove(int x, int y, BoardPlayer currentPlayer, BoardNode[][] board) {
        return isWinningRow(x, currentPlayer, board) ||
                isWinningColumn(y, currentPlayer, board) ||
                isWinningLeftDiagonal(x, y, currentPlayer, board) ||
                isWinningRightDiagonal(x, y, currentPlayer, board);
    }

    private boolean isWinningRow(int row, BoardPlayer player, BoardNode[][] board) {
        return allNodesInRowEqual(row, player, board);
    }

    private boolean isWinningColumn(int col, BoardPlayer player, BoardNode[][] board) {
        return allNodesInColEqual(col, player, board);
    }

    private boolean isWinningLeftDiagonal(int x, int y, BoardPlayer player, BoardNode[][] board) {
        return x == y && allNodesInLeftDiagonalEqual(player, board);
    }

    private boolean isWinningRightDiagonal(int x, int y, BoardPlayer player, BoardNode[][] board) {
        return x + y == board.length - 1 && allNodesInRightDiagonalEqual(player, board);
    }

    private int getTotalMoves(BoardNode[][] board, HashSet<BoardNode> emptyNodes){
        return board.length * board.length - emptyNodes.size();
    }

    private int getMinMovesRequired(BoardNode[][] board){
        return board.length + (board.length - 1);
    }

    private boolean allNodesInRowEqual(int row, BoardPlayer player, BoardNode[][] board) {
        for (int j = 0; j < board.length; j++) {
            if (board[row][j].getPlayer() != player) {
                return false;
            }
        }
        return true;
    }

    private boolean allNodesInColEqual(int col, BoardPlayer player, BoardNode[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col].getPlayer() != player) {
                return false;
            }
        }
        return true;
    }

    private boolean allNodesInLeftDiagonalEqual(BoardPlayer player, BoardNode[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][i].getPlayer() != player) {
                return false;
            }
        }
        return true;
    }

    private boolean allNodesInRightDiagonalEqual(BoardPlayer player, BoardNode[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][board.length - 1 - i].getPlayer() != player) {
                return false;
            }
        }
        return true;
    }
}