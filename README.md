## Tic Tac Toe Game

Tic Tac Toe is a well-known game where two players, often denoted as 'X' and 'O', take turns marking spaces in a 4x4 grid. The player who succeeds in placing three of their marks in a horizontal, vertical, or diagonal row is the winner. This implementation of the Tic Tac Toe game provides an AI opponent, using the Minimax algorithm with memoization, to make it a challenging game for the user.
- Full assignment can be found [here](/static/assigment.pdf).

## Project Structure:
- 📁 **.github** - GitHub actions for automatic test (CI).
- 📁 **.idea** - IntelliJ project files.
- 📁 **src**: This directory contains the main game logic and algorithm implementation.
- 📁 **tests**: Here, you'll find unit tests that ensure the integrity and correctness of the game components.
- 📁 **static** - a resource folder.
- 📁 **outputs** - example runs for assignment's inputs.
- 📄 **compile_and_run** - executes the main with argument, example [here](#usage).

## Features:

- **Minimax Algorithm with Alpha-Beta Pruning and Memoization**: The core decision-making logic for the AI player. The algorithm looks ahead at all possible moves, evaluates them, and chooses the best one. Alpha-beta pruning helps in optimizing the search process by cutting off unnecessary branches in the computation tree.
- **Dynamic Board Size**: The game is adaptable to various board sizes, making it extensible beyond the traditional 3x3 grid.
- **Optimized Game Result Determination**: Instead of checking the entire board after every move, the game checks around the last move, reducing unnecessary computations.
- **State Hashing**: To speed up the Minimax algorithm, the game state is hashed and stored, avoiding redundant evaluations.
- 
## Classes and Components:

- **MiniMaxAlgorithm**: The heart of the AI's decision-making process. It uses the Minimax algorithm to evaluate potential moves and decide on the best possible outcome.
- **GameBoard**: Represents the game's board. It's responsible for managing moves, checking the game's state, and determining results.
- **TicTacToeRule**: A set of rules to determine the outcome of the game based on the current board state and the last move made.
- **BoardNode**: Represents a cell on the game board. Contains information about its current state (empty, 'X', or 'O').
- **Settings**: Contains global settings for the game, such as the board size, which can be adjusted as needed.

1. **Setting Up the Board**:
   - The game initializes a board using the `GameBoard` class, which sets up a grid of a specified size with each cell represented as a `BoardNode`.

2. **User Moves**:
   - The user is prompted to make a move based on their input. The game displays the board's current state after each move, allowing the user to plan their next move strategically.

3. **AI Moves**:
   - For the AI's first move, it randomly selects a position on the board.
   - For all subsequent moves, the AI uses the `minimax` function from the `MiniMaxAlgorithm` class. This algorithm, enhanced with alpha-beta pruning, evaluates possible moves and selects the best one based on a recursive evaluation of potential outcomes. The pruning technique significantly reduces the number of board states the algorithm needs to evaluate.

4. **Determining the Winner**:
   - After every move, the `TicTacToeRule` class checks the game's state using the `determineWinner` method. Instead of evaluating the entire board, it checks around the last move made to optimize performance.

5. **Optimization**:
   - The Minimax algorithm's performance is enhanced using both memoization and alpha-beta pruning. Previous board states and their evaluations are stored in the `visitedNodes` map to prevent re-evaluation. The alpha-beta pruning ensures that the algorithm only evaluates branches that have the potential to provide a better outcome than the current known best.

## Usage:

### Main Program:

- The Main program expects specified game arguments to run the simulation.

  #### In Intelij IDE:
- Use the stock run functionality.

  #### In Eclipse IDE:

- Use the stock run functionality.

  #### In shell (mac/ linux):

- Run:
  ```shell
  chmod +x compile_and_run.sh
  ```
- Then, run:
  ```shell
  ./compile_and_run.sh
  ```
## Tests:

### **MiniMaxAlgorithmTest**:
- Contains tests to ensure that the Minimax algorithm functions correctly and makes optimal decisions for the AI player.

### **TicTacToeRuleTest**:
- Validates that the game's outcome determination logic functions as expected. Tests various board states and ensures that the winner (or draw state) is correctly identified.

## Github CI
- Check `.github/workflows/tests.yaml`.

## Notes:

- This implementation of Tic Tac Toe uses Java and is designed with extensibility in mind. The board size can be adjusted, and the AI's decision-making process is optimized for performance.
- While the traditional Tic Tac Toe game uses a 3x3 grid, this implementation allows for larger board sizes, adding depth and complexity to the game.

---

Please let me know if you need any further details or sections added.