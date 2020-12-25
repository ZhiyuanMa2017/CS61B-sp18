public class fa14mt2q4treediameter {
    /**
     * Return the height of T, where the height of an empty tree is -1 and
     * that of a tree with one node is 0.
     */
    public static int height(BinTree T) {
        if (T == null) {
            return -1;
        } else {
            return Math.max(height(T.left), height(T.right)) + 1;
        }
    }

    /**
     * Return the diameter of T.
     */
    public static int diameter(BinTree T) {
        if (T == null) {
            return 0;
        } else {
            int childdiameter = Math.max(diameter(T.left), diameter(T.right));
            int selfdiameter = 2 +  diameter(T.left) + diameter(T.right);
            return Math.max(childdiameter, selfdiameter);
        }
    }
}

class BinTree {
    int key;
    BinTree left;
    BinTree right;

    BinTree() {
    }

    BinTree(int val) {
        this.key = val;
    }

    BinTree(int val, BinTree left, BinTree right) {
        this.key = val;
        this.left = left;
        this.right = right;
    }
}
