package game.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Snake {

    private ArrayList<Coordinate> body;
    private Coordinate head;
    private Direction direction;
    private int length;

    public Snake(Coordinate initialPos) {
        this.head = initialPos;
        this.direction = Direction.NULL; // Initial direction is set to NULL, Snake does not from unless instructed
        this.length = 1;
        this.body = new ArrayList<Coordinate>();
        this.body.add(initialPos);
    }

    public void move() {
        Coordinate originalPos = new Coordinate(this.head);
        body.addFirst(originalPos);
        if (length < body.size())
            body.removeLast();
        originalPos.plus(direction.vector);
        this.head = originalPos;
    }

    public boolean inSnake(Coordinate coord) {
        for (Coordinate part : body) {
            if (coord.getX() == part.getX() && coord.getY() == part.getY()) {
                return true;
            }
        }
        return false;
    }

    public void grow() {
        length++;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Coordinate getHeadPosition() {
        return this.head;
    }

    public ArrayList<Coordinate> getBody() {
        return this.body;
    }

    public void setBody(Coordinate[] coords) {
        List<Coordinate> coordinateList = Arrays.asList(coords);
        this.body = new ArrayList<>(coordinateList);
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }


}
