public class DataIndexedIntegerSet {
    boolean[] present;
    public DataIndexedIntegerSet() {
        present = new boolean[100000];
    }
    public void insert(int i) {
        present[i] = true;
    }
    public boolean contains(int i) {
        return present[i];
    }
}
