public record TicTacToeResult(GameResult result, int visitedNodes) {
    private static final Logger logger = new Logger("TicTacToeResult");
    public void printResult(){
        switch (result){
            case DRAW -> logger.info("It's a draw!");
            case USER_WINS -> logger.info("You win!");
            case AI_WINS -> logger.info("You lose!");
        }
    }

    public void printVisitedNodes(){
        logger.info("number of visited nodes: "+ visitedNodes);
    }
}
