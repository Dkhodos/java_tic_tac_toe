import java.util.List;
import java.util.Random;

public class AiPlayer extends TicTacToePlayer{
    private static final Logger logger = new Logger("AiPlayer");
    private static final Random random = new Random();

    private final MiniMaxAlgorithm minimax = new MiniMaxAlgorithm();

    protected AiPlayer(GameBoard gameBoard) {
        super(gameBoard);
    }

    @Override
    public BoardNode doMove() {
        List<BoardNode> emptySpots = gameBoard.getEmptyPositions();

        if(isFirstMove(emptySpots)){
            return doRandomMove(emptySpots);
        } else {
            return doBestMove(emptySpots);
        }
    }

    public BoardNode doRandomMove(List<BoardNode> emptySpots){
        logger.debug("Doing random move");

        BoardNode randomMove = emptySpots.get(random.nextInt(emptySpots.size() - 1));
        return gameBoard.makeMove(randomMove, BoardPlayer.AI);
    }

    public BoardNode doBestMove(List<BoardNode> emptySpots) {
        logger.debug("Doing best move");

        // start with AI player move
        BoardPlayer player =  BoardPlayer.AI;

        // best score start at lower score possible
        int bestScore = Integer.MIN_VALUE;
        BoardNode bestMove = null;

        for (BoardNode node : emptySpots){

            gameBoard.makeMove(node, player);
            int score = minimax.minimax(gameBoard, node);
            gameBoard.clearNode(node);

            if(score > bestScore){
                bestScore = score;
                bestMove = node;
            }

            player = choosePlayer(player);
        }

        if(bestMove != null){
            return gameBoard.makeMove(bestMove, BoardPlayer.AI);
        }

        return null;
    }

    public int getVisitedNodesCount(){
        return minimax.getVisitedNodesCount();
    }

    private BoardPlayer choosePlayer(BoardPlayer current){
        return current == BoardPlayer.USER ? BoardPlayer.AI : BoardPlayer.USER;
    }

    private boolean isFirstMove(List<BoardNode> emptySpots){
        return emptySpots.size() == gameBoard.size() - 1;
    }
}
