import java.util.Random;

public class AiPlayer extends TicTacToePlayer{
    private static final Random random = new Random();

    protected AiPlayer(GameBoard gameBoard) {
        super(gameBoard);
    }

    @Override
    public void doMove() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (true){
            int x = random.nextInt(gameBoard.size() - 1);
            int y = random.nextInt(gameBoard.size() - 1);

            if(gameBoard.doMove(x, y, BoardPlayer.AI)){
                return;
            }
        }

    }
}
