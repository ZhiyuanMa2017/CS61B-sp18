public class Widening {
    public static void blahDouble(double x) {
        System.out.println("double: " + x);
    }

    public static void blahInt(int y) {
        System.out.println("double: " + y);
    }

    public static void main(String[] args) {
        int x = 20;
        blahDouble(x);
        // move from a primitive type with a narrower range to a wider range
        // widened
        // double is wider than int

        double y = 20;
        blahInt((int) y);
        // move from a wider tyoe ta narrower type
        // must use casting

    }
}
