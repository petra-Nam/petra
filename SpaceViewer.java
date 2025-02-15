import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SpaceViewer {
    private static final String API_KEY = "DEMO_KEY"; // Replace with your NASA API key

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SpaceViewer::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("NASA Astronomy Picture of the Day");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel("Click 'Get Image' to see today's space picture!", SwingConstants.CENTER);
        JButton fetchButton = new JButton("Get Image");

        fetchButton.addActionListener(e -> {
            String imageUrl = getNASAImage();
            if (imageUrl != null) {
                updateImageLabel(imageLabel, imageUrl);
            }
        });

        frame.add(imageLabel, BorderLayout.CENTER);
        frame.add(fetchButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private static String getNASAImage() {
        String apiUrl = "https://api.nasa.gov/planetary/apod?api_key=" + API_KEY;
        try {
            @SuppressWarnings("deprecation")
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Manually parse the image URL from the response
            String jsonResponse = response.toString();
            int urlStartIndex = jsonResponse.indexOf("\"url\":\"") + 7;
            int urlEndIndex = jsonResponse.indexOf("\"", urlStartIndex);
            String imageUrl = jsonResponse.substring(urlStartIndex, urlEndIndex);

            return imageUrl;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void updateImageLabel(JLabel imageLabel, String imageUrl) {
        try {
            @SuppressWarnings("deprecation")
            ImageIcon originalIcon = new ImageIcon(new URL(imageUrl));
            Image scaledImage = originalIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
            imageLabel.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            imageLabel.setText("Failed to load image");
        }
    }
}
