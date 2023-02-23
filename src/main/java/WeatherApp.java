import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class WeatherApp {
    // Copy your API-KEY here
    public final static String apiKey = "4170999efb19400b9e921713232302";
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String city  = scanner.nextLine();
        String JsonWetter;
        while(true)
        {
            JsonWetter= getWeatherData(city);
            if(JsonWetter==null)
            {
                System.out.print("some thing went wrong. could you enter another city name.\nif do you want ot exit enter \"exit\"\n");
                city=scanner.nextLine();
                if(Objects.equals(city, "exit"))
                    return;
            }else
                break;
        }
        //JsonWetter="{\"location\":{\"name\":\"Tehran\",\"region\":\"Tehran\",\"country\":\"Iran\",\"lat\":35.73,\"lon\":51.33,\"tz_id\":\"Asia/Tehran\",\"localtime_epoch\":1677138746,\"localtime\":\"2023-02-23 11:22\"},\"current\":{\"last_updated_epoch\":1677138300,\"last_updated\":\"2023-02-23 11:15\",\"temp_c\":11.0,\"temp_f\":51.8,\"is_day\":1,\"condition\":{\"text\":\"Sunny\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/113.png\",\"code\":1000},\"wind_mph\":13.6,\"wind_kph\":22.0,\"wind_degree\":300,\"wind_dir\":\"WNW\",\"pressure_mb\":1014.0,\"pressure_in\":29.94,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":28,\"cloud\":0,\"feelslike_c\":9.8,\"feelslike_f\":49.6,\"vis_km\":10.0,\"vis_miles\":6.0,\"uv\":4.0,\"gust_mph\":7.4,\"gust_kph\":11.9}}";
        double temp = getTemperature(JsonWetter);
        int humy = getHumidity(JsonWetter);
        int windDegree = getWindDegree(JsonWetter);
        double windSpeed = getWindSpeed(JsonWetter);
        String windDirect = getWindDirect(JsonWetter);
        double feelTemp = getFeelTemp(JsonWetter);
        setw("city");setw("temperature");setw("humidity");setw("wind degree");setw("wind speed(khp)");setw("wind direct");setw("fesls-like temp");
        System.out.println("");setw(city);setw(temp);setw(humy);setw(windDegree);setw(windSpeed+"(khp)");setw(windDirect);setw(feelTemp);
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
    /**
     * get temperature for a specific city.
     *
     * @param weatherJson the json string that you get from your api request.
     * @return a double that show the city's temperature.
     */
    public static double getTemperature(String weatherJson){
        JSONObject jsonObject = new JSONObject(weatherJson);
        return jsonObject.getJSONObject("current").getDouble("temp_c");
    }
    /**
     * get wind status for a specific city.
     *
     * @param weatherJson the json string that you get from your api request.
     * @return a list that show the city's wind status.
     */
    public static double getFeelTemp(String weatherJson){
        JSONObject jsonObject = new JSONObject(weatherJson);
        return jsonObject.getJSONObject("current").getDouble("temp_c");
    }
    public static int getWindDegree(String weatherJson){
        JSONObject jsonObject = new JSONObject(weatherJson);
        return jsonObject.getJSONObject("current").getInt("wind_degree");
    }
    public static Double getWindSpeed(String weatherJson){
        JSONObject jsonObject = new JSONObject(weatherJson);
        return jsonObject.getJSONObject("current").getDouble("wind_kph");
    }
    public static String getWindDirect(String weatherJson){
        JSONObject jsonObject = new JSONObject(weatherJson);
        return jsonObject.getJSONObject("current").getString("wind_dir");
    }
    /**
     * get humidity for a specific city.
     *
     * @param weatherJson the json string that you get from your api request.
     * @return an int that show the city's humidity.
     */
    public static int getHumidity(String weatherJson){
        JSONObject jsonObject = new JSONObject(weatherJson);
        return jsonObject.getJSONObject("current").getInt("humidity");
    }
    public static void setw(String str)
    {
        if(str.length()<=15)
        {
            while(str.length()<=15)
            {
                str+=" ";
            }
        }
        System.out.print(str);
    }

    public static void setw(int input)
    {
        String str = Integer.toString(input);
        if(str.length()<=15)
        {
            while(str.length()<=15)
            {
                str+=" ";
            }
        }
        System.out.print(str);
    }
    public static void setw(double input)
    {
        String str = Double.toString(input);
        if(str.length()<=15)
        {
            while(str.length()<=15)
            {
                str+=" ";
            }
        }
        System.out.print(str);
    }
    public static void output()
    {

    }
}
/*
{
    "location":
        {
        "name": "London",
        "region": "City of London, Greater London",
        "country": "United Kingdom",
        "lat": 51.52,
        "lon": -0.11,
        "tz_id": "Europe/London",
        "localtime_epoch": 1677136436,
        "localtime": "2023-02-23 7:13"
    },
    "current":{
        "last_updated_epoch": 1677135600,
        "last_updated": "2023-02-23 07:00",
        "temp_c": 6.0,
        "temp_f": 42.8,
        "is_day": 1,
        "condition": {
            "text": "Overcast",
            "icon": "//cdn.weatherapi.com/weather/64x64/day/122.png",
            "code": 1009
        },
        "wind_mph": 6.9,
        "wind_kph": 11.2,
        "wind_degree": 10,
        "wind_dir": "N",
        "pressure_mb": 1016.0,
        "pressure_in": 30.0,
        "precip_mm": 0.1,
        "precip_in": 0.0,
        "humidity": 93,
        "cloud": 75,
        "feelslike_c": 2.9,
        "feelslike_f": 37.2,
        "vis_km": 10.0,
        "vis_miles": 6.0,
        "uv": 2.0,
        "gust_mph": 13.9,
        "gust_kph": 22.3,
        "air_quality": {
            "co": 233.6999969482422,
            "no2": 10.0,
            "o3": 52.900001525878906,
            "so2": 3.799999952316284,
            "pm2_5": 1.2000000476837158,
            "pm10": 1.600000023841858,
            "us-epa-index": 1,
            "gb-defra-index": 1
        }
    }
}
 */