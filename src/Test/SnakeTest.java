package Test;

import Game.Controller.Controller;
import Game.Model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {
  private final Coordinate boardDim = new Coordinate(9, 9);

  // Main method to test the snakes movement direction
  private boolean moveSnake(Direction direction) {
    Controller controller = new Controller(boardDim);
    Coordinate oldPosition = controller.getSnakeHeadPos();
    // Set to NULL (0, 0), we can not set the direction to LEFT because in-game it is theoretically impossible

    controller.setSnakeDirection(direction);
    controller.run();
    oldPosition.plus(direction.vector);
    return (controller.getSnakeHeadPos().getX() == oldPosition.getX() &&
        controller.getSnakeHeadPos().getY() == oldPosition.getY());
  }

  // TODO Check why sequential execution of this test gives error. (Initialize controller before for loop)
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
    int collisions = 0;
    Controller controller = new Controller(boardDim);
    Coordinate[] coords = {new Coordinate(7, 7), new Coordinate(7, 7), new Coordinate(1, 1), new Coordinate(1, 1)};
    Direction[] directions = Direction.values();
    for (int i = 0; i < coords.length; i++) {
      controller.setSnakeHead(coords[i]);
      controller.setSnakeDirection(directions[i]);
      controller.run();
      if (controller.isGameOver()) {
        collisions++;
      }
    }
    assertEquals(collisions, 4);
  }

  // TODO Check snake State

  @Test
  public void testFoodCollision() {
    int correct = 0;
    Direction[] directions = Direction.values();
    Coordinate[] coords = {new Coordinate(5, 4), new Coordinate(4, 5), new Coordinate(3, 4), new Coordinate(4, 3)};
    for (int i = 0; i < coords.length; i++) {
      Controller controller = new Controller(boardDim);
      controller.setSnakeHead(new Coordinate(4, 4));
      controller.setSnakeDirection(directions[i]);
      controller.setFoodPos(coords[i]);
      controller.run();
      if (controller.getScore() == 1) {
        correct++;
      }
    }
    assertEquals(correct, 4);
  }

  @Test
  public void testBodyCollision() {
    // Inicializar las posiciones donde esta la snake, y positionar la cabeza
    // justo antes de una celda con un cuerpo de snake
    Controller controller = new Controller(boardDim);
    Coordinate[] coords = {new Coordinate(4, 4), new Coordinate(5, 4), new Coordinate(5, 3)};
    controller.setSnakeHead(new Coordinate(4, 3));
    controller.setSnakeBody(coords);
    controller.setSnakeDirection(Direction.DOWN);
    controller.run();

    assertTrue(controller.isGameOver());
  }


}