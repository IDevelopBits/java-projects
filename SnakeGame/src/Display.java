import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Display extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Snake snake;
    private Apple apple;
    private boolean gameOver;

    public Display() {
    	startGame();
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        snake.draw(g);
        apple.draw(g);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 16));

        // Draw score
        g.drawString("Score: " + snake.getLength(), 20, 30);
        
        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.setColor(Color.RED);
            g.drawString("GAME OVER", getWidth() / 2 - 120, getHeight() / 2 - 20);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.setColor(Color.WHITE);
            g.drawString("Press 'R' to Restart", getWidth() / 2 - 100, getHeight() / 2 + 20);
            timer.stop();
        }
    }

    public void actionPerformed(ActionEvent e) {
        snake.update();
        
        if (snake.collidedWith(apple)) {
        	apple.spawnApple(); // Move apple to a new location
            snake.grow();
        } else {
        	gameOver = snake.collidedWithSelf();
        }
        
        repaint();
    }
    
    public void startGame() {
        snake = new Snake(400, 300); // Reset snake to starting position
        apple = new Apple(10);       // Generate a new apple
        gameOver = false;
        timer = new Timer(100, this); // 10 fps == 100ms
        timer.start();
        repaint();                   // Redraw the screen
    }


    // Key event handling
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT && !snake.isMovingRight()) snake.setDirection("LEFT");
        if (key == KeyEvent.VK_RIGHT && !snake.isMovingLeft()) snake.setDirection("RIGHT");
        if (key == KeyEvent.VK_UP && !snake.isMovingDown()) snake.setDirection("UP");
        if (key == KeyEvent.VK_DOWN && !snake.isMovingUp()) snake.setDirection("DOWN");
        
        if (key == KeyEvent.VK_R && gameOver) startGame(); // Restart game if "R" is pressed
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake");
        Display gamePanel = new Display();

        frame.add(gamePanel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
