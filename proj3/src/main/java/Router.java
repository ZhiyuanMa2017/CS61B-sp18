import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides a shortestPath method for finding routes between two points
 * on the map. Start by using Dijkstra's, and if your code isn't fast enough for your
 * satisfaction (or the autograder), upgrade your implementation by switching it to A*.
 * Your code will probably not be fast enough to pass the autograder unless you use A*.
 * The difference between A* and Dijkstra's is only a couple of lines of code, and boils
 * down to the priority you use to order your vertices.
 */
public class Router {

    /**
     * Return a List of longs representing the shortest path from the node
     * closest to a start location and the node closest to the destination
     * location.
     * @param g The graph to use.
     * @param stlon The longitude of the start location.
     * @param stlat The latitude of the start location.
     * @param destlon The longitude of the destination location.
     * @param destlat The latitude of the destination location.
     * @return A list of node id's in the order visited on the shortest path.
     */
    public static List<Long> shortestPath(GraphDB g, double stlon, double stlat,
                                          double destlon, double destlat) {
        long start = g.closest(stlon, stlat);
        long des = g.closest(destlon, destlat);

        AStarSolver solver = new AStarSolver(g, GraphDB.Node.of(start, g.lat(start),
                g.lon(start)), GraphDB.Node.of(des, g.lat(des), g.lon(des)));

        Stack<Long> sol = solver.solution();

        if (sol == null || sol.empty()) {
            return new LinkedList<>();
        }

        List<Long> shortestPath = new LinkedList<>(solver.solution());
        Collections.reverse(shortestPath);
        return shortestPath;
    }

    /**
     * Create the list of directions corresponding to a route on the graph.
     * @param g The graph to use.
     * @param route The route to translate into directions. Each element
     *              corresponds to a node from the graph in the route.
     * @return A list of NavigationDirection objects corresponding to the input
     * route.
     */
    public static List<NavigationDirection> routeDirections(GraphDB g, List<Long> route) {
        if (route.size() < 2) {
            return null;
        }

        List<NavigationDirection> nd = new ArrayList<>();
        double curBearing = 0.0;
        int direction = NavigationDirection.START;
        //long startNode = route.get(0);
        String curWay = getWay(g, route.get(0), route.get(1));
        double dis = 0.0;
        for (int i = 1; i < route.size(); ++i) {
            long prevNode = route.get(i - 1);
            long curNode = route.get(i);

            double prevBearing = curBearing;
            curBearing = g.bearing(prevNode, curNode);

            if (g.getWayNames(curNode).contains(curWay)) {
                dis += g.distance(prevNode, curNode);
                continue;
            }

            NavigationDirection turn = new NavigationDirection();
            turn.direction = direction;
            turn.distance = dis;
            turn.way = curWay;
            nd.add(turn);

            direction = getDirection(prevBearing, curBearing);
            //startNode = prevNode;
            dis = g.distance(prevNode, curNode);
            curWay = getWay(g, prevNode, curNode);
        }

        NavigationDirection turn = new NavigationDirection();
        turn.direction = direction;
        turn.distance = dis;
        turn.way = curWay;
        nd.add(turn);

        return nd;
    }

    /**
     * @param g The graph to use
     * @param prevNode
     * @param curNode
     * @return cur way name
     */
    private static String getWay(GraphDB g, long prevNode, long curNode) {
        for (String a : g.getWayNames(prevNode)) {
            for (String b : g.getWayNames(curNode)) {
                if (a.equals(b)) {
                    return a;
                }
            }
        }
        return "";
    }

    /**
     * Calculates what direction we are going based on the two bearings, which
     * are the angles from true north. We compare the angles to see whether
     * we are making a left turn or right turn. Then we can just use the absolute value of the
     * difference to give us the degree of turn (straight, sharp, left, or right).
     * @param prevBearing A double in [0, 360.0]
     * @param currBearing A double in [0, 360.0]
     * @return the Navigation Direction type
     */
    private static int getDirection(double prevBearing, double currBearing) {
        double absDiff = Math.abs(prevBearing - currBearing);
        if (numInRange(absDiff, 0.0, 15.0)) {
            return NavigationDirection.STRAIGHT;
        }
        if ((currBearing > prevBearing && absDiff < 180.0)
                || (currBearing < prevBearing && absDiff > 180.0)) {
            // going right
            if (numInRange(absDiff, 15.0, 30.0) || absDiff > 330.0) {
                return NavigationDirection.SLIGHT_RIGHT;
            } else if (numInRange(absDiff, 30.0, 100.0) || absDiff > 260.0) {
                return NavigationDirection.RIGHT;
            } else {
                return NavigationDirection.SHARP_RIGHT;
            }
        } else {
            // going left
            if (numInRange(absDiff, 15.0, 30.0) || absDiff > 330.0) {
                return NavigationDirection.SLIGHT_LEFT;
            } else if (numInRange(absDiff, 30.0, 100.0) || absDiff > 260.0) {
                return NavigationDirection.LEFT;
            } else {
                return NavigationDirection.SHARP_LEFT;
            }
        }
    }

    /** Checks that a value if between the given ranges. */
    private static boolean numInRange(double value, double from, double to) {
        return value >= from && value <= to;
    }

    /**
     * Class to represent a navigation direction, which consists of 3 attributes:
     * a direction to go, a way, and the distance to travel for.
     */
    public static class NavigationDirection {

        /** Integer constants representing directions. */
        public static final int START = 0;
        public static final int STRAIGHT = 1;
        public static final int SLIGHT_LEFT = 2;
        public static final int SLIGHT_RIGHT = 3;
        public static final int RIGHT = 4;
        public static final int LEFT = 5;
        public static final int SHARP_LEFT = 6;
        public static final int SHARP_RIGHT = 7;

        /** Number of directions supported. */
        public static final int NUM_DIRECTIONS = 8;

        /** A mapping of integer values to directions.*/
        public static final String[] DIRECTIONS = new String[NUM_DIRECTIONS];

        /** Default name for an unknown way. */
        public static final String UNKNOWN_ROAD = "unknown road";
        
        /** Static initializer. */
        static {
            DIRECTIONS[START] = "Start";
            DIRECTIONS[STRAIGHT] = "Go straight";
            DIRECTIONS[SLIGHT_LEFT] = "Slight left";
            DIRECTIONS[SLIGHT_RIGHT] = "Slight right";
            DIRECTIONS[LEFT] = "Turn left";
            DIRECTIONS[RIGHT] = "Turn right";
            DIRECTIONS[SHARP_LEFT] = "Sharp left";
            DIRECTIONS[SHARP_RIGHT] = "Sharp right";
        }

        /** The direction a given NavigationDirection represents.*/
        int direction;
        /** The name of the way I represent. */
        String way;
        /** The distance along this way I represent. */
        double distance;

        /**
         * Create a default, anonymous NavigationDirection.
         */
        public NavigationDirection() {
            this.direction = STRAIGHT;
            this.way = UNKNOWN_ROAD;
            this.distance = 0.0;
        }

        public String toString() {
            return String.format("%s on %s and continue for %.3f miles.",
                    DIRECTIONS[direction], way, distance);
        }

        /**
         * Takes the string representation of a navigation direction and converts it into
         * a Navigation Direction object.
         * @param dirAsString The string representation of the NavigationDirection.
         * @return A NavigationDirection object representing the input string.
         */
        public static NavigationDirection fromString(String dirAsString) {
            String regex = "([a-zA-Z\\s]+) on ([\\w\\s]*) and continue for ([0-9\\.]+) miles\\.";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(dirAsString);
            NavigationDirection nd = new NavigationDirection();
            if (m.matches()) {
                String direction = m.group(1);
                if (direction.equals("Start")) {
                    nd.direction = NavigationDirection.START;
                } else if (direction.equals("Go straight")) {
                    nd.direction = NavigationDirection.STRAIGHT;
                } else if (direction.equals("Slight left")) {
                    nd.direction = NavigationDirection.SLIGHT_LEFT;
                } else if (direction.equals("Slight right")) {
                    nd.direction = NavigationDirection.SLIGHT_RIGHT;
                } else if (direction.equals("Turn right")) {
                    nd.direction = NavigationDirection.RIGHT;
                } else if (direction.equals("Turn left")) {
                    nd.direction = NavigationDirection.LEFT;
                } else if (direction.equals("Sharp left")) {
                    nd.direction = NavigationDirection.SHARP_LEFT;
                } else if (direction.equals("Sharp right")) {
                    nd.direction = NavigationDirection.SHARP_RIGHT;
                } else {
                    return null;
                }

                nd.way = m.group(2);
                try {
                    nd.distance = Double.parseDouble(m.group(3));
                } catch (NumberFormatException e) {
                    return null;
                }
                return nd;
            } else {
                // not a valid nd
                return null;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof NavigationDirection) {
                return direction == ((NavigationDirection) o).direction
                    && way.equals(((NavigationDirection) o).way)
                    && distance == ((NavigationDirection) o).distance;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(direction, way, distance);
        }
    }
}
