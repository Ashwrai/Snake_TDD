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
        snake.setDirection(Direction.UP);

        Controller controller = new Controller(snake, board, food);
        controller.run();

        assertTrue(controller.isGameOver());
    }

    @Test
    public void testFoodCollision() {
        Food food = new Food(boardDim);
        Coordinate oldFoodPos = new Coordinate(5, 4);
        food.setPos(oldFoodPos);

        Coordinate firstPos = new Coordinate(4, 4);
        Snake snake = new Snake(firstPos);
        int snakeLength = snake.getLength();
        Board board = new Board(boardDim);

        Controller controller = new Controller(snake, board, food);
        while (!controller.inBoard(food.getPos())) {
            food.generateRandomPosition();
        }

        controller.setSnakeDirection(Direction.DOWN);

        // By our game logic, we need to execute twice so that our snake body gets bigger
        controller.run();

        boolean bigger = (snakeLength != snake.getLength());
        boolean newFood = (oldFoodPos.getX() != food.getX() || oldFoodPos.getY() != food.getY());

        boolean correct = (bigger && newFood);

        assertTrue(correct);
    }

    // TODO mirar si la food sigue generandose dentro del body (Nuevo test aparte de generacion de food)
    @Test
    public void testBodyCollision() {
        // Inicializar las posiciones donde esta la snake, y positionar la cabeza
        // justo antes de una celda con un cuerpo de snake
        Snake snake = new Snake(new Coordinate(4, 3));
        Coordinate[] coords = {new Coordinate(4, 4), new Coordinate(5, 4), new Coordinate(5, 3)};
        snake.setBody(coords);

        Board board = new Board(boardDim);
        Food food = new Food(boardDim);
        Controller controller = new Controller(snake, board, food);
        while (!controller.inBoard(food.getPos())) {
            food.generateRandomPosition();
        }
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

        int snakeLength = snake.getLength();
        Board board = new Board(boardDim);

        Controller controller = new Controller(snake, board, food);
        while (!controller.inBoard(food.getPos())) {
            food.generateRandomPosition();
        }
        snake.setLength(controller.getMaxScore() - 1 );
        controller.setSnakeDirection(Direction.DOWN);

        // By our game logic, we need to execute twice so that our snake body gets bigger
        controller.run();

        assertTrue(controller.isGameWon());
    }

//    @Test
//    public void testBoardMaxDim() {
//        // Tests behaviour of the game when the board is maximum
//        Coordinate maxDim = new Coordinate(Integer.MAX_VALUE, Integer.MAX_VALUE);
//        Controller controller = new Controller(maxDim);
//
//        //We try up direction
//        controller.setSnakeDirection(Direction.UP);
//        controller.run();
//
//        assertTrue(!controller.isGameOver());
//    }
//
//    @Test
//    public void testBoardMinDim() {
//        //
//        Coordinate minDim = new Coordinate(1, 1);
//        Controller controller = new Controller(minDim);
//
//        //
//        controller.setSnakeDirection(Direction.RIGHT);
//        controller.run();
//        assertTrue(!controller.isGameOver());
//
//    }
}