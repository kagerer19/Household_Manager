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
    public static void main(String[] args) {
        DatabaseSingleton databaseSingleton = DatabaseSingleton.getInstance();

        if (databaseSingleton.isConnected()) {
            System.out.println("Connected to the database successfully.");
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}