import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class DemoCollections {
    public static String cleanString(String s) {
        return s.toLowerCase().replaceAll("[^a-z]","");
    }

    public  static List<String> getWords(String inputFilename) {
        List<String> words = new ArrayList<>();
        In in = new In(inputFilename);
        while (!in.isEmpty()) {
            String nextword = cleanString(in.readString());
            words.add(nextword);
        }
        return words;
    }

    public static int countUniqueWords(List<String> words) {
        Set<String> wordSet = new HashSet<>();
        for (String ithWord : words) {
            wordSet.add(ithWord);
        }
        return wordSet.size();
    }

    public static Map<String, Integer> collectWordCount(List<String> words, List<String> targets){
        Map<String, Integer> counts = new HashMap<>();
        for (String t : targets) {
            counts.put(t, 0);
        }

        for (String s : words) {
            if (counts.containsKey(s)) {
                int oldCount = counts.get(s);
                counts.put(s, oldCount + 1);
            }
        }
        return counts;
    }

    public static void main(String[] args) {
        List<String> w = getWords("libraryOfBabylon.txt");
        System.out.println(countUniqueWords(w));
        //Set<String> wordSet = new HashSet<>(w);

        List<String> targets = new ArrayList<>();
        targets.add("lottery");
        targets.add("the");
        targets.add("babylon");

        System.out.println(collectWordCount(w, targets));

    }
}
