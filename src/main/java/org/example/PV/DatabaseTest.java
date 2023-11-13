// DatabaseTest.java
package org.example.PV;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DatabaseTest {
    public static Connection getConnection() {
        try (InputStream configFileInput = DatabaseTest.class.getClassLoader()
                .getResourceAsStream("connection.properties")) {
            Properties properties = new Properties();
            if (configFileInput != null) {
                properties.load(configFileInput);
            } else {
                throw new RuntimeException("Unable to find connection.properties. Make sure it's in the correct location.");
            }

            return DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password")
            );
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Error connecting to the database.", e);
        }
    }


    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                System.out.println("Database connection successful!");
            } else {
                System.out.println("Unable to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
