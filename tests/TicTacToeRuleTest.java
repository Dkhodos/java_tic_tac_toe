import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicTacToeRuleTest {

    private static final int BOARD_SIZE = 4;

    private TicTacToeRule rule;
    private BoardNode[][] board;
    private HashSet<BoardNode> emptyNodes;

    @BeforeEach
    public void setUp() {
        rule = new TicTacToeRule();
        board = new BoardNode[BOARD_SIZE][BOARD_SIZE];
        emptyNodes = new HashSet<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new BoardNode(i, j);
                emptyNodes.add(board[i][j]);
            }
        }
    }

    @Test
    public void testUserWinsInRow() {
        for (int j = 0; j < BOARD_SIZE; j++) {
            board[2][j].setState(BoardPlayer.USER);
            emptyNodes.remove(board[2][j]);
        }
        BoardNode lastPlayedNode = board[2][BOARD_SIZE - 1];
        addDummyMovesToMeetMinimum();

        assertEquals(GameResult.USER_WINS, rule.determineWinner(board, emptyNodes, lastPlayedNode));
    }

    @Test
    public void testUserWinsInColumn() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i][2].setState(BoardPlayer.USER);
            emptyNodes.remove(board[i][2]);
        }
        BoardNode lastPlayedNode = board[BOARD_SIZE - 1][2];
        addDummyMovesToMeetMinimum();

        assertEquals(GameResult.USER_WINS, rule.determineWinner(board, emptyNodes, lastPlayedNode));
    }

    @Test
    public void testUserWinsInLeftDiagonal() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i][i].setState(BoardPlayer.USER);
            emptyNodes.remove(board[i][i]);
        }
        BoardNode lastPlayedNode = board[BOARD_SIZE - 1][BOARD_SIZE - 1];
        addDummyMovesToMeetMinimum();

        assertEquals(GameResult.USER_WINS, rule.determineWinner(board, emptyNodes, lastPlayedNode));
    }

    @Test
    public void testUserWinsInRightDiagonal() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i][BOARD_SIZE - 1 - i].setState(BoardPlayer.USER);
            emptyNodes.remove(board[i][BOARD_SIZE - 1 - i]);
        }
        BoardNode lastPlayedNode = board[BOARD_SIZE - 1][0];
        addDummyMovesToMeetMinimum();

        assertEquals(GameResult.USER_WINS, rule.determineWinner(board, emptyNodes, lastPlayedNode));
    }

    @Test
    public void testAIWinsInRow() {
        for (int j = 0; j < BOARD_SIZE; j++) {
            board[1][j].setState(BoardPlayer.AI);
            emptyNodes.remove(board[1][j]);
        }
        BoardNode lastPlayedNode = board[1][BOARD_SIZE - 1];
        addDummyMovesToMeetMinimum();

        assertEquals(GameResult.AI_WINS, rule.determineWinner(board, emptyNodes, lastPlayedNode));
    }

    @Test
    public void testAIWinsInColumn() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i][1].setState(BoardPlayer.AI);
            emptyNodes.remove(board[i][1]);
        }
        BoardNode lastPlayedNode = board[BOARD_SIZE - 1][1];
        addDummyMovesToMeetMinimum();

        assertEquals(GameResult.AI_WINS, rule.determineWinner(board, emptyNodes, lastPlayedNode));
    }

    @Test
    public void testAIWinsInLeftDiagonal() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i][i].setState(BoardPlayer.AI);
            emptyNodes.remove(board[i][i]);
        }
        BoardNode lastPlayedNode = board[BOARD_SIZE - 1][BOARD_SIZE - 1];
        addDummyMovesToMeetMinimum();

        assertEquals(GameResult.AI_WINS, rule.determineWinner(board, emptyNodes, lastPlayedNode));
    }

    @Test
    public void testAIWinsInRightDiagonal() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i][BOARD_SIZE - 1 - i].setState(BoardPlayer.AI);
            emptyNodes.remove(board[i][BOARD_SIZE - 1 - i]);
        }
        BoardNode lastPlayedNode = board[BOARD_SIZE - 1][0];
        addDummyMovesToMeetMinimum();

        assertEquals(GameResult.AI_WINS, rule.determineWinner(board, emptyNodes, lastPlayedNode));
    }

    @Test
    public void testDraw() {
        // Pattern to ensure a draw based on BOARD_SIZE:
        // X X X O
        // O O O X
        // X X X O
        // O O O X

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (i % 2 == 0) {  // Even rows
                    if (j == BOARD_SIZE - 1) {
                        board[i][j].setState(BoardPlayer.AI);
                    } else {
                        board[i][j].setState(BoardPlayer.USER);
                    }
                } else {  // Odd rows
                    if (j == BOARD_SIZE - 1) {
                        board[i][j].setState(BoardPlayer.USER);
                    } else {
                        board[i][j].setState(BoardPlayer.AI);
                    }
                }
                emptyNodes.remove(board[i][j]);
            }
        }

        BoardNode lastPlayedNode = board[BOARD_SIZE - 1][BOARD_SIZE - 1];

        assertEquals(GameResult.DRAW, rule.determineWinner(board, emptyNodes, lastPlayedNode));
    }

    @Test
    public void testUndetermined() {
        BoardNode lastPlayedNode = board[0][0];
        lastPlayedNode.setState(BoardPlayer.USER);
        emptyNodes.remove(lastPlayedNode);
        addDummyMovesToMeetMinimum();

        assertEquals(GameResult.UNDETERMINED, rule.determineWinner(board, emptyNodes, lastPlayedNode));
    }

    private void addDummyMovesToMeetMinimum() {
        int totalMovesMade = BOARD_SIZE * BOARD_SIZE - emptyNodes.size();
        int minMovesRequired = BOARD_SIZE + (BOARD_SIZE - 1);
        int dummyMovesNeeded = minMovesRequired - totalMovesMade;

        int movesAdded = 0;
        for (int i = 0; i < BOARD_SIZE && movesAdded < dummyMovesNeeded; i++) {
            for (int j = 0; j < BOARD_SIZE && movesAdded < dummyMovesNeeded; j++) {
                if (board[i][j].getPlayer() == BoardPlayer.EMPTY) {
                    board[i][j].setState(BoardPlayer.AI);
                    emptyNodes.remove(board[i][j]);
                    movesAdded++;
                }
            }
        }
    }
}
