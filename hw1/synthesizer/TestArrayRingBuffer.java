package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());
        arb.enqueue(1);
        assertEquals(1, arb.dequeue());
        assertTrue(arb.isEmpty());
        for (int i = 0; i < 10; i++) {
            arb.enqueue(1);
        }
        assertTrue(arb.isFull());
        arb.dequeue();
        arb.dequeue();
        arb.enqueue(11);
        arb.enqueue(111);
        assertTrue(arb.isFull());

    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
