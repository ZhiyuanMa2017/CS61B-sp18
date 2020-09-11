public class Add implements ComFunc {
    public int apply(int a, int b) {
        return a + b;
    }
    public static int sumAll(int[] x) { // sumAll is not a member of Combine
        return Combine.combine(new Add(), x);
        // need to pass an  instance of Add
        // create a new Add object!
    }
}