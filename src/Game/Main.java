package Game;

import Game.Controller.Controller;
import Game.Model.Coordinate;
import Game.View.Window;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Intro with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        Coordinate boardDim = new Coordinate(9, 9);
        Window gameWindow = new Window(new Controller(boardDim));
        gameWindow.run();
    }
}