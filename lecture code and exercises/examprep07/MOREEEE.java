public class MOREEEE {
    public static void p1(int N) {
        for (int i = 0; i < N; i += 1) {
            for (int j = 1; j < N; j = j + 2) {
                System.out.println("hi !");
            }
        }
    }
    // Θ(N^2)

    public static void p2(int N) {
        for (int i = 0; i < N; i += 1) {
            for (int j = 1; j < N; j = j * 2) {
                System.out.println("hi !");
            }
        }
    }
    // Θ(NlogN)

    public static void p3(int N) {
        if (N <= 1) return;
        p3(N / 2);
        p3(N / 2);
    }
    // Θ(N)

    public static void p4(int N) {
        int m = (int) ((15 + Math.round(3.2 / 2)) *
                (Math.floor(10 / 5.5) / 2.5) * Math.pow(2, 5));
        for (int i = 0; i < m; i++) {
            System.out.println("hi");
        }
    }
    // Θ(1)

    public static void p5(int N) {
        for (int i = 1; i <= N * N; i *= 2) {
            for (int j = 0; j < i; j++) {
                System.out.println("moo");
            }
        }
    }
    // Θ(N^2)
}
