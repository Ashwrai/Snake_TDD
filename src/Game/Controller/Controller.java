package Game.Controller;

import Game.Model.*;

import java.util.ArrayList;

public class Controller {
    private final Board board; // 2D array of Tiles representing the game board
    private final Snake snake;
    private final Food food;
    private int score;
    private boolean gameOver;

    public Controller(Coordinate boardDim) {
        this.board = new Board(boardDim); //Initialize game board
        // Initial position for the snake
        Coordinate initialPos = new Coordinate(4, 2);
        this.snake = new Snake(initialPos);
        this.food = new Food(boardDim); //Generates food in a random position
        updateBoard();
        this.score = 0;
        this.gameOver = false;
    }

    public Controller(Snake snake, Board board, Food food) {
        this.board = board;
        this.snake = snake;
        this.food = food;
        updateBoard();
        this.score = 0;
        this.gameOver = false;
    }

    // Update the snake state on each iteration
    public void run() {
        this.snake.move();
        checkCollisions();
        updateBoard();
    }

    // Check for collisions with food or walls and update the game state
    public void checkCollisions() {
        if (snake.getDirection() != Direction.NULL) {
            Tile nextTile = this.board.getTile(this.snake.getHeadPosition().getX(), this.snake.getHeadPosition().getY());
            if (nextTile != Tile.BLANK) {
                if (nextTile == Tile.WALL || nextTile == Tile.SNAKE) {
                    gameOver = true;
                } else if (nextTile == Tile.FOOD) {
                    foodCollision();
                } else if (nextTile == Tile.HEAD) {
                    throw new RuntimeException("Head vs Head, execution problem");
                } else {
                    throw new RuntimeException("Out of board (Critical)");
                }
            }
        }
    }

    // Update the game board with new snake, food, and wall positions
    public void updateBoard() {
        ArrayList<Coordinate> snakeBody = snake.getBody();
        Coordinate snakeHead = snake.getHeadPosition();

        // Loop through the board and set tiles based on snake, food, and walls
        for (int i = 0; i < this.board.getBoardDim().getX(); i++) {
            for (int j = 0; j < this.board.getBoardDim().getY(); j++) {
                if (i == 0 || i == this.board.getBoardDim().getX() - 1 || j == 0 || j == this.board.getBoardDim().getY() - 1) {
                    this.board.setTile(i, j, Tile.WALL);
                } else {
                    this.board.setTile(i, j, Tile.BLANK);
                }
            }
        }
        // Loop for setting snake body tiles
        for (Coordinate coordinate : snakeBody) {
            this.board.setTile(coordinate.getX(), coordinate.getY(), Tile.SNAKE);
        }

        // Set snake head and food
        this.board.setTile(snakeHead.getX(), snakeHead.getY(), Tile.HEAD);
        this.board.setTile(food.getX(), food.getY(), Tile.FOOD);
    }

    public void foodCollision() {
        this.snake.grow();
        this.food.generateRandomPosition(); //regenerates food in another position when snake eats the last one
        while (!inBoard(this.food.getPos())) {
            this.food.generateRandomPosition();
        }
        this.score++;
    }

    // Check if a coordinate is within the board boundaries
    public boolean inBoard(Coordinate toCheck) {
        return (toCheck.getX() > 0 && toCheck.getX() < this.board.getBoardDim().getX() - 1 &&
                toCheck.getY() > 0 && toCheck.getY() < this.board.getBoardDim().getY() - 1 &&
                !snake.getBody().contains(toCheck));
    }

    public void setSnakeDirection(Direction direction) {
        switch (direction) {
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
            case null, default -> {
                this.snake.setDirection(direction);
            }
        }
    }

    public Tile[][] getBoardState() {
        return this.board.getBoard();
    }

    public int getScore() {
        return this.score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

}