public class BSTNode {
    int key;
    BSTNode left;
    BSTNode right;

    BSTNode() {
    }

    BSTNode(int val) {
        this.key = val;
    }

    BSTNode(int val, BSTNode left, BSTNode right) {
        this.key = val;
        this.left = left;
        this.right = right;
    }
}
