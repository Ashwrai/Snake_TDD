package game.model;

public enum Direction {
    // Possible directions the snake can move
    DOWN(new Coordinate(1, 0)),
    RIGHT(new Coordinate(0, 1)),
    UP(new Coordinate(-1, 0)),
    LEFT(new Coordinate(0, -1)),
    NULL(new Coordinate(0, 0));

    // Contains the coordinate after the movement to update the Snake's position
    public final Coordinate vector;

    Direction(Coordinate vector) {
        this.vector = vector;
    }
}
