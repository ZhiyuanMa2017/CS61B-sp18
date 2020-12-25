public class DepthFirstTraversal {

    public void preOrder(BSTNode x) {
        if (x == null) return;
        System.out.println(x.key);
        preOrder(x.left);
        preOrder(x.right);
    }
    // Preorder: “Visit” a node, then traverse its children
    // We walk the graph, from top going counter-clockwise
    // Shout every time we pass the LEFT of a node
    // Θ(N)

    public void inOrder(BSTNode x) {
        if (x == null) return;
        inOrder(x.left);
        System.out.println(x.key);
        inOrder(x.right);
    }
    // Inorder traversal: Traverse left child, visit, then traverse right child
    // Keys in sorted order for binary tree
    // Shout when you cross the bottom

    public void postOrder(BSTNode x) {
        if (x == null) return;
        postOrder(x.left);
        postOrder(x.right);
        System.out.println(x.key);
    }
    // Postorder traversal: Traverse left, traverse right, then visit
    // Shout when you cross the right

}
