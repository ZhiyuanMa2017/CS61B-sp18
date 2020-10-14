public class SlightlyHarder {
    public static void f4(int N) {
        if (N == 0) {
            return;
        }
        f4(N / 2);
        f4(N / 2);
        f4(N / 2);
        f4(N / 2);
        g(N); // runs in Θ(N^2) time
    }
    // Θ(N^2 * logN)

    public static void f5(int N, int M) {
        if (N < 10) {
            return;
        }
        for (int i = 0; i <= N % 10; i++) {
            f5(N / 10, M / 10);
            System.out.println(M);
        }
    }
    //O(N)

    public static void g(int N) {
    }

}
