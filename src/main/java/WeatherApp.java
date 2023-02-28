import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class WeatherApp {
    // Copy your API-KEY here
    public final static String apiKey = "11d6d7ae006f4890994141146232602";
    // TODO: Write main function
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String city = input.nextLine();
    if (getWeatherData(city) != null)
    {
        callAllFunctions(city);
    }
    else
    {
        System.out.print("Location is INVALID!");
    }
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
            return stringBuilder.toString();
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }
    }

    public static String currentLocation(String weatherJson)
    {
        JSONObject json = new JSONObject(weatherJson);
        String regin;
        regin = json.getJSONObject("location").getString("region");
        return regin;
    }

    public static String localTime(String weatherJson)
    {
        JSONObject json = new JSONObject(weatherJson);
        String local_time;
        local_time = json.getJSONObject("location").getString("localtime");
        return local_time;
    }

    public static double getTemperature(String weatherJson){
        JSONObject json = new JSONObject(weatherJson);
        double answer = 0.0;
        answer = json.getJSONObject("current").getDouble("temp_c");
        return answer;
    }

    public static double getFeelsLikeTem (String weatherJson)
    {
        JSONObject json = new JSONObject(weatherJson);
        double feelsLikeTem = 0.0;
        feelsLikeTem = json.getJSONObject("current").getDouble("feelslike_c");
        return feelsLikeTem;
    }

    public static int getHumidity(String weatherJson){
        JSONObject json = new JSONObject(weatherJson);
        int answer = 0;
        answer = json.getJSONObject("current").getInt("humidity");
        return answer;
    }

    public static double getWindSpeed(String weatherJson)
    {
        JSONObject json = new JSONObject(weatherJson);
        double wind_speed = 0.0;
        wind_speed = json.getJSONObject("current").getDouble("wind_kph");
        return wind_speed;
    }

    public static String getWindDirection(String weatherJson)
    {
        JSONObject json = new JSONObject(weatherJson);
        String wind_dir;
        wind_dir = json.getJSONObject("current").getString("wind_dir");
        return wind_dir;
    }

    public static void callAllFunctions(String city)
    {
        String weatherData      = getWeatherData(city);
        String current_location = currentLocation(weatherData);
        String local_time       = localTime(weatherData);
        double Temperature      = getTemperature(weatherData);
        double feels_like       = getFeelsLikeTem(weatherData);
        int Humidity            = getHumidity(weatherData);
        double wind_speed       = getWindSpeed(weatherData);
        String wind_direction   = getWindDirection(weatherData);

        System.out.print("Current Location: " + current_location + "\n" + "Local Time: " + local_time + "\n"+
                        "Temperature(Celsius): " + Temperature + "\n" +  "Feels like(Celsius): " + feels_like + "\n" + "Humidity: " + Humidity + "\n" +
                        "Wind Speed(kph): " + wind_speed + "\n" + "Wind Direction: " + wind_direction);
    }
}