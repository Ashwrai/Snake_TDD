package Test;

import Game.Controller.Controller;
import Game.Model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {
  private final Coordinate boardDim = new Coordinate(9,9);

  // TODO Check why sequential execution of this test gives error. (Initialize controller before for loop)
  @Test
  public void testSnakeMove(){
    // testear que la snake pueda moverse a cada direccion
    // Un for, por cada direccion, mover y comprovar que se ha movido correctamente
    // Assert(snake.position, oldPosition)
    int correct = 0;
    for (Direction direction : Direction.values()) {
      Controller controller = new Controller(boardDim);
      Coordinate oldPosition = new Coordinate(controller.getSnakeHeadPos());
      controller.setSnakeDirection(direction);
      controller.run();
      oldPosition.plus(direction.vector);
      if (controller.getSnakeHeadPos().getX() == oldPosition.getX() && controller.getSnakeHeadPos().getY() == oldPosition.getY()){
        correct++;
      }
    }
    // Its 3 because there is one case where the snake should not move to the direction indicated.
    assertEquals(correct, 3);
  }

  @Test
  public void testWallCollision(){
    // Inicializar la snake en una posicion anterior a un wall y mover
    // Assert que un metodo outOfBounds que devuelva true
    // Testear que
    int collisions = 0;
    Controller controller = new Controller(boardDim);
    Coordinate[] coords = {new Coordinate(7, 7), new Coordinate(7, 7), new Coordinate(1, 1), new Coordinate(1, 1)};
    Direction[] directions = Direction.values();;
    for (int i = 0; i < coords.length; i++){
      controller.setSnakeHead(coords[i]);
      controller.setSnakeDirection(directions[i]);
      controller.run();
      if (controller.isGameOver()){
        collisions++;
      }
    }
    assertEquals(collisions, 4);
  }

  // TODO Check snake State

  @Test
  public void testFoodCollision(){
    // Inicializar la snake en una posicion y la comida en otra
    // Comprovar el augmento de longitud de la snake
    // Comprovar la generacion de otra fruta
    int correct = 0;
    Direction[] directions = Direction.values();
    Coordinate[] coords = {new Coordinate(5, 4), new Coordinate(4, 5), new Coordinate(3, 4), new Coordinate(4, 3)};
    for (int i = 0; i < directions.length; i++) {
      Controller controller = new Controller(boardDim);
      controller.setSnakeHead(new Coordinate(4,4));
      controller.setSnakeDirection(directions[i]);
      controller.setFoodPos(coords[i]);
      controller.run();
      if (controller.getScore() == 1){
        correct++;
      }
    }
    assertEquals(correct, 4);
  }

  @Test
  public void testBodyCollision(){
    // Inicializar las posiciones donde esta la snake, y positionar la cabeza
    // justo antes de una celda con un cuerpo de snake
    Controller controller = new Controller(boardDim);
    Coordinate[] coords = {new Coordinate(4,4), new Coordinate(5,4), new Coordinate(5,3)};
    controller.setSnakeHead(new Coordinate(4,3));
    controller.setSnakeBody(coords);
    controller.setSnakeDirection(Direction.DOWN);
    controller.run();

    assertTrue(controller.isGameOver());
  }



}