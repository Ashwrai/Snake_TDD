package test;

import game.controller.Controller;
import game.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PairWiseTest extends SnakeTest {

    @Test
    public void testFoodGenerationOnMaxBoardSize() {
        Coordinate maxDim = new Coordinate(1000, 1000);
        Food mockFood = new Food(maxDim);
        Board mockBoard = new Board(maxDim);
        Snake mockSnake = new Snake(new Coordinate(4, 2));

        Controller controller = new Controller(mockSnake, mockBoard, mockFood);
        mockFood.generateRandomPosition();
        assertTrue(controller.validPositionInBoard(mockFood.getPos()));
    }

    @Test
    public void testFoodGenerationOnMinBoardSize() {
        Coordinate maxDim = new Coordinate(3, 3);
        Food mockFood = new Food(maxDim);
        Board mockBoard = new Board(maxDim);
        Snake mockSnake = new Snake(new Coordinate(1, 1));

        Controller controller = new Controller(mockSnake, mockBoard, mockFood);
        mockFood.generateRandomPosition();
        assertFalse(controller.validPositionInBoard(mockFood.getPos()));
    }

    @Test
    public void testSnakeMovementOnDifferentBoardSizes() {
        int[][] boardSizes = {
                {5, 5}, {8, 10}, {10, 15} // Board sizes: {width, height}
        };

        Direction[] snakeMovements = {
                Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT
        };

        for (int[] boardSize : boardSizes) {
            int width = boardSize[0];
            int height = boardSize[1];

            Board board = new Board(new Coordinate(width, height));
            Snake snake = new Snake(new Coordinate(width / 2, height / 2));

            for (Direction direction : snakeMovements) {
                snake.setDirection(direction);
                snake.move();

                // Add assertions to validate the snake's behavior on the board
                // For example, check if the snake stays within the board boundaries
                assertTrue(isWithinBoundaries(snake, width, height));
            }
        }
    }

    // Helper method to check if the snake is within the board boundaries
    private boolean isWithinBoundaries(Snake snake, int width, int height) {
        Coordinate headPosition = snake.getHeadPosition();
        return headPosition.getX() >= 0 && headPosition.getX() < width &&
                headPosition.getY() >= 0 && headPosition.getY() < height;
    }

}
