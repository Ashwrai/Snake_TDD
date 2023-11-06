package Game.Model;

public enum Direction {

  DOWN(new Coordinate(1, 0)),
  RIGHT(new Coordinate(0, 1)),
  UP(new Coordinate(-1, 0)),
  LEFT(new Coordinate(0, -1)),
  STATIC(new Coordinate(0,0));


  //Vector contiene la coordenada de el movimiento a realizar para poder actualizar la posicion de Snake
  public final Coordinate vector;

  Direction(Coordinate vector) {
    this.vector = vector;
  }
}
