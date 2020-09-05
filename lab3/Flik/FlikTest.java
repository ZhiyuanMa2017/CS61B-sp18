import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {
    @Test
    public void testisSameNumber() {
        int a = 128;
        int b = 128;
        assertTrue(a == b);
        assertEquals(a, b);
        assertTrue(Flik.isSameNumber(a, b));
    }
}
