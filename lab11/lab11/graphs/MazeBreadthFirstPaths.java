package lab11.graphs;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /**
     * Conducts a breadth first search of the maze starting at the source.
     */
    private void bfs() {
        Queue<Integer> q = new LinkedList<>();
        if (s == t) {
            targetFound = true;
        }

        if (targetFound) {
            return;
        }
        q.offer(s);
        marked[s] = true;
        while (!q.isEmpty() && !targetFound) {
            int v = q.poll();
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    if (w == t) {
                        targetFound = true;
                    }
                    q.offer(w);
                    if (targetFound) {
                        break;
                    }
                }
            }
            announce();
        }

    }


    @Override
    public void solve() {
        bfs();
    }
}

