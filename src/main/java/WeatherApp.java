import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class WeatherApp {
    // Copy your API-KEY here
    public final static String apiKey = "16f8062cbeac4e2fa29193133232502";
    // TODO: Write main function
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String city = input.next();
        String json = getWeatherData(city);
        printTemp(getTemperatureC(json),getTemperatureF(json),city);
        printHumidity(getHumidity(json),city);
        printWindSpeed(getWindSpeedKph(json),getWindSpeedMph(json),city);
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
            e.printStackTrace();
            return null;
        }
    }

    // TODO: Write getTemperature function returns celsius temperature of city by given json string
    public static double getTemperatureC(String weatherJson){
        JSONObject json = new JSONObject(weatherJson);
        double answer = json.getJSONObject("current").getDouble("temp_c");
        return answer;
    }
    public static double getTemperatureF(String weatherJson){
        JSONObject json = new JSONObject(weatherJson);
        double answer = json.getJSONObject("current").getDouble("temp_f");
        return answer;
    }
    public static void printTemp(double temperatureC,double tempretureF, String city){
        System.out.println(city + "'s current Temperature is " + temperatureC + " Celsius and " + tempretureF + " Fahrenheit");
    }
    // TODO: Write getHumidity function returns humidity percentage of city by given json string
    public static int getHumidity(String weatherJson){
        JSONObject json = new JSONObject(weatherJson);
        int answer = json.getJSONObject("current").getInt("humidity");
        return answer;
    }
    public static void printHumidity(int humidity, String city){
        System.out.println(city + "'s current Humidity is " + humidity);
    }
    //Bonus part
    public static double getWindSpeedKph(String weatherJson){
        JSONObject json = new JSONObject(weatherJson);
        double answer = json.getJSONObject("current").getDouble("wind_kph");
        return answer;
    }
    public static double getWindSpeedMph(String weatherJson){
        JSONObject json = new JSONObject(weatherJson);
        double answer = json.getJSONObject("current").getDouble("wind_mph");
        return answer;
    }
    public static void printWindSpeed(double kph,double mph, String city){
        System.out.println(city + "'s current wind speed is " + kph + " kph and " + mph + " mph" );
    }
}
