package dbconnectionbill.test;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;
import dbconnectionbill.views.Main;

public class InputValidationTest {

    @Test
    public void testValidPhoneNumber() {
        Scanner scanner = new Scanner("9876543210\n");  // Simulating user input
        String phone = Main.getValidPhoneNumber(scanner);  // Assuming Main has this method
        assertEquals("9876543210", phone, "Valid phone should pass");
    }

    @Test
    public void testInvalidPhoneNumber() {
        Scanner scanner = new Scanner("12345\n9876543210\n");  // First input is invalid
        String phone = Main.getValidPhoneNumber(scanner);
        assertEquals("9876543210", phone, "Should reject invalid input and accept correct one");
    }
}
