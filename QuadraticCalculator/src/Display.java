import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display {
    private static GraphPanel graphPanel;

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Quadratic Grapher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());

        // Create input panel with BoxLayout (vertical stacking)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));  // BoxLayout for vertical layout

        // Create input fields and labels
        JLabel labelA = new JLabel("a:");
        JTextField fieldA = new JTextField(5);

        JLabel labelB = new JLabel("b:");
        JTextField fieldB = new JTextField(5);

        JLabel labelC = new JLabel("c:");
        JTextField fieldC = new JTextField(5);

        
        JLabel equationLabel = new JLabel();        
        JButton solveButton = new JButton("Solve");
        JLabel resultLabel = new JLabel("Result: ");
        
        // Add some space between the components
        labelA.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldA.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelB.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldB.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelC.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldC.setAlignmentX(Component.CENTER_ALIGNMENT);
        equationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        solveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initialize graph panel
        graphPanel = new GraphPanel();

        // Add action listener to the button
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a, b, c;

                try {
                    a = Integer.parseInt(fieldA.getText());
                    b = Integer.parseInt(fieldB.getText());
                    c = Integer.parseInt(fieldC.getText());

                    // Create Quadratic object and get zeros
                    Quadratic quad = new Quadratic(a, b, c);
                    double[] zeros = quad.getZeros();
                    double root1 = zeros[0];
                    double root2 = zeros[1];

                    String result;
                    if (root1 == root2) {
                        result = "Root is real and repeated: " + root1;
                    } else {
                        result = "Roots are real and distinct: " + root1 + " and " + root2;
                    }
                    resultLabel.setText(result);
                    equationLabel.setText(String.format("%sx^2 + %sbx + %s", fieldA.getText(), fieldB.getText(), fieldC.getText()));

                    // Update graph with new coefficients
                    graphPanel.setQuadratic(quad);
                    graphPanel.repaint();
                } catch (NumberFormatException e1) {
                    resultLabel.setText("Invalid input! Please enter numbers.");
                } catch (ArithmeticException e2) {
                	equationLabel.setText(String.format("%sx^2 + %sbx + %s", fieldA.getText(), fieldB.getText(), fieldC.getText()));
                    resultLabel.setText(e2.getMessage());
                }
            }
        });

        // Add components to the input panel
        inputPanel.add(labelA);
        inputPanel.add(fieldA);
        inputPanel.add(labelB);
        inputPanel.add(fieldB);
        inputPanel.add(labelC);
        inputPanel.add(fieldC);
        inputPanel.add(equationLabel);
        inputPanel.add(solveButton);
        inputPanel.add(resultLabel);

        // Add panels to frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(graphPanel, BorderLayout.CENTER);

        // Show the frame
        frame.setVisible(true);
    }
}

//GraphPanel class to draw the quadratic function with numbers
@SuppressWarnings("serial")
class GraphPanel extends JPanel {
 private Quadratic quadratic;

 public GraphPanel() {
     this.quadratic = new Quadratic(1, 0, 0);
 }

 public void setQuadratic(Quadratic quadratic) {
     this.quadratic = quadratic;
 }

 @Override
 protected void paintComponent(Graphics g) {
     super.paintComponent(g);
     Graphics2D g2 = (Graphics2D) g;

     int width = getWidth();
     int height = getHeight();
     int scale = 40; // Scale factor for grid spacing

     // Clear the panel
     g2.setColor(Color.WHITE);
     g2.fillRect(0, 0, width, height);

     // Draw axes
     g2.setColor(Color.BLACK);
     g2.drawLine(0, height / 2, width, height / 2); // X-axis
     g2.drawLine(width / 2, 0, width / 2, height); // Y-axis

     // Draw tick marks and numbers on X and Y axes
     g2.setFont(new Font("Arial", Font.PLAIN, 12));

     for (int x = -width / 2; x <= width / 2; x += scale) {
         int xPixel = width / 2 + x;
         g2.drawLine(xPixel, height / 2 - 5, xPixel, height / 2 + 5);
         g2.drawString(Integer.toString(x / scale), xPixel - 5, height / 2 + 20);
     }

     for (int y = -height / 2; y <= height / 2; y += scale) {
         int yPixel = height / 2 - y;
         g2.drawLine(width / 2 - 5, yPixel, width / 2 + 5, yPixel);
         if (y != 0) { // Avoid overlapping with X-axis label
             g2.drawString(Integer.toString(y / scale), width / 2 + 10, yPixel + 5);
         }
     }

     // Call the graph method of Quadratic class
     g2.setColor(Color.RED);
     quadratic.graph(g2, width, height);
 }
}