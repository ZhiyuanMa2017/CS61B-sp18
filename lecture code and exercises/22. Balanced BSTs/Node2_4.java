/** Represents a node in a 2-4 (aka 2-3-4) tree. There is only one
 * instance of this base class itself, Node2_4.EMPTY, representing
 * the empty tree. */
class Node2_4 {
    /** The unique empty node. */
    final static Node2_4 EMPTY = new Node2_4();

    /** Return my Kth child (numbering from 0). */
    Node2_4 kid(int k) {
        /* Implementation not shown. */
        return null;
    }

    /** Return the number of my children (which is one more than the
     * the number of my keys). */
    int arity() {
        /* Implementation not shown. */
        return 0;
    }

    /** Return my Kth key (numbering from 0). */
    String key(int k) {
        /* Implementation not shown. */
        return null;
    }

    /** Return true iff KEY is a key in the tree rooted at me. */
    boolean contains(String key) {
        return false;
    }
}
