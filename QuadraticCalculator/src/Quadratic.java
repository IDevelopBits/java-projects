import java.awt.Graphics;
import java.awt.Polygon;

public class Quadratic {
	private double a; // represents ax^2
	private double b; // represents bx
	private double c; // represents c
	private double discriminant;
	private double[] zeros;
	
	
	public Quadratic(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
		zeros = new double[2];	
		
		discriminant = b * b - 4 * a * c;
		if (discriminant < 0) {
			throw new ArithmeticException("Imaginary values are not supported");
		}
		
		calculateZeros();
	}
	
	private void calculateZeros() {
        if (discriminant > 0) {
            // Two real and distinct roots
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            zeros[0] = root1;
            zeros[1] = root2;
            
            //System.out.println("Roots are real and distinct: " + root1 + " and " + root2);
        } else if (discriminant == 0) {
            // One real and repeated root
            double root = -b / (2 * a);
            zeros[0] = root;
            zeros[1] = root;
            //System.out.println("Root is real and repeated: " + root);
        }
	}
	
	public double[] getZeros() {
		return zeros;
	}
	
    public void graph(Graphics g, int width, int height) {
        Polygon p = new Polygon();
        int scale = 20; // Scaling factor for better visualization

        for (int x = -width / 2; x <= width / 2; x++) {
            double xReal = x / (double) scale;
            double yReal = a * xReal * xReal + b * xReal + c;
            int xPixel = width / 2 + x;
            int yPixel = height / 2 - (int) (yReal * scale);

            p.addPoint(xPixel, yPixel);
        }
        g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
    }
}
