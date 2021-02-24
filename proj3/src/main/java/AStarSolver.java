import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Stack;
import java.util.PriorityQueue;

public class AStarSolver {
    private PriorityQueue<AugmentNode> pq = new PriorityQueue<>();
    private Stack<Long> path = new Stack<>();
    private Map<Long, Double> estimateDisCach = new HashMap<>();
    private Set<Long> visited = new HashSet<>();

    private class AugmentNode implements Comparable<AugmentNode> {

        private GraphDB.Node node;
        private AugmentNode prev;
        private double priority;
        private double disToCur;
        private double estimateDis;

        @Override
        public int compareTo(AugmentNode n) {
            return Double.compare(this.priority, n.priority);
        }

        private AugmentNode(GraphDB.Node node, double disToCur,
                            AugmentNode prev, GraphDB.Node goal) {
            this.disToCur = disToCur;
            this.node = node;
            this.prev = prev;
            if (estimateDisCach.containsKey(node.id())) {
                this.estimateDis = estimateDisCach.get(node.id());
            } else {
                this.estimateDis = GraphDB.distance(node.lon(), node.lat(), goal.lon(), goal.lat());
                estimateDisCach.put(node.id(), this.estimateDis);
            }
            this.priority = this.estimateDis + disToCur;
        }
    }

    public AStarSolver(GraphDB g, GraphDB.Node initial, GraphDB.Node goal) {

        AugmentNode curNode = new AugmentNode(initial, 0, null, goal);
        pq.add(curNode);

        while (!pq.isEmpty()) {
            curNode = pq.poll();
            if (visited.contains(curNode.node.id())) {
                continue;
            }
            if (curNode.node.id() == goal.id()) {
                break;
            }
            visited.add(curNode.node.id());
            for (long neighbor : g.adjacent(curNode.node.id())) {
                GraphDB.Node n = g.vertex.get(neighbor);
                if (visited.contains(n.id())) {
                    continue;
                }
                if (curNode.prev == null || n.id() != curNode.prev.node.id()) {
                    AugmentNode node = new AugmentNode(n, curNode.disToCur
                            + g.distance(curNode.node.id(), n.id()), curNode, goal);
                    pq.add(node);
                }
            }
        }

        if (curNode.node.id() == goal.id()) {
            for (AugmentNode n = curNode; n != null; n = n.prev) {
                path.push(n.node.id());
            }
        }
    }

    public Stack<Long> solution() {
        return path;
    }
}
