import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {

    private TrieNode root;

    public class TrieNode {
        private TrieNode() {
            children = new HashMap<>();
            isWord = false;
            extraInfo = new ArrayList<>();
        }

        Map<Character, TrieNode> children;
        public boolean isWord;
        public List<Map<String, Object>> extraInfo;
    }

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word, long id, double lat, double lon) {
        if (word == null)
            return;

        Map<String, Object> m = new HashMap<>();
        m.put("name", word);
        m.put("id", id);
        m.put("lat", lat);
        m.put("lon", lon);

        word = GraphDB.cleanString(word);
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
        }
        node.isWord = true;
        node.extraInfo.add(m);
    }

    public TrieNode startsWith(String prefix) {
        if (prefix == null) {
            return null;
        }
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) {
                return null;
            }
            node = node.children.get(c);
        }
        return node;
    }

    public boolean search(String word) {
        if (word == null) {
            return false;
        }
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (!node.children.containsKey(c)) {
                return false;
            }
            node = node.children.get(c);
        }
        return node.isWord;
    }
}
