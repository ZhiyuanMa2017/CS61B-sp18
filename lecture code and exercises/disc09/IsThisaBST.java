public class IsThisaBST {
    public static boolean isBSTBad(TreeNode T) {
        if (T == null) {
            return true;
        } else if (T.left != null && T.left.val > T.val) {
            return false;
        } else if (T.right != null && T.right.val < T.val) {
            return false;
        } else {
            return isBSTBad(T.left) && isBSTBad(T.right);
        }
    }
    public static boolean isBSTGood(TreeNode T) {
        return isBSTHelper(T, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    public static boolean isBSTHelper(TreeNode T, int minvalue, int maxvalue) {
        if (T == null) {
            return true;
        } else if (T.val < minvalue || T.val > maxvalue) {
            return false;
        } else {
            return isBSTHelper(T.left, minvalue, T.val) && isBSTHelper(T.right, T.val, maxvalue);
        }
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
}
