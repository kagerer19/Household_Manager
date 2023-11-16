package org.example.PV;

import java.sql.*;

public abstract class AddressDAO implements Crudible<Address> {
    private final Connection connection;

    public AddressDAO() {
        this.connection = DatabaseSingleton.getInstance().getConnection();
    }

    // Create
    @Override
    public int createAddress(Address address) {
        String sql = "INSERT INTO Address (street_name, city, postal_code, house_number) VALUES (?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, address.getStreetName());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getPostCode());
            preparedStatement.setString(4, address.getHouseNum());

            // Execute the insert statement
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // Retrieve generated Key (ID)
                        int generatedID = generatedKeys.getInt(1);
                        System.out.println("Address created successfully with ID: " + generatedID);
                        return generatedID;
                    }
                }
            }
            // If no generated key is retrieved, throw an exception
            throw new RuntimeException("Failed to retrieve the generated ID after creating the address");
        } catch (SQLException e) {
            // Handle the SQL exception
            throw new RuntimeException("Error creating address", e);
        }
    }

    // Read operation
    public void getPersons() {
        String sql = "SELECT * FROM Household";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            System.out.println("-----------".repeat(5));
            System.out.printf("%-15s %-15s %-15s %-15s %-15s %n", "HouseholdID", "Street Name", "City", "Post Code", "House #");
            System.out.println("-----------".repeat(5));


            while (resultSet.next()) {
                int householdID = resultSet.getInt("householdID");
                String streetName = resultSet.getString("streetName");
                String city = resultSet.getString("city");
                String postCode = resultSet.getString("postCode");
                String houseNum = resultSet.getString("houseNum");

                System.out.printf("%-15d %-15s %-15s %-15s %-15s %n", householdID, streetName, city, postCode, houseNum);
                System.out.println("----------".repeat(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update operation
    public void updatePerson(int id, String newName, int newAge) {
        String sql = "UPDATE Persons SET name = ?, age = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, newAge);
            preparedStatement.setInt(3, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Person updated successfully.");
            } else {
                System.out.println("Person with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete operation
    public void deletePerson(int id) {
        String sql = "DELETE FROM Persons WHERE userID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Person deleted successfully.");
            } else {
                System.out.println("Person with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

