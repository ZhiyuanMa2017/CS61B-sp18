public class Dup3 {
    public boolean dup3(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (a[i] == a[j]) {
                    return true;
                }
            }
        }
        return false;
    }

}
