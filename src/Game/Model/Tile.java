package Game.Model;

/**
 * Different types of tiles that can be on the game board.
 */

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
}
