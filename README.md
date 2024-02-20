# Snake Game

This repository contains the source code and documentation for the Snake Game project developed as part of the Test and Software Quality course.

## Project Overview

The main objective of the game is to guide a snake through a game field, collecting food to score points while avoiding collisions with the walls of the game and its own body. When the snake collides, the game ends.

If the snake reaches the maximum score (number of cells the snake can occupy on the board), meaning it fills the entire board, the player wins.

The project is implemented using a Model-View-Controller architecture, allowing the development of the Model and Controller parts under the **TDD** paradigm without the need for the View part to be implemented.

## Repository

You can find the project repository on GitHub: [Snake Repository](https://github.com/Ashwrai/Snake)

The project is implemented in Java, using the IntelliJ IDE.

To run the code after cloning and opening it in IntelliJ, select the src folder as the source root. [src] → [Mark directory as] → [Sources root]

From this point, you can either execute the main method to play or execute the snakeTest from the test folder.

## Testing

### Test-Driven Development (TDD)

The Model and Controller parts were developed using the Test-Driven Development (TDD) paradigm, focusing on writing tests before writing production code. 

### Test Cases

#### Movement Test

One of the initial tests was to verify that the snake could move in all allowed directions.

#### Wall Collision Test

Another test verified collisions of the snake with the walls of the game field.

#### Food Collision Test

This test checked if the snake could correctly consume food.

#### Self Collision Test

This test ensured that the game would end if the snake collided with its own body.

### Equivalent Partition Testing

Test cases were designed to cover different partitions of input space.

#### Minimum Board Dimensions Test

This test verified the functionality with the smallest possible board size.

#### Maximum Board Dimensions Test

Similar to the minimum size test, this one checked the functionality with the largest possible board size.

### Pairwise Testing

Tests were designed to cover critical value combinations.

#### Snake Movement Test on Different Boards

This test combined various board dimensions with snake movement to ensure correct behavior.

#### Food Generation Test on Border Size Boards

This test ensured food generation in boards with minimum or maximum dimensions.

### Coverage Testing

Various coverage testing techniques were employed to ensure comprehensive testing.

#### Statement Coverage

The code coverage report showed the percentage of lines executed during testing.

#### Decision Coverage

The decision coverage was used to ensure that all options of a decision were evaluated during testing.

#### Condition Coverage

Condition coverage tests ensured that all conditions in the code were evaluated with true and false values.

#### Path Coverage

Path coverage tests ensured that all possible execution paths in the code were traversed.

### Loop Testing

Specific test cases were designed to test the logic within loops.

### Conclusion

The implementation of the Snake Game using the Model-View-Controller architecture allowed for the development of tests for the Model and Controller before implementing their functionalities. This approach ensured a modular and maintainable codebase.

The white-box tests covered various key aspects of the game, ensuring that almost the entire program, both lines and branches, were evaluated. However, the View part was not extensively tested.


