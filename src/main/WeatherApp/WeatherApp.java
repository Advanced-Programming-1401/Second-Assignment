import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class MyClass {
    // Copy your API-KEY here
    public final static String apiKey = "c3c9dd1e2b7749bab87141259232502";
    // TODO: Write main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String city = sc.next();

        //String json = getWeatherData(city);
        String json = "{\"location\":{\"name\":\"Tehran\",\"region\":\"Tehran\",\"country\":\"Iran\",\"lat\":35.73,\"lon\":51.33,\"tz_id\":\"Asia/Tehran\",\"localtime_epoch\":1677678014,\"localtime\":\"2023-03-01 17:10\"},\"current\":{\"last_updated_epoch\":1677677400,\"last_updated\":\"2023-03-01 17:00\",\"temp_c\":18.0,\"temp_f\":64.4,\"is_day\":1,\"condition\":{\"text\":\"Sunny\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/113.png\",\"code\":1000},\"wind_mph\":2.2,\"wind_kph\":3.6,\"wind_degree\":10,\"wind_dir\":\"N\",\"pressure_mb\":1019.0,\"pressure_in\":30.09,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":13,\"cloud\":0,\"feelslike_c\":18.0,\"feelslike_f\":64.4,\"vis_km\":10.0,\"vis_miles\":6.0,\"uv\":5.0,\"gust_mph\":3.8,\"gust_kph\":6.1}}";

        double temp_c = getTemperature(json);
        int humidity = getHumidity(json);
        double windspeed = getWindSpeed(json);
        String wind_dir = getWindDirection(json);

        System.out.println("Current Temperature is: " + temp_c + " Celsius");
        System.out.println("Current Humidity is: " + humidity);
        System.out.println("Current Wind speed is: " + windspeed + " km\\h, In direction of : " + wind_dir);

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
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
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

    // TODO: Write getTemperature function returns Celsius temperature of city by given json string
    public static double getTemperature(String weatherJson){
        JSONObject obj = new JSONObject(weatherJson);
        JSONObject current = obj.getJSONObject("current");
        double temp_c = current.getDouble("temp_c");
        return temp_c;
    }

    // TODO: Write getHumidity function returns humidity percentage of city by given json string
    public static int getHumidity(String weatherJson){
        JSONObject obj = new JSONObject(weatherJson);
        JSONObject current = obj.getJSONObject("current");
        int humidity = current.getInt("humidity");
        return humidity;
    }

    public static double getWindSpeed(String weatherJson){
        JSONObject obj = new JSONObject(weatherJson);
        JSONObject current = obj.getJSONObject("current");
        double wind_kph = current.getDouble("wind_kph");
        return wind_kph;
    }

    public static String getWindDirection(String weatherJson){
        JSONObject obj = new JSONObject(weatherJson);
        JSONObject current = obj.getJSONObject("current");
        String wind_dir = current.getString("wind_dir");
        return wind_dir;
    }

}



    On Wed, Mar 1, 2023 at 4:58 PM Saba Madadi <sabamadadi9@gmail.com> wrote:
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.Objects;

        import org.json.JSONObject;
        import javax.swing.*;   //  I Called it to use simple GUI
public class WeatherApp {
    static JFrame pic;
    public final static String apiKey = "253fe6e8f2bd47cf97745159232502";   //  <--- Copy of API_KEY
    public static void main(String[] args)  {
        //In this section I make label and Frame to use this app in file instead of in terminal--->
        pic = new JFrame("City's Weather");
        pic.setVisible(true);
        pic.setSize(1000, 100);
        pic.setLayout(new FlowLayout());
        JLabel l1 = new JLabel("Which Location That You Look For ?");
        pic.add(l1);
        JTextField Location = new JTextField(20);
        pic.add(Location);
        JButton e = new JButton("Click");
        pic.add(e);

        //This thing below says that when you click on it, it closes the page --->
        e.addActionListener((ActionEvent e1) -> {

            String s = e1.getActionCommand();

            if (s.equals("Click")) {

                pic.setVisible(false);
                // open new page --->
                pic = new JFrame("City's Weather Result");
                pic.setVisible(true);
                pic.setSize(900, 100);
                pic.setLayout(new FlowLayout());
                String city = Location.getText();

                //I make Error message for the null answer--->
                if (getWeatherData(city) == null) {

                    pic.setVisible(false);
                    JFrame frame = new JFrame("City's Weather Result");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    JOptionPane.showMessageDialog(frame ,"Sorry! The City does not Found or your Net is Weak!",
                            "ERROR", JOptionPane.ERROR_MESSAGE);

                }

                else {

                    JSONObject WeatherJson = new JSONObject(Objects.requireNonNull(getWeatherData(city)));

                    //in this part I try to Show the weather's condition( Use it by JSON) --->
                    String Humid = Integer.toString(WeatherJson.getJSONObject("current").getInt("humidity"));
                    String Celsius_Temperature = Double.toString(WeatherJson.getJSONObject("current").getDouble("temp_c"));
                    String Wind_Speed = Double.toString(WeatherJson.getJSONObject("current").getDouble("wind_kph"));
                    String Wind_Direction = WeatherJson.getJSONObject("current").getString("wind_dir");
                    System.out.println(getWeatherData(city));

                    //put the weathers condition to the page --->
                    JLabel l112 = new JLabel("Humid : ");
                    pic.add(l112);
                    JLabel l2 = new JLabel(String.valueOf(getHumidity(Humid)));
                    pic.add(l2);
                    JLabel l3 = new JLabel("%      ");
                    pic.add(l3);

                    JLabel l4 = new JLabel("Celsius Temperature : ");
                    pic.add(l4);
                    JLabel l5 = new JLabel(String.valueOf(getTemperature(Celsius_Temperature)));
                    pic.add(l5);
                    JLabel l6 = new JLabel(" C       ");
                    pic.add(l6);

                    JLabel l7 = new JLabel("Wind Speed : ");
                    pic.add(l7);
                    JLabel l8 = new JLabel(String.valueOf(getTemperature(Wind_Speed)));
                    pic.add(l8);
                    JLabel l9 = new JLabel(" m/s      ");
                    pic.add(l9);

                    JLabel l10 = new JLabel("Wind Direction : ");
                    pic.add(l10);
                    JLabel l11 = new JLabel(Wind_Direction);
                    pic.add(l11);

                }

            }

        });

    }

    /**
     * Retrieves weather data for the specified city.
     *
     * @param city the name of the city for which weather data should be retrieved
     * @return a string representation of the weather data, or null if an error occurred
     */
    public static String getWeatherData (String city){

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

        }

        // In this part if you delete the  "e.printStackTrace();" you can takeout this Error --->
        catch (Exception e) {

            return null;

        }

    }

    //By this Function we can change String to Double --->
    public static double getTemperature (String weatherJson){

        double answer = 0.0;
        answer = Double.parseDouble(weatherJson);
        return answer;

    }

    //By this Function we can change String to Int --->
    public static int getHumidity (String weatherJson){

        int answer = 0;
        answer = Integer.parseInt(weatherJson);
        return answer;

    }
}