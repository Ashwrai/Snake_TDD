package Game.Model;

public class GameBoard {
  private Tile[][] board;
  private Snake snake;
  public GameBoard(int rows, int columns) {
    board = new Tile[rows][columns];
    initializeBoard();
  }

  // Initialize the game board with BLANK tiles
  private void initializeBoard() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (i == 0 || i == board.length - 1 || j == 0 || j == board[i].length - 1) {
          board[i][j] = Tile.WALL;
        } else {
          board[i][j] = Tile.BLANK;
        }
      }
    }
  }

  // Accessor methods to get and set tiles on the game board
  public Tile getTile(int row, int col) {
    return board[row][col];
  }

  public void setTile(int row, int col, Tile tile) {
    board[row][col] = tile;
  }

  public void isValid(Coordinate coordinate){
    snake.isIn(coordinate);
  }
}


