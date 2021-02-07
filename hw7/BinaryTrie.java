import edu.princeton.cs.algs4.MinPQ;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BinaryTrie implements Serializable {


    private static class Node implements Comparable<Node>, Serializable {
        private final char ch;
        private final int freq;
        private final Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }


        private boolean isLeaf() {
            return (left == null) && (right == null);
        }

        // compare, based on frequency
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    private Node root;

    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        MinPQ<Node> pq = new MinPQ<>();
        for (Character c : frequencyTable.keySet()) {
            pq.insert(new Node(c, frequencyTable.get(c), null, null));
        }
        while (pq.size() > 1) {
            Node left = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.insert(parent);
        }
        root = pq.delMin();
    }

    public Match longestPrefixMatch(BitSequence querySequence) {
        int length = querySequence.length();
        Node x = root;
        int i;
        BitSequence bs = new BitSequence();
        for (i = 0; i < length; i++) {
            if (x.isLeaf()) {
                break;
            }
            if (querySequence.bitAt(i) == 1) {
                x = x.right;
            } else {
                x = x.left;
            }
            bs = bs.appended(querySequence.bitAt(i));
        }
        return new Match(bs, x.ch);
    }

    public Map<Character, BitSequence> buildLookupTable() {
        Map<Character, BitSequence> table = new HashMap<>();
        BitSequence bs = new BitSequence();
        buildCode(table, root, bs);
        return table;
    }

    private void buildCode(Map<Character, BitSequence> table, Node x, BitSequence bs) {
        if (!x.isLeaf()) {
            buildCode(table, x.left, bs.appended(0));
            buildCode(table, x.right, bs.appended(1));
        } else {
            table.put(x.ch, bs);
        }
    }
}
