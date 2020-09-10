import org.junit.Test;
import static org.junit.Assert.*;

public class Sans {
    public static class IntList {
        public int first;
        public IntList rest;
        public IntList(int f, IntList r) {
            first = f;
            rest = r;
        }
        public IntList() {}
        public static IntList of(Integer... args) { /* works correctly */
            return null;
        }
        public boolean equals(Object x) { /*works correctly with assertEquals*/
            return false;
        }
    }

    public static int[] sans(int[] x, int y) {
        int[] xclean = new int[x.length];
        int c = 0;
        for (int i = 0; i < x.length; i += 1) {
            if (_____________________________________) {
                __________________________________________________
                        __________________________________________________;
            }
        }
        int[] r = ________________________________________________;
        System.arraycopy(_________________________________________);
        return r; // arraycopy parameters are:
    }
    // srcArr, srcStartIdx, destArr, destStartIdx, numToCopy
    // where src->source, dest->destination, Idx->index

    public static IntList ilsans(IntList x, int y) {
        if (___________________________________________________) {
            return _________________________________________;
        }
        if (______________________________________________) {
            return __________________________________________________;
        }
        return new _____________________________________________;
    }

    public static IntList dilsans(IntList x, int y) {
        if (___________________________________________________) {
            ________________________________________________________________;
        }
        ________________________________________________________________;
        if (x.first == y) {
            return _______________________________________;
        }
        return ___________________________________________;
    }


    public class TestSans {
        @Test
        public void testSans() { // TEST THE ARRAY VERSION OF SANS
            int[] x = ______________________________________________________;
            int y = ________________________________________________________;
            int[] expected = _______________________________________________;
            int[] actual = _________________________________________________;
            ________________________________________________________________
                    ________________________________________________________________;
        }

        @Test // TEST THE NON-DESTRUCTIVE INTLIST VERSION OF SANS
        public void testIlsans() {
            IntList x = IntList.of(_________________________________________);
            int y = ________________________________________________________;
            IntList expected = IntList.of(__________________________________ );
            IntList actual = _______________________________________________;
                    ________________________________________________________________
                    ________________________________________________________________;
                    ________________________________________________________________;
        }
    }

}
