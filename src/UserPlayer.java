import java.util.Scanner;

public class UserPlayer extends TicTacToePlayer{
    private static final Scanner scanner = new Scanner(System.in);
    private static final String GET_X_PROMPT_TEMPLATE = "choose row (0-%d)";
    private static final String GET_Y_PROMPT_TEMPLATE = "choose column (0-%d)";

    protected UserPlayer(GameBoard gameBoard) {
        super(gameBoard);
    }

    @Override
    public void doMove() {
        int bound = gameBoard.size() - 1;

        while (true){
            System.out.printf((GET_X_PROMPT_TEMPLATE) + "%n", bound);
            int x = scanner.nextInt();

            System.out.printf((GET_Y_PROMPT_TEMPLATE) + "%n", bound);
            int y = scanner.nextInt();

            if(gameBoard.doMove(x, y, BoardPlayer.USER)){
                return;
            }

            System.out.println("Invalid move, this is already occupied!");
        }

    }
}
