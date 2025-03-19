package service;

import model.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.stream.Stream;

public class UserDataProvider {

    /**Links functions to the 'database'*/
    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = UserDataProvider.class.getClassLoader().getResourceAsStream("users.properties")) {
            if (input == null) {
                throw new RuntimeException("Cannot find users.properties file!");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties file", e);
        }
        return properties;
    }

    /**Getting unaccepted user's data*/
    public static Stream<User> getWrongUsers() {
        Properties properties = loadProperties();
        return Stream.of(
                new User(properties.getProperty("wrongUser1.username"), properties.getProperty("wrongUser1.password")),
                new User(properties.getProperty("wrongUser2.username"), properties.getProperty("wrongUser2.password")),
                new User(properties.getProperty("wrongUser3.username"), properties.getProperty("wrongUser3.password"))
        );
    }

    /**Getting accepted user's data*/
    public static Stream<User> getAcceptedUsers() {
        Properties properties = loadProperties();
        return Stream.of(
                new User(properties.getProperty("acceptedUser1.username"), properties.getProperty("acceptedUser1.password")),
                new User(properties.getProperty("acceptedUser2.username"), properties.getProperty("acceptedUser2.password")),
                new User(properties.getProperty("acceptedUser3.username"), properties.getProperty("acceptedUser3.password"))
        );
    }
}
