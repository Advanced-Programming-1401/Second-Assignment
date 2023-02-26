import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;
import java.util.Objects;

public class WeatherApp{
    // Copy your API-KEY here
    public final static String apiKey = "c3c9dd1e2b7749bab87141259232502";
    // TODO: Write main function

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        String city = sc.next();
        JSONObject Weather = new JSONObject(getWeatherData(city));
        double Temperature = getTemperature(Weather);
        int Humidity=getHumidity(Weather);
        System.out.println(getWeatherData(city));
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
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            connection.disconnect();
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

// TODO: Write getTemperature function returns celsius temperature of city by given json string
    public static double getTemperature(String weatherJson){
        double cur = 0.0;
        JSONObject Weather = new JSONObject(weatherJson);
        answer = Weather.getJSONObject("current").getDouble("Temperature");
        return cur;
    }

// TODO: Write getHumidity function returns humidity percentage of city by given json string
    public static int getHumidity(String weatherJson){
        int cur = 0;
        JSONObject Weather = new JSONObject(weatherJson);
        answer = Weather.getJSONObject("current").getInt("Humidity");
        return cur;
    }
}