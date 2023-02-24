import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import javax.swing.*;
import java.util.Objects;
import java.util.Scanner;

public class WeatherApp{
    // Copy your API-KEY here
    public final static String apiKey = "4170999efb19400b9e921713232302";
    public static void main(String[] args)
    {
        //swing();

        Scanner scanner = new Scanner(System.in);
        //String city  = scanner.nextLine();
        String city = JOptionPane.showInputDialog("Enter the city name");
        String JsonWetter;
        while(true)
        {
            JsonWetter= getWeatherData(city);
            if(JsonWetter==null)
            {
                //System.out.print("some thing went wrong. The city isn't found or the Internet isn't connected.please check them.\nif you want to exit enter \"exit\" or enter a city name to continue.\n");
                JOptionPane.showMessageDialog(null,"some thing went wrong. The city isn't found or the Internet isn't connected.please check them.","ERROR",JOptionPane.ERROR_MESSAGE);
                city = JOptionPane.showInputDialog("Enter the city name");
                if(Objects.equals(city, null))
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
        city=getSity(jsonObject);
        output(city, temp , humy ,windDegree ,windSpeed , windDirect ,feelTemp);
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
            //e.printStackTrace();
            return null;
        }
    }
    public static String getSity(JSONObject jsonObject)
    {
        return jsonObject.getJSONObject("location").getString("name")+"."+jsonObject.getJSONObject("location").getString("region")+"."+jsonObject.getJSONObject("location").getString("country");
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
     * @param str string do you want to print
     */
    public static String setw(String str, int size)
    {
        if(str.length()<=size)
        {
            while(str.length()<=size)
            {
                str+=" ";
            }
        }
        return str;
    }

    public static String setw(int input , int size)
    {
        String str = Integer.toString(input);
        if(str.length()<=size)
        {
            while(str.length()<=size)
            {
                str+=" ";
            }
        }
        return str;
    }
    public static String setw(double input, int size)
    {
        String str = Double.toString(input);
        if(str.length()<=size)
        {
            while(str.length()<=size)
            {
                str+=" ";
            }
        }
        return str;
    }

    public static void output(String city, double temp , int humy , int windDegree , double windSpeed , String windDirect , double feelTemp)
    {
        String out;
        //out=setw("city", city.length()+3)+setw("temperature", 12 )+setw("humidity", 10)+setw("wind degree", 12)+setw("wind speed(khp)",15)+setw("wind direct" , 12)+setw("fesls-like temp", 15);
        //out+="\n"+setw(city,city.length()+3)+setw(temp,12)+setw(humy,10)+setw(windDegree,12)+setw(windSpeed+"(khp)" , 15)+setw(windDirect,12)+setw(feelTemp,15);
        out="city : "+city;
        out+="\ntemperature : "+ temp;
        out+="\nhumidity : "+ humy;
        out+="\nwind degree : " + windDegree;
        out+="\nwind Speed (kph) : " + windSpeed ;
        out+="\nwind direction : " + windDirect;
        out+="\nfeels-like temperature : " + feelTemp;
        JOptionPane.showMessageDialog(null,out);
        //System.out.print(out);
    }

}