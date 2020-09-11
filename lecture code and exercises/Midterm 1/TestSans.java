import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;

public class TestSans {

    @Test
    public void testSans() { // TEST THE ARRAY VERSION OF SANS
        int[] x = {4, 5, 6, 7, 8, 9, 7, 65, 3, 32, 2, 4};
        int y = 7;
        int[] expected = {4, 5, 6, 8, 9, 65, 3, 32, 2, 4};
        int[] actual = Sans.sans(x, y);
        assertArrayEquals(expected, actual);
        assertNotEquals(x, actual);
    }

    @Test // TEST THE NON-DESTRUCTIVE INTLIST VERSION OF SANS
    public void testIlsans() {
        IntList x = IntList.of(4, 5, 5, 5, 5, 5, 5, 5, 55, 5, 5, 5, 6, 6, 6, 6, 6);
        int y = 5;
        IntList expected = IntList.of(4, 55, 6, 6, 6, 6, 6);
        IntList actual = Sans.ilsans(x, y);
        assertEquals(expected, actual);
        assertNotEquals(x, actual);
    }
}
