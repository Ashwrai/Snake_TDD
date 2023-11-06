package Game.Controller;

import Game.Model.*;

import java.util.ArrayList;

public class Controller {
    private Tile[][] board;
    private Snake snake;
    private Food food;
    private int score;
    private boolean gameOver;
    private final Coordinate initialPos = new Coordinate(4,2);
    public Controller(Coordinate boardDim) {
        board = new Tile[boardDim.getX()][boardDim.getY()];
        // Center pos
        snake = new Snake(initialPos);
        food = new Food(boardDim);
        updateBoard();
        score = 0;
    }

    public void run(){
        this.snake.move();
        checkCollisions();
        updateBoard();
    }

    // Check for collisions with food and update the game state
    public void checkCollisions() {
        Tile nextTile = getNextTile();
        if (nextTile != Tile.BLANK) {
            if (nextTile == Tile.WALL || nextTile == Tile.SNAKE) {
                gameOver = true;
            } else if (nextTile == Tile.FOOD){
                foodCollision();
            } else if (nextTile == Tile.HEAD){
                throw new RuntimeException("Head vs Head, execution problem");
            } else {
                throw new RuntimeException("Out of board (?)");
            }
        }
    }
    public void updateBoard(){
        ArrayList<Coordinate> snakeBody = snake.getBody();
        Coordinate snakeHead = snake.getHeadPosition();
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (i == 0 || i == this.board.length - 1 || j == 0 || j == board[i].length - 1) {
                    this.board[i][j] = Tile.WALL;
                } else {
                    this.board[i][j] = Tile.BLANK;
                }
            }
        }
        for (int i = 0; i < snakeBody.size(); i++){
            board[snakeBody.get(i).getX()][snakeBody.get(i).getY()] = Tile.SNAKE;
        }

        board[snakeHead.getX()][snakeHead.getY()] = Tile.HEAD;
        board[food.getX()][food.getY()] = Tile.FOOD;
    }

    public void foodCollision(){
        this.snake.grow();
        this.food.generateRandomPosition();
        while (!inBoard(this.food.getPos())) {
            this.food.generateRandomPosition();
        }
        this.score++;
    }

    public void setSnakeHead(Coordinate coord){
        this.snake.setHead(coord);
        updateBoard();
    }
    public void setSnakeDirection(Direction direction){
        switch (direction){
            case DOWN -> {
                if (snake.getDirection() != Direction.UP)
                    this.snake.setDirection(direction);
            }
            case UP -> {
                if (snake.getDirection() != Direction.DOWN)
                    this.snake.setDirection(direction);
            }
            case RIGHT -> {
                if (snake.getDirection() != Direction.LEFT)
                    this.snake.setDirection(direction);
            }
            case LEFT -> {
                if (snake.getDirection() != Direction.RIGHT)
                    this.snake.setDirection(direction);
            }
        }
    }

    public void setSnakeBody(Coordinate[] coords){
        this.snake.setBody(coords);
        updateBoard();
    }
    public void setFoodPos(Coordinate coord){
        this.food.setPos(coord);
        updateBoard();
    }
    public Tile getNextTile(){
        Coordinate head = snake.getHeadPosition();
        return board[head.getX()][head.getY()];
    }

    public boolean inBoard(Coordinate toCheck){
        return (toCheck.getX() > 0 && toCheck.getX() < this.board.length - 1 &&
                toCheck.getY() > 0 && toCheck.getY() < this.board[0].length -1);
    }

    public Tile[][] getBoardState(){
        return this.board;
    }
    public boolean isGameOver(){
        return gameOver;
    }

    public Coordinate getSnakeHeadPos(){
        return this.snake.getHeadPosition();
    }
    public int getScore() {
        return this.score;
    }

}
