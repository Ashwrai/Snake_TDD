package test;

import game.controller.Controller;
import game.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PairWiseTest {

    @Test
    // Test that checks if food generates in a valid pos if board is large
    public void testFoodGenerationOnMaxBoardSize() {
        Coordinate maxDim = new Coordinate(1000, 1000);
        Food food = new Food(maxDim);
        Board board = new Board(maxDim);
        Snake snake = new Snake(new Coordinate(4, 2));

        Controller controller = new Controller(snake, board, food);
        food.generateRandomPosition();
        assertTrue(controller.validPositionInBoard(food.getPos()));
    }

    @Test
    // Test that checks if food generates in a valid pos if board has minimum dimensions
    public void testFoodGenerationOnMinBoardSize() {
        Coordinate maxDim = new Coordinate(3, 3);
        Food food = new Food(maxDim);
        Board board = new Board(maxDim);
        Snake snake = new Snake(new Coordinate(1, 1));

        Controller controller = new Controller(snake, board, food);
        food.generateRandomPosition();
        assertFalse(controller.validPositionInBoard(food.getPos()));
    }

    @Test
    // Test that checks if the snake moves propertly and game works as espected in different boards
    public void testSnakeMovementOnDifferentBoardSizes() {
        Coordinate[] boardSizes = {
            // Board sizes: {width, height}
            new Coordinate( 5, 5), new Coordinate(8, 10), new Coordinate(10, 15)
        };

        Direction[] snakeMovements = {
            Direction.UP, Direction.LEFT, Direction.DOWN, Direction.RIGHT
        };

        for (Coordinate boardSize : boardSizes) {
            int width = boardSize.getX();
            int height = boardSize.getY();

            Board board = new Board(boardSize);
            Snake snake = new Snake(new Coordinate(width / 2, height / 2));
            Food food = new Food(boardSize);

            Controller controller = new Controller(snake, board, food);
            for (Direction direction : snakeMovements) {
                controller.setSnakeDirection(direction);
                controller.run();

                //We check if the snake stays within the board boundaries to validate the snake's behavior on the board
                assertTrue(isWithinBoundaries(snake, width, height));
            }
        }
    }

    // Helper method to check if the snake is within the board boundaries
    private boolean isWithinBoundaries(Snake snake, int width, int height) {
        Coordinate headPosition = snake.getHeadPosition();
        return headPosition.getX() > 0 && headPosition.getX() < width - 1 &&
            headPosition.getY() > 0 && headPosition.getY() < height - 1;
    }

}
