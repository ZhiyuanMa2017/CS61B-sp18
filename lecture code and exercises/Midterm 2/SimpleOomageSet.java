import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class SimpleOomageSet {
    private WeightedQuickUnionUF wq = new WeightedQuickUnionUF(140609);
    public void add(T item) {
        wq.union(item.hashcode(), 140608);
    }
    public boolean contains(T item) {
        wq.connected(item.hashcode(), 140608);
    }
} // reminder: WeightedQuickUnionUF methods are union() and connected()
