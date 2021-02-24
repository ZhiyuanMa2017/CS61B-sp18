import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class Solver {

    private HashMap<Long, Double> estimatedmap = new HashMap<>();
    private Stack<Long> path;

    public class SearchNode implements Comparable<SearchNode> {

        private double movessofar;
        private SearchNode pre;
        private double estimatedD;
        private GraphDB.Node node;

        @Override
        public int compareTo(SearchNode o) {
            return Double.compare((this.movessofar + this.estimatedD),
                    (o.movessofar + o.estimatedD));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            SearchNode that = (SearchNode) o;
            return this.node.getId() == that.node.getId();
        }

        @Override
        public int hashCode() {
            return (int) node.getId();
        }

        public SearchNode(GraphDB.Node node, double sofar, SearchNode pre, GraphDB.Node goal) {
            this.movessofar = sofar;
            this.pre = pre;
            this.node = node;

            if (estimatedmap != null & estimatedmap.containsKey(node.id)) {
                this.estimatedD = estimatedmap.get(node.id);
            } else {
                this.estimatedD = GraphDB.distance(node.lon, node.lat, goal.lon, goal.lat);
                estimatedmap.put(node.id, this.estimatedD);
            }
        }
    }

    public Solver(GraphDB g, GraphDB.Node start, GraphDB.Node goal) {
        SearchNode searchNode = new SearchNode(start, 0, null, goal);
        PriorityQueue<SearchNode> priorityQueue = new PriorityQueue<>();
        Set<Long> marked = new HashSet<>();
        priorityQueue.add(searchNode);
        while (!priorityQueue.isEmpty()) {
            searchNode = priorityQueue.poll();
            if (searchNode.node.id == goal.id) {
                break;
            }

            if (marked.contains(searchNode.node.id)) {
                continue;
            }
            marked.add(searchNode.node.id);

            for (Long id : searchNode.node.adjNode) {
                if (marked.contains(id)) {
                    continue;
                }
                if (searchNode.pre == null || id != searchNode.pre.node.id) {
                    GraphDB.Node thisnode = g.vertices.get(id);
                    priorityQueue.add(new SearchNode(thisnode,
                            searchNode.movessofar + g.distance(id, searchNode.node.id),
                            searchNode, goal));
                }
            }
        }

        path = new Stack<>();
        if (searchNode.node.id == goal.id) {
            while (searchNode != null) {
                path.push(searchNode.node.id);
                searchNode = searchNode.pre;
            }
        }
    }

    public Stack<Long> solution() {
        return path;
    }


}
