package test;

import game.controller.Controller;
import game.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SnakeTest {
    private final Coordinate boardDim = new Coordinate(9, 9);

    // Main method to test the snakes movement direction
    private boolean moveSnake(Direction direction) {
        Snake snake = new Snake(new Coordinate(4, 2));
        Coordinate oldPosition = snake.getHeadPosition();
        snake.setDirection(direction);
        snake.move();
        oldPosition.plus(direction.vector);
        return (snake.getHeadPosition().getX() == oldPosition.getX() &&
                snake.getHeadPosition().getY() == oldPosition.getY());
    }

    @Test
    public void testSnakeMoveUp() {
        assertTrue(moveSnake(Direction.UP));
    }

    @Test
    public void testSnakeMoveDown() {
        assertTrue(moveSnake(Direction.DOWN));
    }

    @Test
    public void testSnakeMoveLeft() {
        assertTrue(moveSnake(Direction.LEFT));
    }

    @Test
    public void testSnakeMoveRight() {
        assertTrue(moveSnake(Direction.RIGHT));
    }

    @Test
    public void testWallCollision() {
        Board board = new Board(boardDim);
        Snake snake = new Snake(new Coordinate(1, 1));
        Food food = new Food(boardDim);

        Controller controller = new Controller(snake, board, food);
        controller.setSnakeDirection(Direction.UP);
        controller.run();

        assertTrue(controller.isGameOver());
    }

    @Test
    public void testFoodCollision() {
        Food food = new Food(boardDim);
        Coordinate oldFoodPos = new Coordinate(4, 3);
        food.setPos(oldFoodPos);

        Coordinate firstPos = new Coordinate(4, 4);
        Snake snake = new Snake(firstPos);
        int snakeLength = snake.getLength();
        Board board = new Board(boardDim);

        Controller controller = new Controller(snake, board, food);
        int oldScore = controller.getScore();
        controller.setSnakeDirection(Direction.LEFT);

        // By our game logic, we need to execute twice so that our snake body gets bigger
        controller.run();

        boolean bigger = (snakeLength != snake.getLength() && oldScore != controller.getScore());
        boolean newFood = (oldFoodPos.getX() != food.getX() || oldFoodPos.getY() != food.getY());

        assertTrue((bigger && newFood));
    }

    @Test
    public void testBodyCollision() {

        Snake snake = new Snake(new Coordinate(4, 3));
        Coordinate[] coords = {new Coordinate(4, 4), new Coordinate(5, 4), new Coordinate(5, 3)};
        snake.setBody(coords);

        Board board = new Board(boardDim);
        Food food = new Food(boardDim);
        Controller controller = new Controller(snake, board, food);
        snake.setDirection(Direction.DOWN);
        controller.run();

        assertTrue(controller.isGameOver());
    }

    @Test
    public void testMinBoard() {
        Coordinate minDim = new Coordinate(3, 3); // Minimum board dimensions
        Snake snake = new Snake(new Coordinate(1, 1));
        Board board = new Board(minDim);
        Food food = new Food(minDim);

        Controller controller = new Controller(snake, board, food);
        controller.setSnakeDirection(Direction.LEFT);
        controller.run();

        assertTrue(controller.isGameOver());
    }

    @Test
    public void testMaxBoard() {
        Coordinate maxDim = new Coordinate(1000, 1000); // Big board dimension
        Snake snake = new Snake(new Coordinate(998, 998));
        Board board = new Board(maxDim);
        Food food = new Food(maxDim);

        Controller controller = new Controller(snake, board, food);
        controller.setSnakeDirection(Direction.RIGHT);
        controller.run();

        assertTrue(controller.isGameOver());
    }

    @Test
    public void testWinCondition() {
        Food food = new Food(boardDim);
        Coordinate oldFoodPos = new Coordinate(5, 4);
        food.setPos(oldFoodPos);

        Coordinate firstPos = new Coordinate(4, 4);
        Snake snake = new Snake(firstPos);

        Board board = new Board(boardDim);

        Controller controller = new Controller(snake, board, food);

        while (!controller.validPositionInBoard(food.getPos())) {
            food.generateRandomPosition();
        }

        snake.setLength(controller.getMaxScore() - 1 );
        controller.setSnakeDirection(Direction.DOWN);

        // By our game logic, we need to execute twice so that our snake body gets bigger
        controller.run();

        assertTrue(controller.isGameWon());
    }
}