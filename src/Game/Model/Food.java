package Game.Model;

public class Food {
    private int x;
    private int y;
    private int boardWidth;
    private int boardHeight;

    public Food(Coordinate boardDim) {
        // Generate initial random position for the food
        boardWidth = boardDim.getX();
        boardHeight = boardDim.getY();
        generateRandomPosition();
    }

    public void setPos(Coordinate position){
        this.x = position.getX();
        this.y = position.getY();
    }

    public void generateRandomPosition() {
        // Randomly set the x and y coordinates within the game board boundaries
        this.x = 1 + (int) (Math.random() * (boardWidth - 2));
        this.y = 1 + (int) (Math.random() * (boardHeight - 2));
    }

    public Coordinate getPos(){
        return new Coordinate(x, y);
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
