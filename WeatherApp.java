import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherApp {

    private static final String API_KEY = "raju"; // Replace with your actual API key
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=Delhi&appid=" + API_KEY;

    public static void main(String[] args) {
        try {
            // Establish HTTP connection
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Get the response code
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read data from the API
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder jsonResponse = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    jsonResponse.append(inputLine);
                }
                in.close();

                // Parse JSON response
                JSONObject weatherData = new JSONObject(jsonResponse.toString());
                String city = weatherData.getString("name");
                JSONObject main = weatherData.getJSONObject("main");
                double temp = main.getDouble("temp");
                int humidity = main.getInt("humidity");

                // Display structured data
                System.out.println("City: " + city);
                System.out.println("Temperature: " + temp + "K");
                System.out.println("Humidity: " + humidity + "%");
            } else {
                System.out.println("Error: Unable to fetch weather data.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
