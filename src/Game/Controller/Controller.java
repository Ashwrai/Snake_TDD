package Game.Controller;

import Game.Model.*;

import java.util.ArrayList;

public class Controller {
    private final Tile[][] board; // 2D array of Tiles representing the game board
    private final Snake snake;
    private final Food food;
    private int score;
    private boolean gameOver;

    public Controller(Coordinate boardDim) {
        board = new Tile[boardDim.getX()][boardDim.getY()]; //Initialize game board
        // Initial position for the snake
        Coordinate initialPos = new Coordinate(4, 2);
        snake = new Snake(initialPos);
        food = new Food(boardDim); //Generates food in a random position
        updateBoard();
        score = 0;
        gameOver = false;
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
            Tile nextTile = getNextTile();
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
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (i == 0 || i == this.board.length - 1 || j == 0 || j == board[i].length - 1) {
                    this.board[i][j] = Tile.WALL;
                } else {
                    this.board[i][j] = Tile.BLANK;
                }
            }
        }
        // Loop for setting snake body tiles
        for (Coordinate coordinate : snakeBody) {
            board[coordinate.getX()][coordinate.getY()] = Tile.SNAKE;
        }

        // Set snake head and food
        board[snakeHead.getX()][snakeHead.getY()] = Tile.HEAD;
        board[food.getX()][food.getY()] = Tile.FOOD;
    }

    public void foodCollision() {
        this.snake.grow();
        this.food.generateRandomPosition(); //regenerates food in another position when snake eats the last one
        while (!inBoard(this.food.getPos())) {
            this.food.generateRandomPosition();
        }
        this.score++;
    }

    public void setSnakeHead(Coordinate coord) {
        this.snake.setHead(coord);
        updateBoard();
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

    public void setSnakeBody(Coordinate[] coords) {
        this.snake.setBody(coords);
        updateBoard();
    }

    public void setFoodPos(Coordinate coord) {
        this.food.setPos(coord);
        updateBoard();
    }

    public Tile getNextTile() {
        Coordinate head = snake.getHeadPosition();
        return board[head.getX()][head.getY()];
    }

    // Check if a coordinate is within the board boundaries
    public boolean inBoard(Coordinate toCheck) {
        return (toCheck.getX() > 0 && toCheck.getX() < this.board.length - 1 &&
            toCheck.getY() > 0 && toCheck.getY() < this.board[0].length - 1);
    }

    public Tile[][] getBoardState() {
        return this.board;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Coordinate getSnakeHeadPos() {
        return this.snake.getHeadPosition();
    }

    public int getScore() {
        return this.score;
    }

}