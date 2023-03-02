import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.Scanner;

public class WeatherApp {

    public final static String apiKey = "11d6d7ae006f4890994141146232602";
    // TODO: Write main function
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the city name: ");
        String city = JOptionPane.showInputDialog("Please Enter the city name: "); // getting input from user in a graphical panel
        if (getWeatherData(city) != null)
        {
            callAllFunctions(city);
        }
        else
        {
            invalidInput();
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
//            e.printStackTrace();
            return null;
        }
    }

    public static String currentLocation(String weatherJson)
    {
        JSONObject json = new JSONObject(weatherJson);
        String regin;
        regin = json.getJSONObject("location").getString("region");
        return regin;
    }

    public static String localTime(String weatherJson)
    {
        JSONObject json = new JSONObject(weatherJson);
        String local_time;
        local_time = json.getJSONObject("location").getString("localtime");
        return local_time;
    }

    public static double getTemperature(String weatherJson){
        JSONObject json = new JSONObject(weatherJson);
        double answer = 0.0;
        answer = json.getJSONObject("current").getDouble("temp_c");
        return answer;
    }

    public static double getFeelsLikeTem (String weatherJson)
    {
        JSONObject json = new JSONObject(weatherJson);
        double feelsLikeTem = 0.0;
        feelsLikeTem = json.getJSONObject("current").getDouble("feelslike_c");
        return feelsLikeTem;
    }

    public static int getHumidity(String weatherJson){
        JSONObject json = new JSONObject(weatherJson);
        int answer = 0;
        answer = json.getJSONObject("current").getInt("humidity");
        return answer;
    }

    public static double getWindSpeed(String weatherJson)
    {
        JSONObject json = new JSONObject(weatherJson);
        double wind_speed = 0.0;
        wind_speed = json.getJSONObject("current").getDouble("wind_kph");
        return wind_speed;
    }

    public static String getWindDirection(String weatherJson)
    {
        JSONObject json = new JSONObject(weatherJson);
        String wind_dir;
        wind_dir = json.getJSONObject("current").getString("wind_dir");
        return wind_dir;
    }

    public static void currentLocationGUI(String currentLocation, JFrame frame)
    {
        JLabel label = new JLabel(); // creates a label
        Border border = BorderFactory.createLineBorder(Color.CYAN, 5); // creates a borderline that surrounding the frame with pink color and thickness of 5
        label.setText("Current Location: " + " = " + currentLocation); // sets the text inside the corresponding panel
        label.setFont(new Font("MV Boil", Font.PLAIN, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBorder(border);// sets the borderline that we created in few lines back

        JPanel panel = new JPanel(); // creates a panel
        panel.setBackground(Color.red); // sets red for the panel's background
        panel.setBounds(0,0,600,100);// sets the size(width & height) of the panel and where should this panel starts(x & y)
        panel.setLayout(new BorderLayout());

        panel.add(label);
        frame.add(panel);
    }

    public static void localTimeGUI(String localTime, JFrame frame)
    {
        JLabel label = new JLabel(); // creates a label
        Border border = BorderFactory.createLineBorder(Color.CYAN, 5); // creates a borderline that surrounding the frame with pink color and thickness of 5
        label.setText("Local Time: " + " = " + localTime); // sets the text inside the corresponding panel
        label.setFont(new Font("MV Boil", Font.PLAIN, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBorder(border);// sets the borderline that we created in few lines back

        JPanel panel = new JPanel(); // creates a panel
        panel.setBackground(Color.blue); //sets blue for the panel's background
        panel.setBounds(0,100,600,100);// sets the size(width & height) of the panel and where should this panel starts(x & y)
        panel.setLayout(new BorderLayout());

        panel.add(label);
        frame.add(panel);
    }

    public static void temperatureGUI(double temperature, JFrame frame)
    {
        JLabel label = new JLabel();// creates a label
        Border border = BorderFactory.createLineBorder(Color.CYAN, 5); // creates a borderline that surrounding the frame with pink color and thickness of 5
        label.setText("Temperature(Celsius): " + " = " + temperature); // sets the text inside the corresponding panel
        label.setFont(new Font("MV Boil", Font.PLAIN, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBorder(border); // sets the borderline that we created in few lines back

        JPanel panel = new JPanel();// creates a panel
        panel.setBackground(Color.green);//sets green for the panel's background
        panel.setBounds(0,200,600,100);// sets the size(width & height) of the panel and where should this panel starts(x & y)
        panel.setLayout(new BorderLayout());

        panel.add(label);
        frame.add(panel);
    }

    public static void feelsLikeGUI(double feelsLike, JFrame frame)
    {
        JLabel label = new JLabel();// creates a label
        Border border = BorderFactory.createLineBorder(Color.CYAN, 5); // creates a borderline that surrounding the frame with pink color and thickness of 5
        label.setText("Feels Like(Celsius): " + " = " + feelsLike); // sets the text inside the corresponding panel
        label.setFont(new Font("MV Boil", Font.PLAIN, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBorder(border);// sets the borderline that we created in few lines back

        JPanel panel = new JPanel();// creates a panel
        panel.setBackground(Color.pink);//sets pink for the panel's background
        panel.setBounds(0,300,600,100);// sets the size(width & height) of the panel and where should this panel starts(x & y)
        panel.setLayout(new BorderLayout());

        panel.add(label);
        frame.add(panel);
    }

    public static  void humidityGUI(int humidity, JFrame frame)
    {
        JLabel label = new JLabel();// creates a label
        Border border = BorderFactory.createLineBorder(Color.CYAN, 5); // creates a borderline that surrounding the frame with pink color and thickness of 5
        label.setText("Humidity: " + " = " + humidity); // sets the text inside the corresponding panel
        label.setFont(new Font("MV Boil", Font.PLAIN, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBorder(border);// sets the borderline that we created in few lines back

        JPanel panel = new JPanel();// creates a panel
        panel.setBackground(Color.orange);//sets orange for the panel's background
        panel.setBounds(0,400,600,100);// sets the size(width & height) of the panel and where should this panel starts(x & y)
        panel.setLayout(new BorderLayout());

        panel.add(label);
        frame.add(panel);
    }

    public static void windSpeedGUI(double windSpeed, JFrame frame)
    {
        JLabel label = new JLabel();// creates a label
        Border border = BorderFactory.createLineBorder(Color.CYAN, 5); // creates a borderline that surrounding the frame with pink color and thickness of 5
        label.setText("Wind Speed(kph): " + " = " + windSpeed); // sets the text inside the corresponding panel
        label.setFont(new Font("MV Boil", Font.PLAIN, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBorder(border);// sets the borderline that we created in few lines back

        JPanel panel = new JPanel();// creates a panel
        panel.setBackground(Color.magenta);//sets magenta for the panel's background
        panel.setBounds(0,500,600,100);// sets the size(width & height) of the panel and where should this panel starts(x & y)
        panel.setLayout(new BorderLayout());

        panel.add(label);
        frame.add(panel);
    }

    public static void windDirectionGUI(String windDirection, JFrame frame)
    {
        JLabel label = new JLabel(); // creates a label
        Border border = BorderFactory.createLineBorder(Color.CYAN, 5); // creates a borderline that surrounding the frame with pink color and thickness of 5
        label.setText("Wind Direction: " + " = " + windDirection); // sets the text inside the corresponding panel
        label.setFont(new Font("MV Boil", Font.PLAIN, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBorder(border);// sets the borderline that we created in few lines back

        JPanel panel = new JPanel();// creates a panel
        panel.setBackground(new Color(123,50,250));//sets a color whit the corresponding bound for the panel's background
        panel.setBounds(0,600,600,100);// sets the size(width & height) of the panel and where should this panel starts(x & y)
        panel.setLayout(new BorderLayout());

        panel.add(label);
        frame.add(panel);
    }

    public static void callAllFunctions(String city)
    {
        JFrame frame = new JFrame(); // creates a frame
        frame.setTitle("Weather App"); // sets the "Weather App" title for the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // if the user wants to close the app it will be close by clicking on 'x' icon
        frame.setLayout(null); // sets layout manager
        frame.setSize(600,740); // sets x-dimension and y-dimension for the frame
        ImageIcon icon = new ImageIcon("3845731.png"); // gets a picture from the weatherAPI folder and save it in icon
        frame.setIconImage(icon.getImage()); // sets the icon as app icon
        frame.setResizable(false); // it won't allow to the user to change the size of the frame

        // functions that extract the information from the json string
        String weatherData      = getWeatherData(city); // gets the json string corresponding to the city
        String current_location = currentLocation(weatherData);
        String local_time       = localTime(weatherData);
        double Temperature      = getTemperature(weatherData);
        double feels_like       = getFeelsLikeTem(weatherData);
        int Humidity            = getHumidity(weatherData);
        double wind_speed       = getWindSpeed(weatherData);
        String wind_direction   = getWindDirection(weatherData);

        currentLocationGUI  (current_location, frame);
        localTimeGUI        (local_time, frame);
        temperatureGUI      (Temperature, frame);
        feelsLikeGUI        (feels_like, frame);
        humidityGUI         (Humidity, frame);
        windSpeedGUI        (wind_speed, frame);
        windDirectionGUI    (wind_direction, frame);
        frame.setVisible(true); // makes frame visible
    }

    public static void invalidInput()
    {
        Border border = BorderFactory.createLineBorder(Color.cyan,5);

        JLabel label = new JLabel(); // creates a label
        label.setText("The location that you have entered is INVALID!"); // sets a label inside the frame.
        label.setHorizontalAlignment(JLabel.CENTER); // sets the label in the center of the frame
        label.setForeground(Color.BLACK);//sets a color for the text label
        label.setFont(new Font("MV Boil", Font.PLAIN, 20));// sets a font for the text label
        label.setBackground(Color.PINK);// sets background color
        label.setOpaque(true); // display background color
        label.setBorder(border);

        JFrame frame = new JFrame(); //creates a frame
        frame.setTitle("Weather App"); //sets title for the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // if we press the 'x' icon on the top right the frame will be close.
        frame.setSize(600,500); // sets the x-dimension and y-dimension for the frame
        frame.setResizable(false); // the size of the frame will not be changeable
        frame.setVisible(true); // makes frame be visible
        frame.add(label); // add the label to the frame
        ImageIcon image = new ImageIcon("3845731.png"); // creates an Image Icon
        frame.setIconImage(image.getImage()); // sets the Image Icon
    }
}