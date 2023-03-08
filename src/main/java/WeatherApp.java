import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class WeatherApp {
    public final static String apiKey = "197c936379844258894151857230103";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the city name :");
        String city = sc.next();

        String weatherData = getWeatherData(city);
        double temperature = getTemperature(weatherData);
        int humidity = getHumidity(weatherData);

        System.out.println("The temperature in " + city + " is " + temperature);
        System.out.println("The humidity percentage in " + city + " is " + humidity);
    }

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

    public static double getTemperature(String weatherJson){
        double answer = 0.0;
        JSONObject data = new JSONObject(weatherJson);
        answer = data.getJSONObject("current").getDouble("temp_c");
        return answer;
    }

    public static int getHumidity(String weatherJson){
        int answer = 0;
        JSONObject data = new JSONObject(weatherJson);
        answer = data.getJSONObject("current").getInt("humidity");
        return answer;
    }
}