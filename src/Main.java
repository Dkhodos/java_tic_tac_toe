class Main{
    public static void main(String [] args){
        Logger logger = new Logger("Main");

        logger.title("Starting Tic Tac Toe Game");
        GameExecutor gameExecutor = new GameExecutor();
        TicTacToeResult result = gameExecutor.play();

        logger.title("Tic Tac Toe Game Finished, results:");
        result.printResult();
        result.printVisitedNodes();
    }
}