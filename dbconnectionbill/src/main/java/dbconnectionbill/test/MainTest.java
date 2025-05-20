package dbconnectionbill.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dbconnectionbill.views.Main;

public class MainTest {

    @Test
    public void testMainExecution() {
        try {
            Main.main(new String[]{});  // Simulating execution
            assertTrue(true);  // If no exceptions occur, test passes
        } catch (Exception e) {
            fail("Main execution failed: " + e.getMessage());
        }
    }
}
