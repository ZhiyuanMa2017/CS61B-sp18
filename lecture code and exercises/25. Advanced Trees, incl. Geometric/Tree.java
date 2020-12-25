public class Tree {
    int key;
    Tree left;
    Tree right;
    int height;
    Tree() {
    }

    Tree(int val) {
        this.key = val;
    }

    Tree(int val, Tree left, Tree right) {
        this.key = val;
        this.left = left;
        this.right = right;
    }

    public int height() {
        return height;
    }
}