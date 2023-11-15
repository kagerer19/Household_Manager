package org.example.PV;

import org.example.PV.DatabaseTest;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseSingleton {
    private static DatabaseSingleton instance;
    private Connection connection;

    DatabaseSingleton() {
        try (InputStream configFileInput = DatabaseTest.class.getClassLoader()
                .getResourceAsStream("connection.properties")) {
            Properties properties = new Properties();
            if (configFileInput != null) {
                properties.load(configFileInput);

                this.connection = DriverManager.getConnection(
                        properties.getProperty("db.url"),
                        properties.getProperty("db.user"),
                        properties.getProperty("db.password")
                );
            } else {
                throw new RuntimeException("Unable to find connection.properties. Make sure it's in the correct location.");
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Error connecting to the database.", e);
        }
    }

    public static DatabaseSingleton getInstance() {
        if (instance == null) {
            instance = new DatabaseSingleton();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
