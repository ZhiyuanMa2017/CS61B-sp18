import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class UseADTs {
    public boolean twosum(int[] A, int k) {
        HashSet<Integer> seen = new HashSet<>();
        for (int i : A) {
            if (seen.contains(k - i)) {
                return true;
            }
            seen.add(i);
        }
        return false;
    }

    public static void topFivePopularWords(String[] words, int k) {
        HashMap<String, Integer> wordscount = new HashMap<>();
        for (String word : words) {
            if (wordscount.containsKey(word)) {
                wordscount.put(word, wordscount.get(word) + 1);
            } else {
                wordscount.put(word, 1);
            }
        }

        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return wordscount.get(o2) - wordscount.get(o1);
            }
        });

        for (String s : wordscount.keySet()) {
            pq.add(s);
        }

        for (int i = 0; i < pq.size(); i++) {
            System.out.println(pq.poll());
        }


    }
}
