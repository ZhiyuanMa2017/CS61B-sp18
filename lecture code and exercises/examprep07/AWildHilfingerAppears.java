public class AWildHilfingerAppears {

    public int p1(int M) {
        return r(0, M);
    }
    // Θ(N^2)

    public int r(int i, int M) {
        if (i >= M) return 0;
        if (s(i) > 0) return i;
        return r(i + 1, M);
    }

    public int s(int k) {
        if (k <= 0) return 0;
        if (h1(k)) return k;
        return s(k - 1);
    }

    public boolean h1(int k) {
        return false;
    }



    void p2(int M) {
        int L, U;
        for (L = U = 0; U < M; L += 1, U += 2) {
            for (int i = L; i < U; i += 1) {
                h2(i);
            }
        }
    }
    // Θ(N^2)

    public int h2(int k) {
        return 0;
    }
}
