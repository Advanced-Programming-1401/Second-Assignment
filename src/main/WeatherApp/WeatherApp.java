import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class WeatherApp {
    // Copy your API-KEY here
    public final static String apiKey = "c3c9dd1e2b7749bab87141259232502";
    // TODO: Write main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String city = sc.next();
        String json = getWeatherData(city);
        double temp_c = getTemperature(json);
        int humidity = getHumidity(json);
        double windspeed = getWindSpeed(json);
        String wind_dir = getWindDirection(json);

        System.out.println("Current Temperature is: " + temp_c + " Celsius");
        System.out.println("Current Humidity is: " + humidity);
        System.out.println("Current Wind speed is: " + windspeed + " km\\h, In direction of : " + wind_dir);

    }

    /**
     * Retrieves weather data for the specified city.
     *
     * @param city the name of the city for which weather data should be retrieved
     * @return a string representation of the weather data, or null if an error occurred
     */
    public static String getWeatherData(String city) {
        try {
            URL url = new URL("http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // TODO: Write getTemperature function returns Celsius temperature of city by given json string
    public static double getTemperature(String weatherJson){
        JSONObject obj = new JSONObject(weatherJson);
        JSONObject current = obj.getJSONObject("current");
        double temp_c = current.getDouble("temp_c");
        return temp_c;
    }

    // TODO: Write getHumidity function returns humidity percentage of city by given json string
    public static int getHumidity(String weatherJson){
        JSONObject obj = new JSONObject(weatherJson);
        JSONObject current = obj.getJSONObject("current");
        int humidity = current.getInt("humidity");
        return humidity;
    }

    public static double getWindSpeed(String weatherJson){
        JSONObject obj = new JSONObject(weatherJson);
        JSONObject current = obj.getJSONObject("current");
        double wind_kph = current.getDouble("wind_kph");
        return wind_kph;
    }

    public static String getWindDirection(String weatherJson){
        JSONObject obj = new JSONObject(weatherJson);
        JSONObject current = obj.getJSONObject("current");
        String wind_dir = current.getString("wind_dir");
        return wind_dir;
    }

}
