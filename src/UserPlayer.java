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
            int x = getUserMove(String.format(GET_X_PROMPT_TEMPLATE, bound));
            int y = getUserMove(String.format(GET_Y_PROMPT_TEMPLATE, bound));

            if(gameBoard.isNodeEmpty(x, y)){
                return gameBoard.makeMove(x, y, BoardPlayer.USER);
            }

            System.out.println("Invalid move, this is area already occupied!");
        }
    }

    private int getUserMove(String prompt){
        while (true){
            System.out.println(prompt);
            int input = scanner.nextInt();
            if(isMoveInBound(input)){
                return input;
            }
            System.out.println("Invalid input, value "+ input + " is outside of the game board!");
        }
    }

    private boolean isMoveInBound(int input){
        return input >= 0 && input < gameBoard.length();
    }
}
