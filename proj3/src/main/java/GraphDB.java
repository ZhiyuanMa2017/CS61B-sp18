import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Graph for storing all of the intersection (vertex) and road (edge) information.
 * Uses your GraphBuildingHandler to convert the XML files into a graph. Your
 * code must include the vertices, adjacent, distance, closest, lat, and lon
 * methods. You'll also need to include instance variables and methods for
 * modifying the graph (e.g. addNode and addEdge).
 *
 * @author Alan Yao, Josh Hug
 */
public class GraphDB {
    /** Your instance variables for storing the graph. You should consider
     * creating helper classes, e.g. Node, Edge, etc. */

    protected static class Node {  // protected for the use of GraphBuildingHandler
        private long id;
        private double lon;
        private double lat;
        private String name;

        Set<String> wayNames;

        private Node(long id, double lat, double lon) {
            this.id = id;
            this.lat = lat;
            this.lon = lon;
            wayNames = new HashSet<>();
        }

        public static Node of(long id, double lat, double lon) {
            return new Node(id, lat, lon);
        }

        public long id() {
            return id;
        }

        public double lat() {
            return lat;
        }

        public double lon() {
            return lon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static class Edge {
        private long fromID;
        private long toID;
        private double weight;
        private String name;

        protected Edge(long fromID, long toID, String name) {
            this.fromID = fromID;
            this.toID = toID;
            this.name = name;
        }

        public String name() {
            return name;
        }
    }

    Map<Long, Node> vertex = new HashMap<>();
    Map<Long, Set<Edge>> adj = new HashMap<>(); // node id -> neighbor edges
    private static Trie trie = new Trie();

    /**
     * Example constructor shows how to create and start an XML parser.
     * You do not need to modify this constructor, but you're welcome to do so.
     * @param dbPath Path to the XML file to be parsed.
     */
    public GraphDB(String dbPath) {
        try {
            File inputFile = new File(dbPath);
            FileInputStream inputStream = new FileInputStream(inputFile);
            // GZIPInputStream stream = new GZIPInputStream(inputStream);

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            GraphBuildingHandler gbh = new GraphBuildingHandler(this);
            saxParser.parse(inputStream, gbh);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        clean();
    }

    /**
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

    /**
     *  Remove nodes with no connections from the graph.
     *  While this does not guarantee that any two nodes in the remaining graph are connected,
     *  we can reasonably assume this since typically roads are connected.
     */
    private void clean() {
        // cannot iterate through and modify simultaneously
        List<Long> toRemove = new ArrayList<>();
        for (long id : vertex.keySet()) {
            if (adj.get(id).size() == 0) {
                toRemove.add(id);
            }
        }

        for (long id : toRemove) {
            vertex.remove(id);
            adj.remove(id);
        }
    }

    /**
     * Returns an iterable of all vertex IDs in the graph.
     * @return An iterable of id's of all vertices in the graph.
     */
    Iterable<Long> vertices() {
        return vertex.keySet();
    }

    /**
     * Returns ids of all vertices adjacent to v.
     * @param v The id of the vertex we are looking adjacent to.
     * @return An iterable of the ids of the neighbors of v.
     */
    Iterable<Long> adjacent(long v) {
        List<Long> adjVertex = new ArrayList<>();
        Set<Edge> edges = adj.get(v);
        for (Edge edge : edges) {
            adjVertex.add(edge.toID);
        }
        return adjVertex;
    }

    /**
     * Returns the great-circle distance between vertices v and w in miles.
     * Assumes the lon/lat methods are implemented properly.
     * <a href="https://www.movable-type.co.uk/scripts/latlong.html">Source</a>.
     * @param v The id of the first vertex.
     * @param w The id of the second vertex.
     * @return The great-circle distance between the two locations from the graph.
     */
    double distance(long v, long w) {
        return distance(lon(v), lat(v), lon(w), lat(w));
    }

    static double distance(double lonV, double latV, double lonW, double latW) {
        double phi1 = Math.toRadians(latV);
        double phi2 = Math.toRadians(latW);
        double dphi = Math.toRadians(latW - latV);
        double dlambda = Math.toRadians(lonW - lonV);

        double a = Math.sin(dphi / 2.0) * Math.sin(dphi / 2.0);
        a += Math.cos(phi1) * Math.cos(phi2) * Math.sin(dlambda / 2.0) * Math.sin(dlambda / 2.0);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 3963 * c;
    }

    /**
     * Returns the initial bearing (angle) between vertices v and w in degrees.
     * The initial bearing is the angle that, if followed in a straight line
     * along a great-circle arc from the starting point, would take you to the
     * end point.
     * Assumes the lon/lat methods are implemented properly.
     * <a href="https://www.movable-type.co.uk/scripts/latlong.html">Source</a>.
     * @param v The id of the first vertex.
     * @param w The id of the second vertex.
     * @return The initial bearing between the vertices.
     */
    public double bearing(long v, long w) {
        return bearing(lon(v), lat(v), lon(w), lat(w));
    }

    public static double bearing(double lonV, double latV, double lonW, double latW) {
        double phi1 = Math.toRadians(latV);
        double phi2 = Math.toRadians(latW);
        double lambda1 = Math.toRadians(lonV);
        double lambda2 = Math.toRadians(lonW);

        double y = Math.sin(lambda2 - lambda1) * Math.cos(phi2);
        double x = Math.cos(phi1) * Math.sin(phi2);
        x -= Math.sin(phi1) * Math.cos(phi2) * Math.cos(lambda2 - lambda1);
        return Math.toDegrees(Math.atan2(y, x));
    }

    /**
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target. O(lgn)
     */
    long closest(double lon, double lat) {
        double minDis = Double.MAX_VALUE;
        long cloestID = 0;
        for (Node node : vertex.values()) {
            double curDis = distance(node.lon, node.lat, lon, lat);
            if (curDis < minDis) {
                minDis = curDis;
                cloestID = node.id;
            }
        }
        return cloestID;
    }

    /**
     * Gets the longitude of a vertex.
     * @param v The id of the vertex.
     * @return The longitude of the vertex.
     */
    public double lon(long v) {
        if (!vertex.containsKey(v)) {
            return 0.0;
        }
        return vertex.get(v).lon;
    }

    /**
     * Gets the latitude of a vertex.
     * @param v The id of the vertex.
     * @return The latitude of the vertex.
     */
    public double lat(long v) {
        if (!vertex.containsKey(v)) {
            return 0.0;
        }
        return vertex.get(v).lat;
    }

    public void addNode(Node v) {
        if (!vertex.containsKey(v.id())) {
            vertex.put(v.id(), v);
            adj.put(v.id(), new HashSet<>());
        }
    }

  /*  public Set<String> getWayNames(long v) {
        Set<String> wayNames = new HashSet<>();
        for (Edge e : adj.get(v)) {
            wayNames.add(e.name);
        }
        return wayNames;
    }*/

    public ArrayList<String> getWayNames(long v) {
        ArrayList<String> wayNames = new ArrayList<>();
        for (Edge e : adj.get(v)) {
            if (e.name() == null) {
                wayNames.add(Router.NavigationDirection.UNKNOWN_ROAD);
            }
            else {
                wayNames.add(e.name());
            }
        }
        return wayNames;
    }

    public void addEdge(long fromID, long toID, String name) {
        if (vertex.containsKey(fromID) && vertex.containsKey(toID)) {
            Edge edge = new Edge(fromID, toID, name);
            edge.weight = distance(fromID, toID);

            Set<Edge> edges = adj.get(fromID);
            edges.add(edge);
            adj.put(fromID, edges);
        }
    }

    void addNameToTrie(String name, long id, double lat, double lon) {
        trie.insert(name, id, lat, lon);
    }

    public List<String> getLocationsByPrefix(String prefix) {
        List<String> locations = new ArrayList<>();
        // do not need to iterate all the node, just go through the trie, O(k)
        Trie.TrieNode node = trie.startsWith(cleanString(prefix));
        if (node == null) {
            return locations;
        }
        else {
            dfs(node, prefix, "", locations);
        }

        return locations;
    }

    public static void dfs(Trie.TrieNode node, String prefix, String cur, List<String> ans) {
        if (node.isWord) {
            for (Map<String, Object> m : node.extraInfo) {
                ans.add((String) m.get("name"));
            }
        }
        if (node.children.isEmpty()) {
            return;
        }

        for (Map.Entry<Character, Trie.TrieNode> entry : node.children.entrySet()) {
            dfs(entry.getValue(), prefix, cur + entry.getKey(), ans);
        }
    }

    public List<Map<String, Object>> getLocations(String locationName) {
        // O(k) do not iterate all the node
        List<Map<String, Object>> ans = new ArrayList<>();
        locationName = cleanString(locationName);
        if (trie.search(locationName)) {
            return trie.startsWith(locationName).extraInfo;
        }
        return ans;
    }
}
