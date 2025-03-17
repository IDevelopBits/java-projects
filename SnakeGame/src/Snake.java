import java.awt.*;
import java.util.LinkedList;

public class Snake {
    private LinkedList<Point> segments; // every part of the snake
    private int speed; // the speed or the snake
    private String direction; // the snake's current direction

    public Snake(int x, int y) {
        segments = new LinkedList<>();
        segments.add(new Point(x, y));
        this.speed = 10;
        this.direction = "RIGHT"; // Default movement direction
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        for (Point segment : segments) {
            g.fillRect(segment.x, segment.y, 10, 10);
        }
    }

    public void update() {
        Point head = new Point(getHead());

        // Move in the current direction
        switch (direction) {
            case "LEFT":  head.x -= speed; break;
            case "RIGHT": head.x += speed; break;
            case "UP":    head.y -= speed; break;
            case "DOWN":  head.y += speed; break;
        }

        segments.addFirst(head); // Add new head position
        segments.removeLast(); // Remove last segment (no growth yet)
    }
    
    public void grow() {
        Point tail = segments.getLast(); // Get the last segment (tail)
        segments.addLast(new Point(tail)); // Add a new segment at the same position
    }
    
    public boolean collidedWith(Apple apple) {
    	return getHead().equals(apple.getPosition());
    }
    
    public boolean collidedWithSelf() {
        for (int i = 1; i < getLength(); i++) { // Start at 1 since 0 is the head itself
        	if (getHead().equals(segments.get(i))) {
        		return true;
        	}
        }
        return false;
    }

    // Get snake's head
    public Point getHead() {
        return segments.getFirst();
    }
    
    public LinkedList<Point> getBody() {
    	return segments;
    }
    
    public int getLength() {
    	return segments.size();
    }

    // Movement restrictions (to prevent reversing)
    public boolean isMovingLeft() { return direction.equals("LEFT"); }
    public boolean isMovingRight() { return direction.equals("RIGHT"); }
    public boolean isMovingUp() { return direction.equals("UP"); }
    public boolean isMovingDown() { return direction.equals("DOWN"); }

    // Set new direction
    public void setDirection(String newDirection) {
        this.direction = newDirection;
    }
}
