import java.util.HashMap;

public class Trie {

    private Node root;

    private static class Node {
        private boolean iskey;
        private HashMap<Character, Node> next;


        private Node() {
            this.iskey = false;
            next = new HashMap<>();
        }
    }

    public Trie() {
        root = new Node();
    }

    public void put(String key) {
        Node cur = root;
        for (char c : key.toCharArray()) {
            cur.next.putIfAbsent(c, new Node());
            cur = cur.next.get(c);
        }
        cur.iskey = true;
    }

    public boolean contains(String key) {
        Node cur = root;
        for (char c : key.toCharArray()) {
            cur = cur.next.get(c);
            if (cur == null) {
                return false;
            }
        }
        return cur.iskey;
    }

    public boolean hasPrefix(String query) {
        Node cur = root;
        for (char c : query.toCharArray()) {
            cur = cur.next.get(c);
            if (cur == null) {
                return false;
            }
        }
        return true;
    }

}
