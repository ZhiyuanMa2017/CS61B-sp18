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
        while(L.left!=null){
        T.left = mergeRight2(T.left, Integer.MAX_VALUE, L);}
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

    public static void main(String[] args) {
        IntTree t8 = new IntTree(8, null, null);
        IntTree t10 = new IntTree(10, t8, null);
        IntTree t3 = new IntTree(3, null, t10);
        IntTree t14 = new IntTree(14, null, null);
        IntTree t27 = new IntTree(27, null, null);
        IntTree t16 = new IntTree(16, t14, t27);
        IntTree t12 = new IntTree(12, t3, t16);
        IntTree T = new IntTree(123456, t12, null);

        IntTree t26 = new IntTree(26, null, null);
        IntTree t11 = new IntTree(11, null, t26);
        IntTree t1 = new IntTree(1, null, t11);
        IntTree L = new IntTree(123456, t1, null);

        IntTree result = mergeRight(T, L);

    }

}