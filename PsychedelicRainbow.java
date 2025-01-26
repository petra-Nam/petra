import javax.swing.*;
import java.awt.*;

public class PsychedelicRainbow extends JPanel {

    public static void main(String[] args) {
        // Create a frame to display the panel
        JFrame frame = new JFrame("Equidistant Rainbow");
        PsychedelicRainbow panel = new PsychedelicRainbow();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);  // Set size of the window
        frame.add(panel);  // Add the panel to the frame
        frame.setVisible(true);  // Make the frame visible
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Get the Graphics2D object for advanced drawing
        Graphics2D g2d = (Graphics2D) g;

        // Set the number of rainbow colors
        int numColors = 10;
        
        // Calculate the width for each color band
        int width = getWidth() / numColors;

        // Loop through the colors and draw each band with equidistant rainbow colors
        for (int i = 0; i < numColors; i++) {
            // Calculate hue (equidistant colors)
            float hue = (float) i / numColors;
            
            // Convert from HSV to RGB
            Color color = Color.getHSBColor(hue, 1.0f, 1.0f);
            
            // Set the color for the current rectangle
            g2d.setColor(color);

            // Draw a rectangle to display the color
            g2d.fillRect(i * width, 0, width, getHeight());
        }
    }
}
