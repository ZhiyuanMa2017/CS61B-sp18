package lab11.graphs;


/**
 * @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private boolean hasCycle = false;
    private Maze maze;
    private int[] parent;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
    }

    @Override
    public void solve() {
        parent = new int[maze.V()];
        int s = maze.xyTo1D(1, 1);
        edgeTo[s] = s;
        parent[s] = s;
        dfscycle(s);
    }

    private void dfscycle(int v) {
        marked[v] = true;
        announce();
        if (hasCycle) {
            return;
        }
        for (int w : maze.adj(v)) {
            if (hasCycle) {
                return;
            }
            if (!marked[w]) {
                parent[w] = v;
                dfscycle(w);
            } else {
                if (w != parent[v]) {
                    parent[w] = v;
                    edgeTo[w] = v;
                    int c = v;
                    while (c != w) {
                        edgeTo[c] = parent[c];
                        c = parent[c];
                    }
                    hasCycle = true;
                    announce();
                    return;
                }
            }
        }
    }
}
