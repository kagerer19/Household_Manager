package org.example.PV;

class Person {
    public Person() {
    }

    enum Gender {
        MALE, FEMALE, OTHER
    }

    private int userID;
    private String firstName;
    private String lastName;
    private String DOB;
    private Gender gender;
    private Household household;

    //Constructors
    Person(int ID, String name, String surname) {
        this.userID = ID;
        this.firstName = name;
        this.lastName = surname;
    }

    Person(int ID, String name, String surname, String birthday, Gender gender) {
        this.userID = ID;
        this.firstName = name;
        this.lastName = surname;
        this.DOB = birthday;
        this.gender = gender;
    }

    Person(int ID, String name, String surname, String birthday, Gender gender, Household household1) {
        this.userID = ID;
        this.firstName = name;
        this.lastName = surname;
        this.DOB = birthday;
        this.gender = gender;
        setAddress(household1);
    }

    //Getters
    public int getID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDOB() {
        return DOB;
    }

    public Gender getGender() {
        return gender;
    }

    public void setAddress(Household household1) {
        this.household = household1;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\n-Person's details-\n");
        result.append("User-ID: ").append(getID()).append("\n");
        result.append("Name: ").append(getFirstName()).append("\n");
        result.append("Last Name: ").append(getLastName()).append("\n");
        result.append("DOB: ").append(getDOB()).append("\n");
        result.append("Gender: ").append(getGender()).append("\n");

        if (household != null) {
            result.append("\n-Address-\n");
            result.append("User-ID: ").append(getID()).append("\n");
            result.append("Street: ").append(household.getStreetName()).append("\n");
            result.append("City: ").append(household.getCity()).append("\n");
            result.append("Post Code: ").append(household.getPostCode()).append("\n");
            result.append("House Number: ").append(household.getHouseNum()).append("\n");
        } else {
            result.append("\nNo Address Information\n");
        }

        return result.toString();
    }

}
