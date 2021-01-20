package lab11.graphs;

import edu.princeton.cs.algs4.MinPQ;


/**
 * @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    MinPQ<Integer> pq;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        pq = new MinPQ<>((o1, o2) -> {
            if (h(o1) + distTo[o1] < h(o2) + distTo[o2]) {
                return -1;
            } else if (h(o1) + distTo[o1] == h(o2) + distTo[o2]) {
                return 0;
            } else {
                return 1;
            }
        });
    }

    /**
     * Estimate of the distance from v to the target.
     */
    private int h(int v) {
        return Math.abs(maze.toX(s) - maze.toX(t)) + Math.abs(maze.toY(s) - maze.toY(t));
    }

    /**
     * Finds vertex estimated to be closest to target.
     */
    private int findMinimumUnmarked() {
        return pq.delMin();
        /* You do not have to use this method. */
    }

    /**
     * Performs an A star search from vertex s.
     */
    private void astar(int ss) {
        pq.insert(ss);
        marked[ss] = true;
        announce();
        while (!pq.isEmpty()) {
            int v = findMinimumUnmarked();
            announce();
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    pq.insert(w);
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    if (w == t) {
                        targetFound = true;
                        announce();
                    }

                    if (targetFound) {
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

