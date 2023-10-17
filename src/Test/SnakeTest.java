package Test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class SnakeTest {
  public void testSnakeMove{
    // testear que la snake pueda moverse a cada direccion
    // Un for, por cada direccion, mover y comprovar que se ha movido correctamente
    // Assert(snake.position, oldPosition
    Snake snake = new Snake();
    direction = {arriba, abajo, izquierda, derecha};
    for (Direction direction : Direction.values()) {
      oldPosition = snake.getPosition();

      snake.setDirection(direction);

      snake.move();
      oldPosition = oldposition + movement
      assertThat(snake.getPosition(), oldPosition));
    }
  }

  public void testWallCollision{
    // Inicializar la snake en una posicion anterior a un wall y mover
    // Assert que un metodo outOfBounds que devuelva true
    // Testear que
    // Optional: Testear cada pared

  }

  public void testFoodCollision{
    // Inicializar la snake en una posicion y la comida en otra
    // Comprovar el augmento de longitud de la snake
    // Comprovar la generacion de otra fruta
  }

  public void testBodyCollision{
    // Inicializar las posiciones donde esta la snake, y positionar la cabeza
    // justo antes de una celda con un cuerpo de snake
    //  Metodo isCoiled
  }



}