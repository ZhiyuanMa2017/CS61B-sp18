import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


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
    /**
     * Your instance variables for storing the graph. You should consider
     * creating helper classes, e.g. Node, Edge, etc.
     */
    Map<Long, Node> vertices = new LinkedHashMap<>();
    private final Map<Long, Edge> edges = new LinkedHashMap<>();
    private final Map<String, Long> path = new LinkedHashMap<>();
    private Trie trie = new Trie();

    static class Node {
        long id;
        double lat;
        double lon;
        List<Long> adjNode = new ArrayList<>();
        String name;

        Node(long id, double lat, double lon) {
            this.id = id;
            this.lat = lat;
            this.lon = lon;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getId() {
            return id;
        }


    }

    static class Edge {
        long id;
        List<Long> vertexList;
        String name;


        Edge(long id, List<Long> vertexList) {
            this.id = id;
            this.vertexList = vertexList;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * Example constructor shows how to create and start an XML parser.
     * You do not need to modify this constructor, but you're welcome to do so.
     *
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
     *
     * @param s Input string.
     * @return Cleaned string.
     */
    static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

    /**
     * Remove nodes with no connections from the graph.
     * While this does not guarantee that any two nodes in the remaining graph are connected,
     * we can reasonably assume this since typically roads are connected.
     */
    private void clean() {
        Map<Long, Node> aftervertices = new LinkedHashMap<>();
        for (Node node : vertices.values()) {
            if (node.adjNode.size() != 0) {
                aftervertices.put(node.id, node);
            }
        }
        vertices = aftervertices;
    }

    /**
     * Returns an iterable of all vertex IDs in the graph.
     *
     * @return An iterable of id's of all vertices in the graph.
     */
    Iterable<Long> vertices() {
        return vertices.keySet();
    }

    /**
     * Returns ids of all vertices adjacent to v.
     *
     * @param v The id of the vertex we are looking adjacent to.
     * @return An iterable of the ids of the neighbors of v.
     */
    Iterable<Long> adjacent(long v) {
        List<Long> adjnodes = new ArrayList<>();
        Node thisnode = vertices.get(v);
        for (Long aLong : thisnode.adjNode) {
            adjnodes.add(aLong);
        }
        return adjnodes;
    }

    /**
     * Returns the great-circle distance between vertices v and w in miles.
     * Assumes the lon/lat methods are implemented properly.
     * <a href="https://www.movable-type.co.uk/scripts/latlong.html">Source</a>.
     *
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
     *
     * @param v The id of the first vertex.
     * @param w The id of the second vertex.
     * @return The initial bearing between the vertices.
     */
    double bearing(long v, long w) {
        return bearing(lon(v), lat(v), lon(w), lat(w));
    }

    static double bearing(double lonV, double latV, double lonW, double latW) {
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
     *
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    long closest(double lon, double lat) {
        double mindis = Double.MAX_VALUE;
        double curdis;
        long minid = 0;
        for (Long id : vertices.keySet()) {
            curdis = distance(lon, lat, vertices.get(id).lon, vertices.get(id).lat);
            if (curdis < mindis) {
                mindis = curdis;
                minid = id;
            }
        }
        return minid;
    }

    /**
     * Gets the longitude of a vertex.
     *
     * @param v The id of the vertex.
     * @return The longitude of the vertex.
     */
    double lon(long v) {
        if (vertices.containsKey(v)) {
            return vertices.get(v).lon;
        } else {
            return 0;
        }
    }

    /**
     * Gets the latitude of a vertex.
     *
     * @param v The id of the vertex.
     * @return The latitude of the vertex.
     */
    double lat(long v) {
        if (vertices.containsKey(v)) {
            return vertices.get(v).lat;
        } else {
            return 0;
        }
    }

    void addNode(long id, double lat, double lon) {
        vertices.put(id, new Node(id, lat, lon));
    }


    void addEdge(long id, List<Long> vertexList) {
        edges.put(id, new Edge(id, vertexList));
        for (int i = 0; i < vertexList.size() - 1; i++) {
            long firstid = vertexList.get(i);
            long secondid = vertexList.get(i + 1);
            Node firstnode = vertices.get(firstid);
            Node secondnode = vertices.get(secondid);
            firstnode.adjNode.add(secondid);
            secondnode.adjNode.add(firstid);
            path.put(firstid + "to" + secondid, id);
            path.put(secondid + "to" + firstid, id);
        }
    }

    void setNodename(long id, String name) {
        vertices.get(id).setName(name);
        trie.put(id, name, vertices.get(id).lat, vertices.get(id).lon);

    }

    void setWayname(long id, String name) {
        edges.get(id).setName(name);
    }

    Long findWayid(Long v, Long w) {
        String name1 = v + "to" + w;
        if (path.containsKey(name1)) {
            return path.get(name1);
        } else {
            return (long) -1;
        }
    }

    String getWayname(Long id) {
        if (edges.containsKey(id)) {
            if (edges.get(id).name != null) {
                return edges.get(id).name;
            }
        }
        return Router.NavigationDirection.UNKNOWN_ROAD;
    }

    List<String> getnodenamesByPrefix(String prefix) {
        return trie.keysWithPrefix(prefix);
    }

    List<Map<String, Object>> getlocations(String name) {
        List<Map<String, Object>> res = new ArrayList<>();
        String key = cleanString(name);
        if (trie.contains(key)) {
            res = trie.getnodeinfo(key);
        }
        return res;
    }


}
