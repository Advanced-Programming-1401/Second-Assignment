import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class WeatherApp {
    // Copy your API-KEY here
    public final static String apiKey = "396aacba5f4a49119af165854232502";
    // TODO: Write main function
    public static void main(String[] args) {
        Scanner Scan = new Scanner(System.in);

        System.out.println("Enter The City's Name: ");
        String city=Scan.nextLine();

        String jsonString=getWeatherData(city);

        System.out.println("temperature of " + city + " is " + getTemperature(jsonString));
        System.out.println("humidity of " + city + " is " + getHumidity(jsonString));
        System.out.println("wind speed of " + city + " is " + getWindV(jsonString));
        System.out.println("wind direction of " + city + " is " + getWindDir(jsonString));
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
        double answer = 0.0;

        JSONObject jsonObj = new JSONObject(weatherJson.toString());
        answer=jsonObj.getJSONObject("current").getDouble("temp_c");

        return answer;
    }

    // TODO: Write getHumidity function returns humidity percentage of city by given json string
    public static int getHumidity(String weatherJson){
        int answer = 0;

        JSONObject jsonObj = new JSONObject(weatherJson.toString());
        answer=jsonObj.getJSONObject("current").getInt("humidity");

        return answer;
    }

    public static String getWindDir(String weatherJson){
        String answer;

        JSONObject jsonObj = new JSONObject(weatherJson.toString());
        answer=jsonObj.getJSONObject("current").getString("wind_dir");

        return answer;
    }

    public static double getWindV(String weatherJson){
        double answer = 0.0;

        JSONObject jsonObj = new JSONObject(weatherJson.toString());
        answer=jsonObj.getJSONObject("current").getDouble("wind_mph");

        return answer;
    }

}



