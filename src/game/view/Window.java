package game.view;

import game.controller.Controller;
import game.model.Direction;
import game.model.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * Represents the main game window with the game board.
 */

public class Window extends JFrame implements ActionListener {
    private final Controller controller;
    private GamePanel gamePanel;
    JButton newGameButton;
    JButton highestScoresButton;
    JButton exitButton;
    JPanel menu;
    private Direction keyEvent;

    public Window(Controller controller) {
        this.controller = controller;
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Default close operation to exit the window
        this.setLayout(new BorderLayout());
        this.gamePanel = new GamePanel(controller);
        this.menu = menu();
        this.add(menu);
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public JPanel menu() {
        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(4, 1));

        newGameButton = new JButton("New game");
        newGameButton.addActionListener(this);
        highestScoresButton = new JButton("Highest Scores");
        highestScoresButton.addActionListener(this);
        exitButton = new JButton("Exit game");
        exitButton.addActionListener(this);

        menu.add(newGameButton);
        menu.add(highestScoresButton);
        menu.add(exitButton);

        return menu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == newGameButton) {
            GameThread gameThread = new GameThread(this);
            gameThread.start();
        } else if (e.getSource() == highestScoresButton) {
            displayScoresPanel();
        } else if (e.getSource() == exitButton) {
            this.dispose();
        } else {
            throw new IllegalStateException("Unexpected value: " + e.getSource());
        }
    }

    private void displayScoresPanel() {
        JFrame scoresFrame = new JFrame("Scores");
        scoresFrame.setLayout(new BorderLayout());

        JTextArea scoresTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(scoresTextArea);
        scoresFrame.add(scrollPane, BorderLayout.CENTER);

        try {
            File scoresFile = new File("usernames.txt");
            Scanner scanner = new Scanner(scoresFile);

            while (scanner.hasNextLine()) {
                scoresTextArea.append(scanner.nextLine() + "\n");
            }

            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        scoresFrame.setSize(300, 300);
        scoresFrame.setVisible(true);
    }

        static class GamePanel extends JPanel {
        private final Controller controller;
        private Tile[][] board;

        public GamePanel(Controller controller) {
            this.controller = controller;
            Tile[][] board = controller.getBoardState();
            this.setBoard(board);
        }

        public void setBoard(Tile[][] board) {
            this.board = board;
            repaint(); // Request the panel to be repainted
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (board != null) {
                int cellSize = 37; // Adjust the cell size as needed

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

                // Display the score
                String scoreText = "Score: " + controller.getScore();
                g.setColor(Color.BLACK);
                g.drawString(scoreText, 10, getHeight() - 10); // Adjust the position as needed
            }
        }
    }

    class GameThread extends Thread {

        private final JFrame frame;

        public GameThread(JFrame frame) {
            this.frame = frame;
        }

        @Override
        public void run() {
            frame.remove(menu);
            frame.add(gamePanel, BorderLayout.CENTER);
            frame.setFocusable(true);
            frame.setFocusTraversalKeysEnabled(false);
            frame.addKeyListener(new ArrowKeyListener());
            frame.setSize(400, 400);
            // Creates a new GamePanel and adds it to the window
            keyEvent = Direction.NULL;
            // Add a KeyListener for arrow keys
            Tile[][] board = controller.getBoardState();
            gamePanel.setBoard(board);

            SwingUtilities.invokeLater(() -> {
                frame.setVisible(true); // Make the frame visible here
                frame.requestFocusInWindow(); // Request focus
            });

            //Main game loop
            while (true) {
                try {
                    long speedUp = controller.getSpeedUp();

                    long sleepTime = 500 - speedUp;
                    if (sleepTime < 100) {
                        sleepTime = 100; // Ensuring a minimum sleep time for smoother gameplay
                    }

                    Thread.sleep(sleepTime); // Adjust the sleep time to control the snake speed for the player
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                controller.setSnakeDirection(keyEvent);
                controller.run(); //updates board with the actual state
                if (controller.isGameOver() || controller.isGameWon()) {
                    break;
                }
                board = controller.getBoardState();
                gamePanel.setBoard(board);
            }

            frame.remove(gamePanel);
            JLabel finalLabel;
            if (controller.isGameOver()) {
                finalLabel = new JLabel("Game Over!");
            } else {
                finalLabel = new JLabel("You won !");
            }

            finalPanel(finalLabel);
        }

        class ArrowKeyListener extends KeyAdapter {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key) {
                    case (KeyEvent.VK_LEFT):
                        keyEvent = Direction.LEFT;
                        break;
                    case (KeyEvent.VK_RIGHT):
                        keyEvent = Direction.RIGHT;
                        break;
                    case (KeyEvent.VK_UP):
                        keyEvent = Direction.UP;
                        break;
                    case (KeyEvent.VK_DOWN):
                        keyEvent = Direction.DOWN;
                        break;
                }
            }
        }

        public void finalPanel(JLabel finalLabel) {
            JPanel finalPanel = new JPanel();

            JTextField usernameField = new JTextField(20);
            finalPanel.add(finalLabel);

            finalPanel.add(new JLabel("To Keep the Score, Enter Username: "));
            finalPanel.add(usernameField);

// Create a button to save username and handle the file writing
            JButton saveButton = new JButton("Save Username");
            saveButton.addActionListener(e -> {
                String username = usernameField.getText();
                if (!username.isEmpty()) {
                    saveUsernameToFile(username);
                }
                frame.dispose();
            });
            finalPanel.add(saveButton);

// Add the game over panel to the frame after the game loop finishes
            frame.add(finalPanel, BorderLayout.CENTER);

// Repaint and update the UI
            frame.revalidate();
            frame.repaint();
        }

        private void saveUsernameToFile(String username) {
            try {
                FileWriter writer = new FileWriter("usernames.txt", true); // Appends to file
                writer.write(username + " " + controller.getScore() + "\n");
                writer.close();
                System.out.println("Username saved successfully!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
