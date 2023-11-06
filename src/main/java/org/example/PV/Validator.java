package org.example.PV;

import static org.example.PV.Main.*;

public class Validator {
    public static final String NAME_REGEX = "^[A-Za-z\\s]+$";
    public static final String DOB_REGEX = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";
    public static final String NUM_REGEX = "^\\d+$";

    public void validateInput(String firstName, String lastName) throws InvalidPersonDetailsException {
        if (!firstName.matches(NAME_REGEX) || !lastName.matches(NAME_REGEX)) {
            throw new InvalidPersonDetailsException("Process restarted: Please enter a valid first and last name");
        }
    }

    public void validateInput(String firstName, String lastName, String dob) throws InvalidPersonDetailsException {
        if (!firstName.matches(NAME_REGEX) || !lastName.matches(NAME_REGEX) || !dob.matches(DOB_REGEX)) {
            throw new InvalidPersonDetailsException("Process restarted: Please enter valid input e.g(dd/mm/yyyy) * all names cant contain numbers or special characters");
        }
    }

    public void validateInput(String firstName, String lastName, String dob, String city, String zipCode, String houseNumber) throws InvalidPersonDetailsException {
        if (!firstName.matches(NAME_REGEX) || !lastName.matches(NAME_REGEX) || !dob.matches(DOB_REGEX) || !city.matches(NAME_REGEX) || !zipCode.matches(NUM_REGEX) || !houseNumber.matches(NUM_REGEX)) {
            throw new InvalidPersonDetailsException("Process restarted: Please enter valid input");
        }
    }
}