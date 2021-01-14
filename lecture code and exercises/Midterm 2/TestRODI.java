import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class TestRODI {
    @Test
    public void testRODI() {
        ReverseOddDigitIterator odi = new ReverseOddDigitIterator(12345770);
        List<Integer> expected = List.of(7,7,5,3,1);
        List<Integer> actual = IterableUtils.toList(odi);
        assertEquals(expected, actual);

    }
} // assume any classes you need from java.util have been imported
