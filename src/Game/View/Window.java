package Game.View;
import Game.Controller.Controller;
import Game.Model.Direction;
import Game.Model.Tile;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame implements Runnable {
  private Controller controller;
  private GamePanel gamePanel;

  private boolean keyEventHandled;

  public Window(Controller controller) {
    this.controller = controller;
    this.setFocusable(true);
    this.setFocusTraversalKeysEnabled(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(400, 400);

    gamePanel = new GamePanel(controller);
    this.add(gamePanel);

    // Add a KeyListener for arrow keys
    this.addKeyListener(new ArrowKeyListener());

    this.setVisible(true);
  }

  @Override
  public void run() {
    // Your game loop logic here
    Tile[][] board = controller.getBoardState();
    gamePanel.setBoard(board);
    while (true) {
      try {
        Thread.sleep(500); // Adjust the sleep time to control the game loop speed
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      controller.run();
      if (controller.isGameOver()) {
        break;
      }
      board = controller.getBoardState();
      gamePanel.setBoard(board);
      keyEventHandled = true;
    }
  }

  class GamePanel extends JPanel {
    private Controller controller;
    private Tile[][] board;

    public GamePanel(Controller controller) {
      this.controller = controller;
    }

    public void setBoard(Tile[][] board) {
      this.board = board;
      repaint(); // Request the panel to be repainted
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      if (board != null) {
        int cellSize = 20; // Adjust the cell size as needed

        for (int i = 0; i < board.length; i++) {
          for (int j = 0; j < board[i].length; j++) {
            int x = j * cellSize;
            int y = i * cellSize;

            switch (board[i][j]) {
              case WALL:
                g.setColor(Color.BLACK);
                break;
              case BLANK:
                g.setColor(Color.WHITE);
                break;
              case FOOD:
                g.setColor(Color.RED);
                break;
              case SNAKE:
                g.setColor(Color.GREEN);
                break;
              case HEAD:
                g.setColor(Color.BLUE);
                break;
              default:
                throw new RuntimeException("Draw game error out of bounds");
            }
            g.fillRect(x, y, cellSize, cellSize);
          }
        }
      }
    }
  }

  class ArrowKeyListener extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
      int key = e.getKeyCode();
      Direction dir = null;
      if (keyEventHandled) {
        switch (key) {
          case (KeyEvent.VK_LEFT):
            dir = Direction.LEFT;
            break;
          case (KeyEvent.VK_RIGHT):
            dir = Direction.RIGHT;
            break;
          case (KeyEvent.VK_UP):
            dir = Direction.UP;
            break;
          case (KeyEvent.VK_DOWN):
            dir = Direction.DOWN;
            break;
        }
        if (dir != null) {
          controller.setSnakeDirection(dir);
          keyEventHandled = false;
        }
      }
    }
  }
}
