package Game.Model;

public enum Direction {

  DOWN(new Coordinate(0, 1)),
  LEFT(new Coordinate(-1, 0)),
  RIGHT(new Coordinate(1, 0)),
  UP(new Coordinate(0, -1));

  //Vector contiene la coordenada de el movimiento a realizar para poder actualizar la posicion de Snake
  public final Coordinate vector;

  Direction(Coordinate vector) {
    this.vector = vector;
  }
}
