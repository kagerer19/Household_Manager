package org.example.PV;

import java.sql.*;

public class PersonDAO {

    private final Connection connection;

    public PersonDAO() {
        // Assuming DatabaseSingleton is correctly configured and working
        this.connection = DatabaseSingleton.getInstance().getConnection();
    }

    // Create
    public void Create(int userID, String firstName, String lastName, int householdID) {
        String sql = "INSERT INTO Persons (userID, firstName, lastName, DOB, gender, householdID) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setNull(4, Types.DATE);
            preparedStatement.setNull(5, Types.VARCHAR);
            preparedStatement.setNull(6, Types.INTEGER);
            preparedStatement.executeUpdate();
            System.out.println("Added to the database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Create(int householdID, String streetName, String city, String postCode, String houseNum) {
        String sql = "INSERT INTO Household (householdID, streetName, city, postCode, houseNum) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, householdID);
            preparedStatement.setString(2, streetName);
            preparedStatement.setString(3, city);
            preparedStatement.setString(4, postCode);
            preparedStatement.setString(5, houseNum);
            preparedStatement.executeUpdate();
            System.out.println("Added to the database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Create(Person person, Household household) {
        String sql = "INSERT INTO Household (householdID, streetName, city, postCode, houseNum) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, person.getID());
            preparedStatement.setString(2, household.getStreetName());
            preparedStatement.setString(3, household.getCity());
            preparedStatement.setString(4, household.getPostCode());
            preparedStatement.setString(5, household.getHouseNum());
            preparedStatement.executeUpdate();
            System.out.println("Added to the database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
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

    public static void main(String[] args) {
        PersonDAO personDAO = new PersonDAO();

        // String street, String town, String postalCode, String houseNumber
        // Test CRUD operations
        // personDAO.Create(1001, "Strand ST", "Cape Town", "40205", "23B");
        personDAO.getPersons();
        // Disconnect from the database
        DatabaseSingleton.getInstance().disconnect();
    }
}

