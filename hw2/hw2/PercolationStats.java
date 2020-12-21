package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] threshold;
    private int t;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("illegal input");
        }
        t = T;
        threshold = new double[T];
        for (int i = 0; i < T; i++) {
            threshold[i] = experiment(pf.make(N), N);
        }
    }
    // perform T independent experiments on an N-by-N grid

    private double experiment(Percolation p, int n) {
        while (!p.percolates()) {
            int row = StdRandom.uniform(n);
            int col = StdRandom.uniform(n);
            while (p.isOpen(row, col)) {
                row = StdRandom.uniform(n);
                col = StdRandom.uniform(n);
            }
            p.open(row, col);
        }
        return (double) p.numberOfOpenSites() /(n*n);
    }

    public double mean() {
        return StdStats.mean(threshold);
    }
    // sample mean of percolation threshold

    public double stddev() {
        return StdStats.stddev(threshold);
    }
    // sample standard deviation of percolation threshold

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(t);
    }
    // low endpoint of 95% confidence interval

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(t);
    }
    // high endpoint of 95% confidence interval
}
