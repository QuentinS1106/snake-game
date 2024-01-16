package snakegame;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Snake {
    public static void main(String[] args) {
        
        int ROWS = 10;
        int COLUMNS = 10;
        int START_ROW = 5;
        int START_COLUMN = 5;
        Game game = new Game(ROWS, COLUMNS, START_ROW, START_COLUMN);
        game.spawnFood();
        SnakeWindow s = new SnakeWindow(game);
        try {
            while (true) {
                Thread.sleep(400);
                game.updateSnake();
                s.repaint();
                if (Game.hitSnake) {
                    break;
                }
            }
            System.out.println("You Lose!\nThank you for playing Snake!");
        } catch (InterruptedException e) {
            System.out.println("Interrupted!");
        }
    }
}

class SnakeWindow extends JFrame {
    Game game;
    Graphics g;
    private class SnakeKeyListener implements KeyListener {
        private Game watchedGame;
        public SnakeKeyListener(Game g) {
            watchedGame = g;
        }
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case (KeyEvent.VK_W):
                    if (Game.getDirection() != Direction.DOWN || watchedGame.getLength() == 1) {
                        Game.changeDirection(Direction.UP);
                    }
                    break;
                case (KeyEvent.VK_S):
                    if (Game.getDirection() != Direction.UP || watchedGame.getLength() == 1) {
                        Game.changeDirection(Direction.DOWN);
                    }
                    break;
                case (KeyEvent.VK_A):
                    if (Game.getDirection() != Direction.RIGHT || watchedGame.getLength() == 1) {
                        Game.changeDirection(Direction.LEFT);
                    }
                    break;
                case (KeyEvent.VK_D):
                    if (Game.getDirection() != Direction.LEFT || watchedGame.getLength() == 1) {
                        Game.changeDirection(Direction.RIGHT);
                    }
                    break;
            }
        }
        public void keyReleased(KeyEvent e) {
            // Unneeded
        }
        public void keyTyped(KeyEvent e) {
            // Unneeded
        }
    }
    public SnakeWindow(Game game) {
        this.game = game;
        setTitle("Snake");
        setSize(1000, 1000);
        addKeyListener(new SnakeKeyListener(game));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void paint(Graphics g) {
        this.g = g;
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < game.board.length; i++) {
            for (int j = 0; j < game.board[0].length; j++) {
                switch (game.board[i][j].getType()) {
                    case EMPTY:
                        g2.setColor(Color.BLACK);
                        break;
                    case FOOD:
                        g2.setColor(Color.RED);
                        break;
                    case SNAKE:
                        g2.setColor(Color.GREEN);
                        break;
                }
                g2.fillRect(i * 100, j * 100, 100, 100);
            }
        }
    }
}
