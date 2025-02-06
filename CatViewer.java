import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class CatViewer {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CatViewer::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Random Cat Viewer");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel("Click 'Get Cat' to see a cat!", SwingConstants.CENTER);
        JButton fetchButton = new JButton("Get Cat");

        fetchButton.addActionListener(e -> {
            String imageUrl = getRandomCatImage();
            if (imageUrl != null) {
                ImageIcon catImage = new ImageIcon(fetchImage(imageUrl));
                imageLabel.setIcon(catImage);
                imageLabel.setText(""); // Remove placeholder text
            }
        });

        frame.add(imageLabel, BorderLayout.CENTER);
        frame.add(fetchButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private static String getRandomCatImage() {
        String apiUrl = "https://api.thecatapi.com/v1/images/search";
        try {
            URI uri = new URI(apiUrl); // Use URI instead of deprecated URL(String)
            URL url = uri.toURL(); // Convert URI to URL

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Extract the image URL from the response
            String jsonResponse = response.toString();
            String imageUrl = jsonResponse.split("\"url\":\"")[1].split("\"")[0]; // Simple string manipulation

            return imageUrl;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Image fetchImage(String imageUrl) {
        try {
            URI uri = new URI(imageUrl); // Convert to URI
            URL url = uri.toURL(); // Convert URI to URL
            return new ImageIcon(url).getImage();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
