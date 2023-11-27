package test;

import game.controller.Controller;
import game.model.Coordinate;
import org.junit.jupiter.api.Test;

public class LoopTesting {

    @Test
    public void maxScoreLoopTest() {
        Controller controller = new Controller(new Coordinate(12, 12));

        controller.getMaxScore();

    }
}
