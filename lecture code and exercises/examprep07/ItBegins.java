public class ItBegins {
    public static void f1(int N) { //Desired Runtime: Θ(N)
        for (int i = 1; i < N; i++) {
            System.out.println("hi");
        }
    }

    public static void f2(int N) { //Desired Runtime: Θ(logN)
        for (int i = 1; i < N; i = i * 2) {
            System.out.println("hi");
        }
    }

    public static void f3(int N) { //Desired Runtime: Θ(1)
        for (int i = 1; i < N; i = i + N) {
            System.out.println("hi");
        }
    }
}
