/**
 * Abstract class representing a player in the Tic Tac Toe game.
 * This serves as a base for different types of players (e.g., AI, human).
 * Each player is able to make a move on the game board.
 */
abstract class TicTacToePlayer {

    // The game board on which the player makes moves.
    protected GameBoard gameBoard;

    /**
     * Constructs a Tic Tac Toe player with a specified game board.
     *
     * @param gameBoard The game board on which the player will make moves.
     */
    protected TicTacToePlayer(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    /**
     * Abstract method for a player to make a move on the game board.
     * The specific implementation of how the move is determined depends on the type of player (e.g., AI's strategy).
     *
     * @return The board node where the move was made.
     */
    abstract public BoardNode doMove();
}
