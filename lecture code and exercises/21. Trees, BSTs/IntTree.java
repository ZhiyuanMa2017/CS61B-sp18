public class IntTree {
    public IntTree (int data, IntTree left, IntTree right) {
        this.data = data; this.left = left; this.right = right;
    }
    public final int data;
    public IntTree left, right;

    /** Assuming that T and L are binary search trees each with a single
     * sentinel tree node, and that all left children in L aside from
     * the sentinel are empty (L is "right-leaning"), returns (the
     * sentinel of) a binary search tree containing the original elements
     * of T and L. The operation is destructive, and creates no
     * new nodes. In the worst case, it takes time linear in the
     * total number of items in T and L. */
    public static IntTree mergeRight (IntTree T, IntTree L) {
        T.left = mergeRight2(T.left, Integer.MAX_VALUE, L);
        return T;
    }

    /** Assuming T is a BST, and L is the sentinel of a
     * right-leaning BST, return the result of inserting all items of L
     * that are <= NEXT in T, removing them from L. */
    private static IntTree mergeRight2(IntTree T, int next, IntTree L) {
        if(L.left == null) {
            return T;
        }
        if(T == null) {
            if(L.left.data <= next) {
                IntTree p = L.left;
                L.left = L.left.right;
                p.right = mergeRight2(null, next, L);
                return p;
            }
        } else {
            if(L.left.data <= T.data) {
                T.left = mergeRight2(T.left, T.data, L);
            }
            if(L.left != null && T.left.data > T.data) {
                T.right = mergeRight2(T.right, next, L);
            }
        }
        return T;
    }

}