import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.json.JSONPointer;

import java.util.Scanner;

public class WeatherApp {
    // Copy your API-KEY here
    public final static String apiKey = "c5ef8ee0c3324578ac1150710232802";
    // TODO: Write main function
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter city name : ");
        String cityName = input.nextLine();

//        System.out.print(getWeatherData(cityName));
//        double temp = getTemperature(getWeatherData(cityName));
//        int humidity  = getHumidity(getWeatherData(cityName));
//
        System.out.println("temperature in " + cityName + " is " +  getTemperature(getWeatherData(cityName)) + " °C.");
        System.out.println("humidity in " + cityName + " is " + getHumidity(getWeatherData(cityName)) + "%.");
//        System.out.println("wind speed in " + cityName + " is" + getHumidity(getWeatherData(cityName)) + "kilometers per hour.");
//        System.out.println("wind direction in " + cityName + " is" + getHumidity(getWeatherData(cityName)) + ".");
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
    public static double getTemperature(String weatherJson){
        JSONObject weather = new JSONObject(weatherJson);
        double answer = 0.0;
        answer =  weather.getJSONObject("current").getDouble("temp_c");


        return answer;
    }

    // TODO: Write getHumidity function returns humidity percentage of city by given json string
    public static int getHumidity(String weatherJson){
        int answer = 0;
        JSONObject weather  = new JSONObject(weatherJson);
        answer  = weather.getJSONObject("current").getInt("humidity");
        return answer;
    }
}
