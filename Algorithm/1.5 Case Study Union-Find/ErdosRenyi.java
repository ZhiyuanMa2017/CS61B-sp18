/******************************************************************************
 *  Compilation:  javac ErdosRenyi.java
 *  Execution:    java ErdosRenyi n trials
 *  Dependencies: StdRandom.java StdStats.java UF.java
 *
 *  Repeatedly add random edges (with replacement) to a graph on n
 *  vertices until the graph is connected. Report the mean and
 *  standard deviation of the number of edges added.
 *
 *  When n is large, Erdos and Renyi proved that after about 1/2 n ln n
 *  additions, the graph will have a 50/50 chance of being connected.
 *
 *  % java ErdosRenyi 100 1000
 *  1/2 n ln n = 230.25850929940458
 *  mean       = 263.584
 *  stddev     = 64.39309702134229
 *
 *  % java ErdosRenyi 100 1000
 *  1/2 n ln n = 230.25850929940458
 *  mean       = 263.93
 *  stddev     = 63.54839966513712
 *
 *  % java ErdosRenyi 12800 1000
 *  1/2 n ln n = 60526.08287940933
 *  mean       = 64231.526
 *  stddev     = 8362.273790143683
 *
 *
 *
 *         Computational Experiments
 *         --------------------------
 *
 *       n    mean # edges    1/2 n ln n
 *  ------------------------------------
 *     100            260            230
 *     200            600            530
 *     400           1300           1200
 *     800           2900           2700
 *    1600           6400           5900
 *    3200          14000          13000
 *    6400          30000          28000
 *   12800          64000          61000
 *   25600         140000         130000
 *   51200         290000         280000
 *  102400         620000         590000
 *  204800        1300000        1300000
 *  409600        2700000        2700000
 *
 ******************************************************************************/

public class ErdosRenyi {

    // number of random edges (with replacement) needed for an n-vertex
    // graph to become connected
    public static int count(int n) {
        int edges = 0;
        UF uf = new UF(n);
        while (uf.count() > 1) {
            int i = StdRandom.uniform(n);
            int j = StdRandom.uniform(n);
            uf.union(i, j);
            edges++;
        }
        return edges;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);          // number of vertices
        int trials = Integer.parseInt(args[1]);     // number of trials
        int[] edges = new int[trials];              // record statistics

        // repeat the experiment trials times
        for (int t = 0; t < trials; t++) {
            edges[t] = count(n);
        }

        // report statistics
        StdOut.println("1/2 n ln n = " + 0.5 * n * Math.log(n));
        StdOut.println("mean       = " + StdStats.mean(edges));
        StdOut.println("stddev     = " + StdStats.stddev(edges));
    }
}