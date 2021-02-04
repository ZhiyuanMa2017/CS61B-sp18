import edu.princeton.cs.algs4.MinPQ;

import java.util.Arrays;
import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        MinPQ<String> minPQ = new MinPQ<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()) {
                    return o1.length() - o2.length();
                } else {
                    return -o1.compareTo(o2);
                }
            }
        });
        String s1 = "abc";
        String s2 = "def";
        minPQ.insert(s1);
        minPQ.insert(s2);
        System.out.println(s1.compareTo(s2));
        System.out.println(minPQ.min());

        boolean[][] t = new boolean[2][3];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                t[i][j] = true;
            }
        }
        t[1][0] = false;
        boolean[][] f = t.clone();
        System.out.println(Arrays.deepToString(f));

        TrieST<Integer> st = new TrieST<Integer>();
        In dict = new In("words.txt");
        String[] dicts = dict.readAllLines();
        for (int i = 0; i < dicts.length; i++) {
            st.put(dicts[i], i);
        }

        int i = 0;
        StdOut.println("keys(\"\"):");
        for (Object key : st.keys()) {
            StdOut.println(key + " " + st.get((String) key));
            i++;
            if (i == 10) {
                break;
            }
        }
        StdOut.println();
        System.out.println(st.size());
        System.out.println(st.hasPrefix("net"));

        System.out.println((int) 'é');

    }
}
