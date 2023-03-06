import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;



public class WeatherApp {
    // Copy your API-KEY here
    public final static String apiKey = "b36dc40757fb415fa35192654232602";
    // TODO: Write main function
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String city = scanner.next();
        System.out.println("Temperature in C is : " + getTemperature(getWeatherData(city)) + "degree");
        System.out.println("Humidity is : "+ getHumidity(getWeatherData(city)));
        System.out.println("Wind speeed in kph is :" + getWind(getWeatherData(city)));
        System.out.println("Wind direction is : " + getWindDirection(getWeatherData(city)));
        if (getWeatherData(city) == null) {
            System.out.println("wrong name!!");
        }
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
        JSONObject tem = new JSONObject(weatherJson);
        answer = tem.getJSONObject("current").getDouble("temp_c");
        return answer;
    }

    public static int getHumidity(String weatherJson){
        int answer = 0;
        JSONObject humidity = new JSONObject(weatherJson);
        answer = humidity.getJSONObject("current").getInt("humidity");
        return answer;
    }
    public static double getWind(String weatherJson){
        double answer = 0.0;
        JSONObject wind = new JSONObject(weatherJson);
        answer = wind.getJSONObject("current").getDouble("wind_kph");
        return answer;
    }
    public static String getWindDirection(String weatherJson){
        String answer = "";
        JSONObject windDir = new JSONObject(weatherJson);
        answer = windDir.getJSONObject("current").getString("wind_dir");
        return answer;
    }

}
