package game.model;

public class Coordinate {

    private int x;
    private int y;

    public Coordinate(int px, int py) {
        this.x = px;
        this.y = py;
    }

    public Coordinate(Coordinate coord) {
        this.x = coord.getX();
        this.y = coord.getY();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    //Method to change position
    public void plus(Coordinate coordinate) {
        this.x += coordinate.getX();
        this.y += coordinate.getY();
    }
}

