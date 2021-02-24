import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {

    private Node root;

    private static class Node {
        private boolean iskey;
        private HashMap<Character, Node> next;
        private List<Map<String, Object>> nodeinfo;


        private Node() {
            this.iskey = false;
            next = new HashMap<>();
            nodeinfo = new ArrayList<>();
        }
    }

    public Trie() {
        root = new Node();
    }

    public Node get(String key) {
        if (key == null) {
            return null;
        }
        Node x = get(root, key, 0);
        return x;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d);
        return get(x.next.get(c), key, d + 1);
    }

    public void put(long id, String name, double lat, double lon) {
        String key = GraphDB.cleanString(name);
        Node cur = root;
        for (char c : key.toCharArray()) {
            cur.next.putIfAbsent(c, new Node());
            cur = cur.next.get(c);
        }
        cur.iskey = true;

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("lat", lat);
        map.put("lon", lon);

        cur.nodeinfo.add(map);
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

    public List<String> keysWithPrefix(String prefix) {
        List<String> res = new ArrayList<>();
        Node cur = root;
        for (char c : prefix.toCharArray()) {
            if (!cur.next.containsKey(c)) {
                return res;
            }
            cur = cur.next.get(c);
        }

        findkeys(cur, prefix, res);
        return res;
    }

    private void findkeys(Node cur, String query, List<String> res) {
        if (cur.iskey) {
            res.add((String) cur.nodeinfo.get(0).get("name"));
        }
        if (cur.next.isEmpty()) {
            return;
        }

        for (Map.Entry<Character, Node> entry : cur.next.entrySet()) {
            findkeys(entry.getValue(), query + entry.getKey(), res);
        }
    }

    public List<Map<String, Object>> getnodeinfo(String name) {
        Node thisnode = get(name);
        List<Map<String, Object>> res = new ArrayList<>();
        if (thisnode != null) {
            res = thisnode.nodeinfo;
        }
        return res;
    }
}
