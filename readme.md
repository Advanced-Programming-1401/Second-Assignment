# Weather App Assignment

## Introduction
In this assignment, you will build a simple Java application that connects to a weather API and retrieves information about the current weather conditions of a given city.

## Objectives
- Understand how to make HTTP requests to retrieve data from APIs
- Learn how to use the org.json library by reading its documention
- Parse and process JSON data in Java
- Use Git for version control and collaborate on a codebase
- Use Maven or Gradle as a package manager

## Requirements
- Java 8 or later
- Git
- Maven or Gradle

## Tasks
1. Get an API key from https://www.weatherapi.com/. You need to sign up and verify your account.
2. Fork this repository and clone the fork to your local machine
3. Create a new Git branch and switch to it using the checkout command
4. Add the org.json package to Gradle. You must include the necessary code in the dependencies section of the `build.gradle` file to include the JSON library. Be sure to check https://www.mvnrepository.com/ for more information.

5. Write a Java class `WeatherApp` that does the following:
    - Prompts the user for a city name
    - Parses the JSON response to extract the temperature and humidity
    - Prints the temperature and humidity to the console

6. Commit your changes and push them to your own fork on Github

## Notes
1. This project uses Gradle by default, but you are allowed to create a new project and use Maven as your package manager instead.
2. You can learn more about the Weather API by reading its official documentation at https://www.weatherapi.com/docs/. You can also experiment with the API by visiting the API Explorer tab in the website.

## Evaluation
- Your code should compile and run without any errors
- Your code should correctly retrieve and display the temperature and humidity for a given city
- Your code should be well-organized, readable, properly commented and follows clean code principles
- You should use Git for version control and include meaningful commit messages

## Bonus Objectives
1. Extend the WeatherApp class to also display the current wind speed and direction
2. Add error handling to handle cases where the API returns an error or the city name is not found
3. Implement a simple GUI (Graphical User Interface) for your project. This GUI should be able to display the city name and its temperature and humidity as text. There are several GUI libraries available for Java, But we would recommend using either Swing or JavaFX:

    - Swing is an older UI toolkit that has been part of the Java Standard Edition (Java SE) since its inception. Swing is still widely used and remains a popular choice for building desktop applications, especially for applications that require a more lightweight UI toolkit.
    - JavaFX, which is a more recent addition to the Java platform, is considered more powerful and provides more features and capabilities for building modern, visually rich desktop applications.

## Submission
- Push your code to your fork on Github

The deadline for submitting your code is XX/XX/2023. Any commits made after this date will not affect your score.

Good luck and happy coding!
