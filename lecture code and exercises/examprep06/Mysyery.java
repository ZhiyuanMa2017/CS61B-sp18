public class Mysyery {
    public static void checkIfZero(int x) throws Exception {
        if (x == 0) {
        throw new Exception("x was zero!");
    }
        System.out.println(x); // PRINT STATEMENT
}

    public static int mystery(int x) {
        int counter = 0;
        try {
            while (true) {
                x = x / 2;
                checkIfZero(x);
                counter += 1;
                System.out.println("counter is " + counter); // PRINT STATEMENT
            }
        } catch (Exception e) {
            return counter;
        }
    }

    public static void main(String[] args) {
        System.out.println("mystery of 1 is " + mystery(1));
        System.out.println("mystery of 6 is " + mystery(6));
    }
}
