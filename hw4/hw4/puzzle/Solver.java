package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;


import java.util.HashMap;

public class Solver {
    private int move;
    private Stack<WorldState> al;
    private HashMap<WorldState, Integer> mp = new HashMap<>();

    public class SearchNode implements Comparable<SearchNode> {
        private WorldState ws;
        private int movessofar;
        private SearchNode pre;
        private int estimatedD;

        @Override
        public int compareTo(SearchNode o) {
            return (this.movessofar + this.estimatedD) - (o.movessofar + o.estimatedD);
        }

        public SearchNode(WorldState ws, int sofar, SearchNode pre) {
            this.ws = ws;
            this.movessofar = sofar;
            this.pre = pre;
            if (mp != null & mp.containsKey(ws)) {
                this.estimatedD = mp.get(ws);
            } else {
                this.estimatedD = ws.estimatedDistanceToGoal();
                mp.put(ws, estimatedD);
            }
        }
    }

    public Solver(WorldState initial) {
        SearchNode sn = new SearchNode(initial, 0, null);
        MinPQ<SearchNode> pq = new MinPQ<>();
        while (!sn.ws.isGoal()) {
            for (WorldState neighbor : sn.ws.neighbors()) {
                if (sn.pre == null || !neighbor.equals(sn.pre.ws)) {
                    pq.insert(new SearchNode(neighbor, sn.movessofar + 1, sn));
                }
            }
            sn = pq.delMin();
        }
        move = sn.movessofar;
        al = new Stack<>();
        while (sn != null) {
            al.push(sn.ws);
            sn = sn.pre;
        }
    }

    public int moves() {
        return move;
    }

    public Iterable<WorldState> solution() {
        return al;
    }
}
