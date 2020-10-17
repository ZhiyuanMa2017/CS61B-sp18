public class Weightedquickunionwithpathcompression implements DisjointSets {
    private int[] parents;
    private int[] size;

    public Weightedquickunionwithpathcompression(int N) {
        parents = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
            size[i] = 1;
        }
    }

    public int find(int p) {
        int parentnum = parents[p];
        if (parentnum == parents[parentnum]) {
            return parentnum;
        } else {
            parents[p] = find(parentnum);
            return find(parentnum);
        }
    }


    public void connect(int p, int q) {
        int pnum = find(p);
        int qnum = find(q);
        if (pnum == qnum) {
            return;
        }
        if (size[pnum] > size[qnum]) {
            parents[qnum] = pnum;
            size[pnum] += size[qnum];
        } else {
            parents[pnum] = qnum;
            size[qnum] += size[pnum];
        }
    }


    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
