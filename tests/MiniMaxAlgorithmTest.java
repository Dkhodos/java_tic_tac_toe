import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import java.util.List;

public class MiniMaxAlgorithmTest {

    private MiniMaxAlgorithm miniMax;
    private GameBoard gameBoard;

    private final static int originalSize = Settings.BOARD_SIZE;

    @BeforeEach
    public void setUp() {
        miniMax = new MiniMaxAlgorithm();
        gameBoard = new GameBoard();  // Assuming default constructor initializes an empty board
    }

    @BeforeAll
    public static void beforeAll(){
        Settings.BOARD_SIZE = 3;
    }

    @AfterAll
    public static void afterAll(){
        Settings.BOARD_SIZE = originalSize;
    }

    @Test
    public void testEmptyBoard() {
        int score = miniMax.minimax(gameBoard, null);
        assertEquals(-1, score, "Score should be -1 for an empty board");
    }

    @Test
    public void testAIWinningMove() {
        // Setup a board state where AI is one move away from winning
        gameBoard.makeMove(0, 0, BoardPlayer.AI);
        gameBoard.makeMove(1, 0, BoardPlayer.AI);

        // Some random user moves
        gameBoard.makeMove(0, 1, BoardPlayer.USER);
        gameBoard.makeMove(1, 1, BoardPlayer.USER);

        // AI's winning move
        BoardNode lastNode = gameBoard.makeMove(2, 0, BoardPlayer.AI);

        int score = miniMax.minimax(gameBoard, lastNode);
        assertTrue(score > 0, "Score should be positive when AI has a winning move");
    }

    @Test
    public void testUserWinningMove() {
        // Setup a board state where User is one move away from winning
        gameBoard.makeMove(1, 0, BoardPlayer.USER);
        gameBoard.makeMove(1, 1, BoardPlayer.USER);

        // Some random AI moves
        gameBoard.makeMove(0, 0, BoardPlayer.AI);
        gameBoard.makeMove(0, 1, BoardPlayer.AI);

        // User's winning move
        BoardNode lastNode = gameBoard.makeMove(1, 2, BoardPlayer.USER);

        int score = miniMax.minimax(gameBoard, lastNode);
        assertTrue(score < 0, "Score should be negative when User has a winning move");
    }

    @Test
    public void testVisitedNodesCount() {
        // Test for a certain board state
        gameBoard.makeMove(2, 0, BoardPlayer.AI);
        BoardNode lastNode = gameBoard.makeMove(2, 1, BoardPlayer.USER);
        miniMax.minimax(gameBoard, lastNode);

        // The exact count can vary based on the implementation, so this is just an example
        assertTrue(miniMax.getVisitedNodesCount() > 0, "Visited nodes should be greater than 0 after minimax");
    }

    @Test
    public void testAIBlocksUserFromWinning() {
        // Setup a board state where User is one move away from winning
        gameBoard.makeMove(0, 0, BoardPlayer.USER);
        gameBoard.makeMove(1, 1, BoardPlayer.USER);
        gameBoard.makeMove(2, 1, BoardPlayer.AI);  // AI's random move
        gameBoard.makeMove(2, 2, BoardPlayer.AI);  // AI's random move

        BoardNode aiMove = getBestAIMove(gameBoard);  // This function will use your provided logic to get the best AI move

        // Check if AI's move is at (2, 0) to block User from winning
        assertEquals(2, aiMove.getI());
        assertEquals(0, aiMove.getJ());
    }

    private BoardNode getBestAIMove(GameBoard gameBoard) {
        List<BoardNode> emptySpots = gameBoard.getEmptyPositions();

        // best score start at lower score possible
        int bestScore = Integer.MIN_VALUE;
        BoardNode bestMove = null;

        for (BoardNode node : emptySpots) {
            gameBoard.makeMove(node, BoardPlayer.AI);
            int score = miniMax.minimax(gameBoard, node);
            gameBoard.resetNodePlayer(node);

            if(score > bestScore) {
                bestScore = score;
                bestMove = node;
            }
        }

        return bestMove;
    }
}
