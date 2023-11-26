package Game.Model;

import Game.Controller.Controller;

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
    this.direction = Direction.NULL; // Initial direction is set to right
    this.length = 1;
    this.body = new ArrayList<Coordinate>();
    this.body.add(new Coordinate(4, 2));
  }

  public void setHead(Coordinate coord) {
    this.head = coord;
  }

  public void setBody(Coordinate[] coords) {
    List<Coordinate> coordinateList = Arrays.asList(coords);
    this.body = new ArrayList<>(coordinateList);
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  // Method to move the snake in the current direction
  public void move() {
    Coordinate originalPos = new Coordinate(this.head); //Copy of current position
    body.addFirst(originalPos);
    if (length < body.size())
      body.removeLast();
    originalPos.plus(direction.vector);
    this.head = originalPos;
  }

  public void grow() {
    length++;
  }

  public Direction getDirection() {
    return this.direction;
  }

  public Coordinate getHeadPosition() {
    return this.head;
  }

  public ArrayList<Coordinate> getBody() {
    return this.body;
  }

  public int getLength() {
    return this.length;
  }

}
