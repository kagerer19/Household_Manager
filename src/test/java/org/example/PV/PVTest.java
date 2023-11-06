package org.example.PV;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class PVTest {
    @Test
    void testRetrieveUserByName() {
        PV pvCodersBay = new PV();

        assertThrows(NullPointerException.class, () -> {
            pvCodersBay.getUserByName("Bob");
        });

    }
}