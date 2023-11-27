package game.model;

public class Board {
    private final Tile[][] board;
    private final Coordinate boardDim;

    public Board(Coordinate boardDim) {
        this.boardDim = boardDim;
        this.board = new Tile[boardDim.getX()][boardDim.getY()];
    }

    public Coordinate getBoardDim() {
        return this.boardDim;
    }

    public Tile getTile(int x, int y) {
        return board[x][y];
    }

    public void setTile(int x, int y, Tile tile) {
        board[x][y] = tile;
    }

    public Tile[][] getBoard() {
        return this.board;
    }
}
