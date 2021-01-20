import edu.princeton.cs.algs4.Graph;

import java.util.HashSet;
import java.util.Set;

public class twocolor {
    public static void twocolor(Graph G, int v, Set<Integer> a, Set<Integer> b) {
        a.add(v);
        for (int u : G.adj(v)) {
            if (a.contains(u)) {
                throw new IllegalArgumentException("graph is not bipartite");
            }
            if (!b.contains(u)) {
                twocolor(G, u, b, a);
            }
        }
    }

    public static void main(String[] args) {
        HashSet<Integer> blue = new HashSet<Integer>();
        HashSet<Integer> green = new HashSet<Integer>();
        Graph G = new Graph(1);
        twocolor(G, 0,blue, green);
        System.out.println("Blue vertices are:"+blue.toString());
    }
}
