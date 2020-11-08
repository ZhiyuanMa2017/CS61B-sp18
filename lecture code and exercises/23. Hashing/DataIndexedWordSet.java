public class DataIndexedWordSet {
    boolean[] present;

    public DataIndexedWordSet() {
        present = new boolean[100000];
    }

    public void insert(String s) {
        int intRep = convertToInt(s);
        present[intRep] = true;
    }

    public boolean contains(String s) {
        int intRep = convertToInt(s);
        return present[intRep];
    }

    public static int letterNum(String s, int i) {
        int ithChar = s.charAt(i);
        if ((ithChar < 'a') || (ithChar > 'z')) {
            throw new IllegalArgumentException();
        }
        return ithChar - 'a' + 1;
    }

    public static int convertToInt(String s) {
        int intRep = 0;
        for (int i = 0; i < s.length(); i++) {
            intRep = intRep << 5;
            intRep = intRep + letterNum(s, i);
        }
        return intRep;
    }
}
