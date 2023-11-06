package Game.Model;

public enum Tile {
  BLANK(0),
  SNAKE(1),
  HEAD(2),
  FOOD(3),
  WALL(4);

  private final int value;

  Tile(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
