package org.example.PV;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    @Test
    void validateInputFailure() {
        Validator validation = new Validator();

        // Test Incorrect first and last name
        assertThrows(InvalidPersonDetailsException.class, () -> {
            validation.validateInput("", "Smith");
        });

        // Test Incorrect Date of birth format
        assertThrows(InvalidPersonDetailsException.class, () -> {
            validation.validateInput("John", "Smith", "4/7/96");
            validation.validateInput("John", "Smith", "4th July 2002");
            validation.validateInput("John", "Smith", "2002/07/04");
        });

        // Test Incorrect Address details
        assertThrows(InvalidPersonDetailsException.class, () -> validation.validateInput("John", "Smith", "04/07/1996", "Linz", "twenty-forty", "nineteen"));
    }
}