package org.example.PV;

import java.util.Random;
import java.util.Scanner;

import static org.example.PV.Validator.NUM_REGEX;

public class Main {
    public static void main(String[] args) {
        //Used for generation of User IDs
        Random rand = new Random();

        //Offices available
        PV pvCodersBay = new PV();
        PV pvLinz = new PV();
        PV pvVienna = new PV();

        Scanner sc = new Scanner(System.in);

        boolean shouldExit = false;


        //Choose office you would like to administrate for
        System.out.println("Which office would you like to create for? ");
        System.out.println("1. Coders Bay");
        System.out.println("2. Linz");
        System.out.println("3. Vienna");
        int managementOffice = sc.nextInt();


        while (!shouldExit) {
            System.out.println("Choose an option:");
            System.out.println("1. Create user with first and last name");
            System.out.println("2. Create user with first and last name, DOB, and gender");
            System.out.println("3. Create user with all details (Address included)");
            System.out.println("4. Print user list");
            System.out.println("5. Search user list");
            System.out.println("6. Delete User");
            System.out.println("7. Exit");

            int userOption = sc.nextInt();

            switch (userOption) {
                case 1 -> {
                    String firstName, lastName;
                    Validator validation = new Validator();

                    try {
                        System.out.println("Enter first name: ");
                        firstName = sc.next();
                        System.out.println("Enter last name: ");
                        lastName = sc.next();
                        validation.validateInput(firstName, lastName);

                        if (managementOffice == 1) {
                            pvCodersBay.createNewUser(rand.nextInt(1000, 5000), firstName, lastName); // ID is not specified
                        } else if (managementOffice == 2) {
                            pvLinz.createNewUser(rand.nextInt(1000, 5000), firstName, lastName);
                        } else {
                            pvVienna.createNewUser(rand.nextInt(1000, 5000), firstName, lastName);
                        }

                    } catch (InvalidPersonDetailsException e) {
                        e.printStackTrace();
                    }
                }

                case 2 -> {
                    String firstName, lastName, dob;
                    Validator validation = new Validator();

                    try {
                        System.out.println("Enter first name: ");
                        //Consume newline
                        sc.nextLine();

                        firstName = sc.next();

                        System.out.println("Enter last name: ");
                        lastName = sc.next();

                        System.out.println("Enter DOB: ");
                        dob = sc.next();

                        System.out.println("Enter gender (MALE or FEMALE): ");
                        String genderStr = sc.next();
                        User.Gender gender = User.Gender.valueOf(genderStr.toUpperCase());

                        validation.validateInput(firstName, lastName, dob);

                        if (managementOffice == 1) {
                            pvCodersBay.createNewUser(rand.nextInt(1000, 5000), firstName, lastName, dob, gender); // ID is not specified
                        } else if (managementOffice == 2) {
                            pvLinz.createNewUser(rand.nextInt(1000, 5000), firstName, lastName, dob, gender);
                        } else {
                            pvVienna.createNewUser(rand.nextInt(1000, 5000), firstName, lastName, dob, gender);
                        }
                    } catch (InvalidPersonDetailsException e) {
                        e.printStackTrace();
                    }

                }

                case 3 -> {
                    String firstName, lastName, dob, genderStr;
                    String houseNumber;
                    User.Gender genderWithAddress;
                    Address address;
                    Validator validation = new Validator();

                    try {
                        System.out.println("Enter first name: ");
                        firstName = sc.next();
                        System.out.println("Enter last name: ");
                        lastName = sc.next();
                        System.out.println("Enter DOB: ");
                        dob = sc.next();
                        System.out.println("Enter gender (MALE or FEMALE): ");
                        genderStr = sc.next();
                        genderWithAddress = User.Gender.valueOf(genderStr.toUpperCase());
                        System.out.println("Enter street address: ");
                        String street = sc.next();

                        //Consume newline
                        sc.nextLine();

                        System.out.println("Enter city: ");
                        String city = sc.nextLine();
                        System.out.println("Enter zip code: ");
                        String zipCode = sc.nextLine();
                        System.out.println("Enter house number: ");
                        houseNumber = sc.next();
                        address = new Address(street, city, zipCode, houseNumber);

                        validation.validateInput(firstName, lastName, dob, city, zipCode, String.valueOf(houseNumber));

                        if (managementOffice == 1) {
                            pvCodersBay.createNewUser(rand.nextInt(1000, 5000), firstName, lastName, dob, genderWithAddress, address); // ID is not specified
                        } else if (managementOffice == 2) {
                            pvLinz.createNewUser(rand.nextInt(1000, 5000), firstName, lastName, dob, genderWithAddress, address);
                        } else {
                            pvVienna.createNewUser(rand.nextInt(1000, 5000), firstName, lastName, dob, genderWithAddress, address);
                        }
                    } catch (InvalidPersonDetailsException e) {
                        e.printStackTrace();
                    }


                }

                case 4 -> {
                    System.out.print("User List for -> ");

                    if (managementOffice == 1) {
                        System.out.println("Coders Bay Office " + pvCodersBay);
                    } else if (managementOffice == 2) {
                        System.out.println("Linz Office " + pvLinz);
                    } else {
                        System.out.println("Vienna Office " + pvVienna);
                    }
                }

                case 5 -> {
                    System.out.println("Enter the name of the user that you want retrieve: ");
                    String userToFind = sc.next();

                    if (managementOffice == 1) {
                        try {
                            pvCodersBay.getUserByName(userToFind);
                            System.out.println(pvCodersBay.getUserByName(userToFind));
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }

                    } else if (managementOffice == 2) {
                        try {
                            pvLinz.getUserByName(userToFind);
                            System.out.println(pvLinz.getUserByName(userToFind));
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }

                    } else if (managementOffice == 3) {
                        try {
                            pvVienna.getUserByName(userToFind);
                            System.out.println(pvVienna.getUserByName(userToFind));
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                }

                case 6 -> {


                    String userIDToDelete = sc.next();
                    String userID = String.valueOf(userIDToDelete);

                    if (userIDToDelete.matches(NUM_REGEX)) {
                        if (managementOffice == 1) {
                            if (pvCodersBay.getUserById(Integer.parseInt(userID)) != null) {
                                pvCodersBay.removeUser(Integer.parseInt(userIDToDelete));
                                System.out.println("Updated list after the deletion: " + pvCodersBay);
                            }

                        } else if (managementOffice == 2) {
                            if (pvLinz.getUserById(Integer.parseInt(userID)) != null) {
                                pvLinz.removeUser(Integer.parseInt(userIDToDelete));
                                System.out.println("Updated list after the deletion: " + pvLinz);
                            }

                        } else if (managementOffice == 3) {
                            if (pvVienna.getUserById(Integer.parseInt(userID)) != null) {
                                pvVienna.removeUser(Integer.parseInt(userIDToDelete));
                                System.out.println("Updated list after the deletion: " + pvVienna);
                            }
                        }
                    } else {
                        System.out.println("Invalid input, please enter a valid id number: ");
                    }
                }

                case 7 -> shouldExit = true;

                default -> System.out.println("Invalid option, please choose a valid option.");
            }
        }
    }
}