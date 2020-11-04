public class AsymptoticAnalysis {

    public void andslam(int N) {
        if (N > 0) {
            for (int i = 0; i < N; i += 1) {
                System.out.println("datboi.jpg");
            }
            andslam(N / 2);
        }
    }

    public static void andwelcome(int[] arr, int low, int high) {
        System.out.print("[ ");
        for (int i = low; i < high; i += 1) {
            System.out.print("loyal ");
        }
        System.out.println("]");
        if (high - low > 0) {
            double coin = Math.random();
            if (coin > 0.5) {
                andwelcome(arr, low, low + (high - low) / 2);
            } else {
                andwelcome(arr, low, low + (high - low) / 2);
                andwelcome(arr, low + (high - low) / 2, high);
            }
        }
    }

    public int tothe(int N) {
        if (N <= 1) {
            return N;
        }
        return tothe(N - 1) + tothe(N - 1);
    }

    public static void spacejam(int N) {
        if (N <= 1) {
            return;
        }
        for (int i = 0; i < N; i += 1) {
            spacejam(N - 1);
        }
    }
}

