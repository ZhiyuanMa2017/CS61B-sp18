public class OffByN implements CharacterComparator {

    public int diff;

    public  OffByN(int N) {
        diff = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (x - y == diff || y - x == diff) {
            return true;
        } return false;
    }


}
