package test;

import game.controller.Controller;
import game.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoverageTests {
    private final Coordinate boardDim = new Coordinate(9, 9);
    private boolean moveSnakeCoverage(Direction current, Direction wanted) {
        Snake snake = new Snake(new Coordinate(5, 5));
        Coordinate oldPosition = new Coordinate(5, 5);
        Board board = new Board(boardDim);
        Food food = new Food(boardDim);
        snake.setDirection(current);
        Controller controller = new Controller(snake, board, food);
        controller.setSnakeDirection(wanted);
        controller.run();
        oldPosition.plus(wanted.vector);
        return (snake.getHeadPosition().getX() != oldPosition.getX() ||
                snake.getHeadPosition().getY() != oldPosition.getY());
    }
    @Test
    public void testSnakeMoveUp() {
        assertTrue(moveSnakeCoverage(Direction.UP, Direction.DOWN));
    }

    @Test
    public void testSnakeMoveDown() {
        assertTrue(moveSnakeCoverage(Direction.DOWN, Direction.UP));
    }

    @Test
    public void testSnakeMoveLeft() {
        assertTrue(moveSnakeCoverage(Direction.LEFT, Direction.RIGHT));
    }

    @Test
    public void testSnakeMoveRight() {
        assertTrue(moveSnakeCoverage(Direction.RIGHT, Direction.LEFT));
    }

    @Test
    public void testGameOverConditions() {
        int correct = 0;
        Board board = new Board(boardDim);
        Snake snake = new Snake(new Coordinate(2, 1));
        Food food = new Food(boardDim);

        Controller controller = new Controller(snake, board, food);
        controller.setSnakeDirection(Direction.UP);
        controller.run();
        if (!controller.isGameOverWall()) {
            correct++;
        }
        controller.run();
        if (controller.isGameOverWall()) {
            correct++;
        }
        assertEquals(correct, 2);
    }

    @Test
    public void testWinCondition() {
        int correct = 0;

        Food food = new Food(boardDim);
        Coordinate foodPos = new Coordinate(6, 4);
        food.setPos(foodPos);

        Coordinate firstPos = new Coordinate(4, 4);
        Snake snake = new Snake(firstPos);

        Board board = new Board(boardDim);

        Controller controller = new Controller(snake, board, food);

        // First run, previous to the win condition
        snake.setLength(controller.getMaxScore() - 1);
        controller.setSnakeDirection(Direction.DOWN);
        controller.run();

        if (!controller.isGameWon()) {
            correct++;
        }

        // Second run, accomplishing win condition
        controller.run();

        if (controller.isGameWon()) {
            correct++;
        }

        assertEquals(correct, 2);
    }
}
