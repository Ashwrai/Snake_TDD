package Game.Model;

public enum Tile {
  BLANK(0),
  SNAKE(1),
  FOOD(2),
  WALL(3);

  private final int value;

  Tile(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
