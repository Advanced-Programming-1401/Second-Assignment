# In God we trust

![logo][Logo.png]

# Second Assignment
## Rana Rokni 401222066

1. Introduction

In this assignment I built a terminal based program named weather app which shows weather condition of a given city by user including temperature, humidity, wind speed and direction using data given by a free weather API.


2. Design and Implementation

The app is written in Java programming language and uses Gradle as a build automation tool.

The org.json library is used to encode json data, java.io to handle input streams, IOExceptions and to read files, and java.net library to do low-level networking with DatagramPacket objects which may be sent and received over the network through a DatagramSocket object.

The main challenge was to learn how to work with json data and libraries in java. None valid city name was also a issue to be solved. 


3. Testing and Evaluation

Main class of the project is Weatherapp and it includes 7 functions listed below:
* jsonFileReader
* isCityNameValid
 * getWeatherData
 * getTemperature
 * getHumidity
 * getWindSpeed
 * getWindDirection
 
First, the city name is asked from user, the isCityNameValid function will check whether it is valid or not by the information taken from jsonFileReader( it reads and iterates from a json file including all city names of the world), if it wasn't, the user will be asked to enter the name again and again until the name entered would be valid.
After city validation check, function getWeatherData take city name as an input and connects to a URL using unique API key provided by [weather API][https://www.weatherapi.com "weather"] after registration and returns a string in a json order. The other functions will input this string and return it to a json object each, then they will find the information required and return them in a suitable type.

In jsonFileReader and getWeatherData there is methods to handle possible errors of file reading and URL connection.

4. Conclusion

The report concludes the aspests of the weather app project, which can be improved by adding GUI and reporting more detailed weather condition.  





 
