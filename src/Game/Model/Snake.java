package Game.Model;

import java.util.ArrayList;

public class Snake {

  private ArrayList<Coordinate> body;
  private Coordinate head;
  private Direction direction;
  private int length;

  public Snake(Coordinate initialPos){
    this.head = initialPos;
    this.direction = Direction.RIGHT;
    this.length = 1;
    this.body = new ArrayList<Coordinate>();
    body.add(initialPos);
  }

  public void setDirection(Direction direction){
    this.direction = direction;
  }

  public void move(){
    Coordinate initialPos = this.head;
    initialPos.plus(direction.vector);
    this.head = initialPos;
  }

  public Coordinate getHeadPosition(){
    return this.head;
  }

}
