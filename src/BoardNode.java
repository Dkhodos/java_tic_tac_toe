/**
 * Represents a single node (or cell) on the Tic Tac Toe game board.
 * Each node is identified by its row (i) and column (j) indices.
 * It can be occupied by a player (AI or USER) or remain empty.
 */
public class BoardNode {

    /** The row index of this node on the game board. */
    private final int i;

    /** The column index of this node on the game board. */
    private final int j;

    /** The current player occupying this node. Default is EMPTY, meaning no player occupies it. */
    private BoardPlayer player = BoardPlayer.EMPTY;

    /**
     * Constructs a new BoardNode with specified row and column indices.
     *
     * @param i the row index of the node.
     * @param j the column index of the node.
     */
    public BoardNode(int i, int j) {
        this.i = i;
        this.j = j;
    }

    /**
     * Gets the row index of this node.
     *
     * @return the row index.
     */
    public int getI() {
        return i;
    }

    /**
     * Gets the column index of this node.
     *
     * @return the column index.
     */
    public int getJ() {
        return j;
    }

    /**
     * Retrieves the symbolic representation of the player occupying this node.
     *
     * @return the symbol of the player.
     */
    public String getPlayerSymbol() {
        return this.player.getValue();
    }

    /**
     * Checks if the node is unoccupied.
     *
     * @return true if the node is empty, false otherwise.
     */
    public boolean isEmpty() {
        return player == BoardPlayer.EMPTY;
    }

    /**
     * Retrieves the player currently occupying this node.
     *
     * @return the occupying player.
     */
    public BoardPlayer getPlayer() {
        return player;
    }

    /**
     * Sets the state of this node to the specified player.
     *
     * @param state the player (AI, USER, or EMPTY) to set this node's state to.
     */
    public void setPlayer(BoardPlayer state) {
        this.player = state;
    }

    /**
     * Clears the node, setting its state back to EMPTY.
     */
    public void clearPlayer() {
        player = BoardPlayer.EMPTY;
    }

    @Override
    public String toString() {
        if(isEmpty()){
            return "(" + i + "," + j + ")";
        }
        return "(" + i + "," + j + "," +getPlayerSymbol() +")";
    }
}
