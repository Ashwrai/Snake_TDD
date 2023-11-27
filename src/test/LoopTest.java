package test;

import game.controller.Controller;
import game.model.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// We are testing the only loop that does not require a view. Controller.getMaxScore()
public class LoopTest {
    @Test
    public void testGetMaxScoreWithBoundaryCases() {
        // Testing with minimal and moderately small board sizes
        testMaxScore(new Coordinate(4, 4), 4);
        testMaxScore(new Coordinate(5, 5), 9);
    }

    @Test
    public void testGetMaxScoreWithStandardCases() {
        // Testing with reasonably sized boards
        testMaxScore(new Coordinate(5, 5), 9);
        testMaxScore(new Coordinate(10, 10), 64);
    }

    @Test
    public void testGetMaxScoreWithExtremeCases() {
        // Testing with larger board sizes
        testMaxScore(new Coordinate(20, 20), 324);
        testMaxScore(new Coordinate(1000, 1000), 996004 );
    }

    // Helper method to perform tests for getMaxScore() based on different board dimensions
    private void testMaxScore(Coordinate boardDim, int expectedMaxScore) {
        Controller controller = new Controller(boardDim);
        int actualMaxScore = controller.getMaxScore();
        assertEquals(expectedMaxScore, actualMaxScore);
    }
}
