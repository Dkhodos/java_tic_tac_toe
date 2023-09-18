import java.util.Scanner;

public class UserPlayer extends TicTacToePlayer{
    private static final Scanner scanner = new Scanner(System.in);
    private static final String GET_X_PROMPT_TEMPLATE = "choose row (0-%d)";
    private static final String GET_Y_PROMPT_TEMPLATE = "choose column (0-%d)";

    protected UserPlayer(GameBoard gameBoard) {
        super(gameBoard);
    }

    @Override
    public BoardNode doMove() {
        int bound = Settings.BOARD_SIZE - 1;

        while (true){
            System.out.printf((GET_X_PROMPT_TEMPLATE) + "%n", bound);
            int x = scanner.nextInt();

            System.out.printf((GET_Y_PROMPT_TEMPLATE) + "%n", bound);
            int y = scanner.nextInt();

            if(gameBoard.isNodeEmpty(x, y)){
                return gameBoard.makeMove(x, y, BoardPlayer.USER);
            }

            System.out.println("Invalid move, this is area already occupied!");
        }
    }
}
