package game.model;

public class Food {
    private final int boardWidth;
    private final int boardHeight;
    private int x;
    private int y;

    public Food(Coordinate boardDim) {
        boardWidth = boardDim.getX();
        boardHeight = boardDim.getY();
        generateRandomPosition();
    }

    public void generateRandomPosition() {
        // Randomly set the x and y coordinates within the game board boundaries
        this.x = 1 + (int) (Math.random() * (boardWidth - 2));
        this.y = 1 + (int) (Math.random() * (boardHeight - 2));
    }

    public Coordinate getPos() {
        return new Coordinate(x, y);
    }

    public void setPos(Coordinate position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}