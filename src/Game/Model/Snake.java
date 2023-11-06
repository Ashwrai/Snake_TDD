package Game.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Snake {

  private ArrayList<Coordinate> body;
  private Coordinate head;
  private Direction direction;
  private int length;

  public Snake(Coordinate initialPos){
    this.head = initialPos;
    this.direction = Direction.RIGHT;
    this.length = 0;
    this.body = new ArrayList<Coordinate>();
    body.add(initialPos);
  }

  public void setHead(Coordinate coord){
    this.head = coord;
  }
  public void setBody(Coordinate[] coords){
    List<Coordinate> coordinateList = Arrays.asList(coords);
    this.body = new ArrayList<>(coordinateList);
  }

  public void setDirection(Direction direction){
    this.direction = direction;
  }

  public void move(){
    Coordinate originalPos = this.head;
    body.add(originalPos);
    if (length <= body.size())
      body.removeFirst();
    originalPos.plus(direction.vector);
    this.head = originalPos;
  }
  public void grow(){
    length++;
  }
  public Direction getDirection(){
    return this.direction;
  }
  public Coordinate getHeadPosition(){
    return this.head;
  }

  public ArrayList<Coordinate> getBody(){
    return this.body;
  }

}
