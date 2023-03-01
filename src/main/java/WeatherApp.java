import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Scanner;

public class WeatherApp {
    // Copy your API-KEY here
    public final static String apiKey = "1e99e23b3ace45ccac532103232602";
    // TODO: Write main function
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the city");
        String cityName = scanner.next();

        Object obj = null;
        try {
            obj = new JSONParser().parse(new FileReader("Second-Assignment/Json-Files/Cities.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String allCityNames = obj.toString();

        while (!isCityNameValid(allCityNames, cityName)){
            System.out.println("City name entered is not valid, try again");
            cityName = scanner.next();
        }


        String weatherJson = getWeatherData(cityName);
        double temperature = getTemperature(weatherJson);
        int humidity = getHumidity(weatherJson);
        double windSpeed = getWindSpeed(weatherJson);
        String windDirection = getWindDirection(weatherJson);

        System.out.println("Temperature in " + cityName + " is : " + temperature + " celsius degree");
        System.out.println("Humidity in " + cityName + " is : " + humidity + "%");
        System.out.println("Wind speed in " + cityName + " is : " + windSpeed +" kilometer per hour");
        System.out.println("Wind direction in " + cityName + " is : " + windDirection);
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
        } catch (IOException e) {
            e.toString();
            return null;
        }
    }

    public static boolean isCityNameValid(String allCityNames, String cityName){
        JSONArray jsonArray = new JSONArray(allCityNames);

        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject objects = jsonArray.getJSONObject(i);
            String name = objects.getString("name");
            if(name.equals(cityName)){
                return true;
            }
        }
        return false;
    }
    // TODO: Write getTemperature function returns celsius temperature of city by given json string
    public static double getTemperature(String weatherJson){
        double answer = 0.0;
        JSONObject json = new JSONObject(weatherJson);
        String temperature = json.getJSONObject("current").get("temp_c").toString();
        answer = Double.parseDouble(temperature);


        return answer;
    }

    // TODO: Write getHumidity function returns humidity percentage of city by given json string
    public static int getHumidity(String weatherJson){
        int answer = 0;
        JSONObject json = new JSONObject(weatherJson);
        String humidity = json.getJSONObject("current").get("humidity").toString();
        answer = Integer.parseInt(humidity);

        return answer;
    }

    public static double getWindSpeed(String weatherJson){
        double answer = 0.0;
        JSONObject json = new JSONObject(weatherJson);
        String wind_speed = json.getJSONObject("current").get("wind_kph").toString();
        answer = Double.parseDouble(wind_speed);

        return  answer;
    }

    public static String getWindDirection(String weatherJson){
        String answer = "";
        JSONObject json = new JSONObject(weatherJson);
        String direction = json.getJSONObject("current").getString("wind_dir");

        answer = direction;
        switch (direction){
            case "N":
                answer = "north";
                break;
            case "NNE":
                answer = "north-northeast";
                break;
            case "NE":
                answer = "northeast";
                break;
            case "ENE":
                answer = "east-northeast";
                break;
            case "E":
                answer = "east";
                break;
            case "ESE":
                answer = "east-southeast";
                break;
            case "SE":
                answer = "southeast";
                break;
            case "SSE":
                answer = "south-southeast";
                break;
            case "S":
                answer = "south";
                break;
            case "SSW":
                answer = "south-southwest";
                break;
            case "SW":
                answer = "southwest";
                break;
            case "WSW":
                answer = "west-southwest";
                break;
            case "W":
                answer = "west";
                break;
            case "WNW":
                answer = "west-northwest";
                break;
            case "NW":
                answer = "northwest";
                break;
            case "NNW":
                answer = "north-northwest";
                break;
        }

        return answer;
    }
}