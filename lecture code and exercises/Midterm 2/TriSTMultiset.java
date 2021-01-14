public class TriSTMultiset<T extends Comparable<T>> implements Multiset<T> {
    private class Node {
        private T item;
        private Node left, middle, right;

        public Node(T i) {
            item = i;
        }
    }

    Node root = null;

    public void add(T item) {
        root = add(item, root);
    }

    private Node add(T item, Node p) {
        if (p == null) {
            return new Node((item));
        }
        int cmp = item.compareTo(p.item);
        if (cmp < 0) {
            p.left = add(item, p.left);
        } else if (cmp > 0) {
            p.right = add(item, p.right);
        } else {
            p.middle = add(item, p.middle);
        }
        return p;
    }

    public boolean contains(T item) {
        return true;
    }

    public int multiplicity(T item) {
        return 1;
    }
}
