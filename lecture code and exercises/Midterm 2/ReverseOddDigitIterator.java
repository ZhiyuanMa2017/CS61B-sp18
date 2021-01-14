import java.util.Iterator;

public class ReverseOddDigitIterator implements Iterable<Integer>,
        Iterator<Integer> {
    private int value;
    public ReverseOddDigitIterator(int v) {
        value = v;
    }
    public boolean hasNext() {
        if (value == 0) {
            return false;
        }
        if (value % 2 == 1) {
            return true;
        } else {
            value = value / 10;
            return hasNext();
        }
    }
    public Integer next() {
        int d = value % 10;
        value = value / 10;
        return d;
    }
    public Iterator<Integer> iterator() {
        return this;
    }
} // assume any classes you need from java.util have been imported