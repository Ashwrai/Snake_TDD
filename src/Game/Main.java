package Game;

import Game.Controller.Controller;
import Game.Model.Coordinate;
import Game.View.Window;

public class Main {
    static final Coordinate BOARD_DIM = new Coordinate(9, 9);
    /**
     * The main method that runs when the application is started.
     * Creates the dimensions of the game board and the game window, then initiates the game execution.
     */
    public static void main(String[] args) {

        new Window(new Controller(BOARD_DIM));

    }
}