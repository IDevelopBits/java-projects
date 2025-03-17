import java.awt.*;
import java.util.Random;

public class Apple {
    private int x, y;
    private int gridSize;
    private Random random;

    public Apple(int gridSize) {
        this.gridSize = gridSize;
        this.random = new Random();
        spawnApple();
    }

    public void spawnApple() {
    	// Subtract 10 from maxX and maxY to prevent overlapping with screen
        int maxX = (800 - 10) / gridSize; // 800px width / 10px grid
        int maxY = (600 - 10) / gridSize; // 600px height / 10px grid
        x = random.nextInt(maxX) * gridSize;
        y = random.nextInt(maxY) * gridSize;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, gridSize, gridSize);
    }

    public Point getPosition() {
        return new Point(x, y);
    }
}
