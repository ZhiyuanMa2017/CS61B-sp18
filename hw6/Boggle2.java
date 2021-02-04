import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;


public class Boggle2 {

    // File path of dictionary file
    static String dictPath = "words.txt";
    private static TrieST trie;
    private static char[][] charBoard;
    private static TreeSet<String> treeSet;
    private static int[] dx = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dy = new int[]{0, 1, 1, 1, 0, -1, -1, -1};


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
        charBoard = new char[m][n];
        for (int i = 0; i < m; i++) {
            if (board[i].length() != n) {
                throw new IllegalArgumentException();
            }
            for (int j = 0; j < n; j++) {
                charBoard[i][j] = board[i].charAt(j);
            }
        }
        trie = new TrieST();
        In dict = new In(dictPath);
        String[] dicts = dict.readAllLines();
        for (int i = 0; i < dicts.length; i++) {
            trie.put(dicts[i], i);
        }

        boolean[][] marked = new boolean[m][n];

        treeSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()) {
                    return o2.length() - o1.length();
                } else {
                    return o1.compareTo(o2);
                }
            }
        });


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                findword("", marked, i, j);
            }
        }

        List<String> res = new ArrayList<>();
        while (res.size() < k) {
            if (treeSet.isEmpty()) {
                break;
            }
            res.add(treeSet.pollFirst());
        }
        return res;
    }

    private static void findword(String pre, boolean[][] markednow, int x, int y) {
        if (!inbound(x, y) || markednow[x][y] || !trie.hasPrefix(pre)) {
            return;
        }

        pre += String.valueOf(charBoard[x][y]);

        markednow[x][y] = true;
        if (pre.length() >= 3 && trie.contains(pre)) {
            treeSet.add(pre);
        }

        for (int i = 0; i < 8; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];
            findword(pre, markednow, xx, yy);
        }
        markednow[x][y] = false;
    }


    private static boolean inbound(int x, int y) {
        return x >= 0 && x < charBoard.length && y >= 0 && y < charBoard[0].length;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<String> res = solve(7, "smallBoard2.txt");
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(res.toString());
    }
}
