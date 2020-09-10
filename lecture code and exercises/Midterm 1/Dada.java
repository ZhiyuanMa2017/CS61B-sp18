public class Dada {
    private static String[] rs;

    /** Prints out the given array, i.e. if d contains two Strings
     * with names "alice" and "bob", this method will print "alice bob ".
     */
    private static void printStringArray(String[] s) {
        for (int i = 0; i < s.length; i += 1) {
            System.out.print(s[i] + " "); }
        System.out.println();
    }
//    public static void main(String[] args) {
//        String a = "alice";
//        String b = "bob";
//        String c = "carol";
//        String d = "dan";
//        String[][] twod = {{a, b}, {c, d}};
//        String[] X = twod[1];// x = {c, d}
//        printStringArray(X); // carol dan
//        Dada.rs = X;// rs = {c, d}
//        String[] Y = Dada.rs;
//        Y = new String[]{d, c};// Y = {d, c}
//        Dada.rs[1] = "eve";// rs = {c, "eve"}
//        printStringArray(Dada.rs);// carol eve
//        printStringArray(Y);// dan carol
//        printStringArray(twod[0]); //alice bob
//        printStringArray(twod[1]); //carol eve
//    }

    private static void fillMany(String[] d) {
        System.arraycopy(Dada.rs, 0, d, 0, d.length);
    }
    private static void fillOne(String d) {
        d = Dada.rs[0];
    }
    public static void main(String[] args) {
        String a = "alice";
        String b = "bob";
        String c = "carol";
        String d = "dan";
        String[][] twod = {{a, b}, {c, d}};
        Dada.rs = new String[]{"fritz", "gritz"};
        String[] X = twod[0];
        printStringArray(X); // alice bob
        fillOne(X[0]);// X[0] = Dada.rs[0] x={fritz, bob}
        printStringArray(X); // fritz bob WRONG!!! the correct answer is alice bob
        fillMany(X);
        printStringArray(X); // fritz gritz
    }
}