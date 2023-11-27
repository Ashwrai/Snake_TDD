package test;

import game.controller.Controller;
import game.model.Board;
import game.model.Coordinate;
import game.model.Food;
import game.model.Snake;
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
        assertTrue(controller.inBoard(mockFood.getPos()));
    }

    @Test
    public void testFoodGenerationOnMinBoardSize() {
        Coordinate maxDim = new Coordinate(3, 3);
        Food mockFood = new Food(maxDim);
        Board mockBoard = new Board(maxDim);
        Snake mockSnake = new Snake(new Coordinate(1, 1));

        Controller controller = new Controller(mockSnake, mockBoard, mockFood);
        mockFood.generateRandomPosition();
        assertFalse(controller.inBoard(mockFood.getPos()));
    }


}
