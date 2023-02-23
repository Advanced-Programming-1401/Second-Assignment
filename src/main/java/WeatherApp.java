import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

import java.util.Objects;
import java.util.Scanner;
public class WeatherApp{
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
                System.out.print("some thing went wrong. The city isn't found or the Internet isn't connected.please check them.\nif you want to exit enter \"exit\" or enter a city name to continue.\n");
                city=scanner.nextLine();
                if(Objects.equals(city, "exit"))
                    return;
            }else
                break;
        }
        JSONObject jsonObject = new JSONObject(JsonWetter);
        double temp = getTemperature(jsonObject);
        int humy = getHumidity(jsonObject);
        int windDegree = getWindDegree(jsonObject);
        double windSpeed = getWindSpeed(jsonObject);
        String windDirect = getWindDirect(jsonObject);
        double feelTemp = getFeelTemp(jsonObject);
        outputTerminal(city, temp , humy ,windDegree ,windSpeed , windDirect ,feelTemp);
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
            //e.printStackTrace();
            return null;
        }
    }
    /**
     * get temperature for a specific city.
     *
     * @param jsonObject the json obj that you get from your api request.
     * @return a double that show the city's temperature.
     */
    public static double getTemperature(JSONObject jsonObject){
        return jsonObject.getJSONObject("current").getDouble("temp_c");
    }
    /**
     * get temp with wind affect for a specific city.
     *
     * @param jsonObject the json obj that you get from your api request.
     * @return a double that show the city's wind status.
     */
    public static double getFeelTemp(JSONObject jsonObject){
        return jsonObject.getJSONObject("current").getDouble("temp_c");
    }
    /**
     * get wind degree for a specific city.
     *
     * @param jsonObject the json obj that you get from your api request.
     * @return the city's wind status.
     */
    public static int getWindDegree(JSONObject jsonObject){
        return jsonObject.getJSONObject("current").getInt("wind_degree");
    }
    /**
     * get wind speed for a specific city.
     *
     * @param jsonObject the json obj that you get from your api request.
     * @return the city's wind status.
     */
    public static Double getWindSpeed(JSONObject jsonObject){
        return jsonObject.getJSONObject("current").getDouble("wind_kph");
    }
    /**
     * get wind direct for a specific city.
     *
     * @param jsonObject the json obj that you get from your api request.
     * @return the city's wind status.
     */
    public static String getWindDirect(JSONObject jsonObject){
        return jsonObject.getJSONObject("current").getString("wind_dir");
    }
    /**
     * get humidity for a specific city.
     *
     * @param jsonObject the json obj that you get from your api request.
     * @return an int that show the city's humidity.
     */
    public static int getHumidity(JSONObject jsonObject){
        return jsonObject.getJSONObject("current").getInt("humidity");
    }

    /**
     * print input with 15 character width.
     * @param str
     */
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

    public static void outputTerminal(String city, double temp , int humy , int windDegree , double windSpeed , String windDirect , double feelTemp)
    {
        setw("city");setw("temperature");setw("humidity");setw("wind degree");setw("wind speed(khp)");setw("wind direct");setw("fesls-like temp");
        System.out.println("");setw(city);setw(temp);setw(humy);setw(windDegree);setw(windSpeed+"(khp)");setw(windDirect);setw(feelTemp);
    }
}