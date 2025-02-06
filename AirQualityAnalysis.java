
import javax.swing.*;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AirQualityAnalysis {

    private static final String API_KEY = "your_api_key";  // Replace with your API key

    // Method to get city coordinates (latitude and longitude)
    public static double[] getCityCoordinates(String city) throws Exception {
        String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY;
        HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
        connection.setRequestMethod("GET");

        InputStreamReader reader = new InputStreamReader(connection.getInputStream());
        JsonObject jsonResponse = JsonParser.parseReader(reader).getAsJsonObject();
        double lat = jsonResponse.getAsJsonObject("coord").get("lat").getAsDouble();
        double lon = jsonResponse.getAsJsonObject("coord").get("lon").getAsDouble();

        return new double[]{lat, lon};
    }

    // Method to fetch air quality data for the city
    public static List<Double> getAirQualityData(double lat, double lon) throws Exception {
        String apiUrl = "http://api.openweathermap.org/data/2.5/air_pollution?lat=" + lat + "&lon=" + lon + "&appid=" + API_KEY;
        HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
        connection.setRequestMethod("GET");

        InputStreamReader reader = new InputStreamReader(connection.getInputStream());
        JsonObject jsonResponse = JsonParser.parseReader(reader).getAsJsonObject();

        List<Double> dailyPm25 = new ArrayList<>();
        for (int i = 0; i < jsonResponse.getAsJsonArray("list").size(); i++) {
            JsonObject hourData = jsonResponse.getAsJsonArray("list").get(i).getAsJsonObject();
            double pm25 = hourData.getAsJsonObject("components").get("pm2_5").getAsDouble();
            dailyPm25.add(pm25);
        }

        return dailyPm25;
    }

    // Method to calculate summary statistics
    public static void displaySummaryStatistics(List<Double> dailyPm25) {
        double sum = 0;
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;

        for (double pm25 : dailyPm25) {
            sum += pm25;
            if (pm25 > max) max = pm25;
            if (pm25 < min) min = pm25;
        }

        double weeklyAvg = sum / dailyPm25.size();

        System.out.println("Weekly Average PM2.5: " + weeklyAvg);
        System.out.println("Highest PM2.5: " + max);
        System.out.println("Lowest PM2.5: " + min);
    }

    // Method to visualize PM2.5 data using JFreeChart
    public static void plotGraph(List<Double> dailyPm25) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < dailyPm25.size(); i++) {
            dataset.addValue(dailyPm25.get(i), "PM2.5 Levels", "Day " + (i + 1));
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Air Quality Over Week", 
                "Day", 
                "PM2.5 (µg/m³)", 
                dataset, 
                PlotOrientation.VERTICAL, 
                true, 
                true, 
                false
        );

        // Display chart in a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        JFrame frame = new JFrame("Air Quality Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        String city = "Berlin";  // Specify the city

        try {
            double[] coordinates = getCityCoordinates(city);
            List<Double> airQualityData = getAirQualityData(coordinates[0], coordinates[1]);

            // Display summary statistics
            displaySummaryStatistics(airQualityData);

            // Plot PM2.5 data
            plotGraph(airQualityData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
