package Test;

import Game.Model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class SnakeTest {
  @Test
  public void testSnakeMove(){
    // testear que la snake pueda moverse a cada direccion
    // Un for, por cada direccion, mover y comprovar que se ha movido correctamente
    // Assert(snake.position, oldPosition4

    Snake snake = new Snake(new Coordinate(0, 0));
    for (Direction direction : Direction.values()) {
      Coordinate oldPosition = snake.getHeadPosition();

      snake.setDirection(direction);

      snake.move();
      oldPosition.plus(direction.vector);
      assertEquals(snake.getHeadPosition(), oldPosition);
    }
  }

  public void testFoodGeneration(){
    //Inicializar la Snake y una comida en la misma posicion
    //Creamos un GameBoard para testear el metodo que comprueba si una posicion es valida
    //Assert que un metodo de GameBoard que mira si la posicion es valida devuelva false

    Coordinate coord = new Coordinate(2,2);
    Snake snake = new Snake(coord);
    Food food = new Food(coord);

    GameBoard board;

    assertEquals(board.isValid(), false);

  }

//  public void testWallCollision{
//    // Inicializar la snake en una posicion anterior a un wall y mover
//    // Assert que un metodo outOfBounds que devuelva true
//    // Testear que
//    // Optional: Testear cada pared
//
//  }
//
//  public void testFoodCollision{
//    // Inicializar la snake en una posicion y la comida en otra
//    // Comprovar el augmento de longitud de la snake
//    // Comprovar la generacion de otra fruta
//  }
//
//  public void testBodyCollision{
//    // Inicializar las posiciones donde esta la snake, y positionar la cabeza
//    // justo antes de una celda con un cuerpo de snake
//    //  Metodo isCoiled
//  }



}