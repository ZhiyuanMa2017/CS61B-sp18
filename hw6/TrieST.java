import edu.princeton.cs.algs4.Queue;

import java.util.List;


public class TrieST<Value> {
    private static final int R = 256;

    private Node root;
    private int n;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public TrieST() {
    }

    public Value get(String key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }

    public void put(String key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (val == null) {
            delete(key);
        } else {
            root = put(root, key, val, 0);
        }
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            if (x.val == null) {
                n++;
            }
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    public void delete(String key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            if (x.val != null) {
                n--;
            }
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }
        if (x.val != null) {
            return x;
        }
        for (int i = 0; i < R; i++) {
            if (x.next[i] != null) {
                return x;
            }
        }
        return null;
    }


    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> results = new Queue<String>();
        Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }

    private void collect(Node x, StringBuilder prefix, Queue<String> results) {
        if (x == null) {
            return;
        }
        if (x.val != null) {
            results.enqueue(prefix.toString());
        }
        for (char i = 0; i < R; i++) {
            prefix.append(i);
            collect(x.next[i], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    public String longestPrefixOf(String query) {
        if (query == null) {
            throw new IllegalArgumentException();
        }
        int length = longestPrefixOf(root, query, 0, -1);
        if (length == -1) {
            return null;
        } else {
            return query.substring(0, length);
        }
    }

    private int longestPrefixOf(Node x, String query, int d, int length) {
        if (x == null) {
            return length;
        }
        if (x.val != null) {
            length = d;
        }
        if (d == query.length()) {
            return length;
        }
        char c = query.charAt(d);
        return longestPrefixOf(x.next[c], query, d + 1, length);
    }

    public boolean hasPrefix(String query) {
        if (query == null) {
            return false;
        }
        return hasPrefix(root, query, 0);
    }

    private boolean hasPrefix(Node x, String key, int d) {
        if (x == null) {
            return false;
        }
        if (d == key.length()) {
            return true;
        }
        char c = key.charAt(d);
        if (x.next[c] == null) {
            return false;
        }
        return hasPrefix(x.next[c], key, d + 1);
    }

    public static void main(String[] args) {
        TrieST<Integer> st = new TrieST<Integer>();
        List<String> l = List.of("she", "sells", "sea", "shells", "by", "the", "sea", "shore");
        for (int i = 0; i < l.size(); i++) {
            String key = l.get(i);
            st.put(key, i);
        }

        // print results
        if (st.size() < 100) {
            StdOut.println("keys(\"\"):");
            for (String key : st.keys()) {
                StdOut.println(key + " " + st.get(key));
            }
            StdOut.println();
        }
//
//        StdOut.println("longestPrefixOf(\"shellsort\"):");
//        StdOut.println(st.longestPrefixOf("shellsort"));
//        StdOut.println();
//
//        StdOut.println("longestPrefixOf(\"quicksort\"):");
//        StdOut.println(st.longestPrefixOf("quicksort"));
//        StdOut.println();
//
//        StdOut.println("keysWithPrefix(\"shor\"):");
//        for (String s : st.keysWithPrefix("shor")) {
//            StdOut.println(s);
//        }
//        StdOut.println();

        StdOut.println("hasPrefixOf(\"se\"):");
        StdOut.println(st.hasPrefix("se"));
        StdOut.println();

        StdOut.println("hasPrefixOf(\"she\"):");
        StdOut.println(st.hasPrefix("she"));
        StdOut.println();

        StdOut.println("hasPrefixOf(\"thee\"):");
        StdOut.println(st.hasPrefix("thee"));
        StdOut.println();


    }
}
