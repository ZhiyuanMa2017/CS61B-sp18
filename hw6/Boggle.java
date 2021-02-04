import edu.princeton.cs.algs4.MinPQ;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Boggle {

    // File path of dictionary file
    static String dictPath = "words.txt";
    private static Trie trie;
    private static char[][] charBoard;
    private static MinPQ<String> minPQ;
    private static int knum;

    /**
     * Solves a Boggle puzzle.
     *
     * @param k             The maximum number of words to return.
     * @param boardFilePath The file path to Boggle board file.
     * @return a list of words found in given Boggle board.
     * The Strings are sorted in descending order of length.
     * If multiple words have the same length,
     * have them in ascending alphabetical order.
     */
    public static List<String> solve(int k, String boardFilePath) {
        if (k <= 0) {
            throw new IllegalArgumentException();
        }
        if (!new File(dictPath).exists()) {
            throw new IllegalArgumentException();
        }

        String[] board = new In(boardFilePath).readAllLines();
        int m = board.length;
        int n = board[0].length();
        for (String s : board) {
            if (s.length() != n) {
                throw new IllegalArgumentException();
            }
        }
        knum = k;
        trie = new Trie<Integer>();
        In dict = new In(dictPath);
        String[] dicts = dict.readAllLines();
        for (int i = 0; i < dicts.length; i++) {
            trie.put(dicts[i], i);
        }

        charBoard = new char[m][n];
        boolean[][] marked = new boolean[m][n];
        minPQ = new MinPQ<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()) {
                    return o1.length() - o2.length();
                } else {
                    return -o1.compareTo(o2);
                }
            }
        });

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                charBoard[i][j] = board[i].charAt(j);
                marked[i][j] = false;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                findword("", marked, i, j);
            }
        }

        LinkedList<String> res = new LinkedList<>();
        for (int i = 0; i < knum; i++) {
            if (minPQ.isEmpty()) {
                break;
            }
            res.addFirst(minPQ.delMin());
        }
        return res;
    }

    private static void findword(String pre, boolean[][] markednow, int x, int y) {
        int mm = markednow.length;
        int nn = markednow[0].length;
        boolean[][] markedd = new boolean[mm][nn];
        for (int i = 0; i < mm; i++) {
            for (int j = 0; j < nn; j++) {
                markedd[i][j] = markednow[i][j];
            }
        }
        String str = pre + charBoard[x][y];
        markedd[x][y] = true;

        if (trie.contains(str) && str.length() >= 3 && !pqcontains(str)) {
            minPQ.insert(str);
            if (minPQ.size() > knum) {
                minPQ.delMin();
            }
        }

        List<int[]> unused = unmarked(markedd, x, y);
        if (!trie.hasPrefix(str) || unused.isEmpty()) {
            return;
        }
        for (int[] ints : unused) {
            findword(str, markedd, ints[0], ints[1]);
        }
    }

    private static boolean pqcontains(String s) {
        for (String s1 : minPQ) {
            if (s1.equals(s)) {
                return true;
            }
        }
        return false;
    }

    private static List<int[]> unmarked(boolean[][] marked, int x, int y) {
        List<int[]> list = new ArrayList<>();
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (inbound(i, j) && !marked[i][j]) {
                    list.add(new int[]{i, j});
                }
            }
        }
        return list;

    }

    private static boolean inbound(int x, int y) {
        if (x >= 0 && x < charBoard.length && y >= 0 && y < charBoard[0].length) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<String> res = solve(7, "smallBoard2.txt");
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(res.toString());
    }
}
