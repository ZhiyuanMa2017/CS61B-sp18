package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private int topsentinel;
    private int bottomsentinel;
    private WeightedQuickUnionUF dset;
    private WeightedQuickUnionUF topset;
    private boolean[][] grid;
    private int opensites;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N should be greater than 0");
        }
        this.n = N;
        grid = new boolean[N][N];
        dset = new WeightedQuickUnionUF(N * N + 2);
        topset = new WeightedQuickUnionUF(N * N + 1);
        topsentinel = N * N;
        bottomsentinel = N * N + 1;
        opensites = 0;

    }
    // create N-by-N grid, with all sites initially blocked

    public void open(int row, int col) {
        if (row < 0 || row > n - 1 || col < 0 || col > n - 1) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        if (!grid[row][col]) {
            grid[row][col] = true;
            opensites++;
            int thisindex = xyTo1D(row, col);
            if (row == 0) {
                dset.union(thisindex, topsentinel);
                topset.union(thisindex, topsentinel);
            }
            if (row == n - 1) {
                dset.union(thisindex, bottomsentinel);
            }
            if (row > 0 && grid[row - 1][col]) {
                int upperneighbor = xyTo1D(row - 1, col);
                dset.union(thisindex, upperneighbor);
                topset.union(thisindex, upperneighbor);
            }
            if (row < n - 1 && grid[row + 1][col]) {
                int lowerneighbor = xyTo1D(row + 1, col);
                dset.union(thisindex, lowerneighbor);
                topset.union(thisindex, lowerneighbor);
            }

            if (col < n - 1 && grid[row][col + 1]) {
                int rightneighbor = xyTo1D(row, col + 1);
                dset.union(thisindex, rightneighbor);
                topset.union(thisindex, rightneighbor);
            }
            if (col > 0 && grid[row][col - 1]) {
                int leftneighbor = xyTo1D(row, col - 1);
                dset.union(thisindex, leftneighbor);
                topset.union(thisindex, leftneighbor);
            }
        }
    }
    // open the site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        if (row < 0 || row > n - 1 || col < 0 || col > n - 1) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        return grid[row][col];
    }
    // is the site (row, col) open?

    public boolean isFull(int row, int col) {
        if (row < 0 || row > n - 1 || col < 0 || col > n - 1) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        if (!isOpen(row, col)) {
            return false;
        } else {
            int thisindex = xyTo1D(row, col);
            return topset.connected(thisindex, topsentinel);
        }
    }
    // is the site (row, col) full?

    public int numberOfOpenSites() {
        return this.opensites;
    }
    // number of open sites

    public boolean percolates() {
        return dset.connected(topsentinel, bottomsentinel);
    }
    // does the system percolate?

    private int xyTo1D(int r, int c) {
        return r * n + c;
    }


    public static void main(String[] args) {
    }  // use for unit testing (not required)
}
