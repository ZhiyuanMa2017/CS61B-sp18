public class Sans {

    /** Non-destructively creates a copy of x that contains no y. */
    public static int[] sans(int[] x, int y) {
        int[] xclean = new int[x.length];
        int c = 0;
        for (int i = 0; i < x.length; i += 1) {
            if (x[i] != y) {
                xclean[c] = x[i];
                c = c + 1;
            }
        }
        int[] r = new int[c];
        System.arraycopy(xclean, 0, r, 0, c);
        return r; // arraycopy parameters are: srcArr, srcStartIdx, destArr, destStartIdx, numToCopy
    } // where src->source, dest->destination, Idx->index

    /** Non-destructively creates a copy of x that contains no y. */
    public static IntList ilsans(IntList x, int y) {
        if (x == null) {
            return null;
        }
        if (x.first == y) {
            return ilsans(x.rest, y);
        }
        return new IntList(x.first, ilsans(x.rest, y));
    }

    /** Destructively creates a copy of x that contains no y. You may
     not use new. */
    public static IntList dilsans(IntList x, int y) {
        if (x == null) {
            return null;
        }
        x.rest = dilsans(x.rest, y);
        if (x.first == y) {
            return x.rest;
        }
        return x;
    }

}
