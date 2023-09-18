import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

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
        // Assuming the starting player is the AI (Maximizing)
        int score = miniMax.minimax(gameBoard, null, true);
        assertEquals(0, score, "Score should be 0 for an empty board");
    }

    @Test
    public void testAIWinningMove() {
        // Setup a board state where AI has a winning move
        // This is just an example; actual method calls might differ
        gameBoard.makeMove(0, 0, BoardPlayer.AI);
        gameBoard.makeMove(0, 1, BoardPlayer.AI);
        gameBoard.makeMove(0, 2, BoardPlayer.AI);

        int score = miniMax.minimax(gameBoard, new BoardNode(0, 2), true);
        assertEquals(1, score, "Score should be 1 when AI has a winning move");
    }

    @Test
    public void testUserWinningMove() {
        // Setup a board state where User has a winning move
        gameBoard.makeMove(1, 0, BoardPlayer.USER);
        gameBoard.makeMove(1, 1, BoardPlayer.USER);
        gameBoard.makeMove(1, 2, BoardPlayer.USER);

        int score = miniMax.minimax(gameBoard, new BoardNode(1, 2), false);
        assertEquals(-1, score, "Score should be -1 when User has a winning move");
    }

    @Test
    public void testVisitedNodesCount() {
        // Test for a certain board state
        gameBoard.makeMove(2, 0, BoardPlayer.AI);
        gameBoard.makeMove(2, 1, BoardPlayer.USER);
        miniMax.minimax(gameBoard, new BoardNode(2, 1), true);

        // The exact count can vary based on the implementation, so this is just an example
        assertTrue(miniMax.getVisitedNodesCount() > 0, "Visited nodes should be greater than 0 after minimax");
    }
}
