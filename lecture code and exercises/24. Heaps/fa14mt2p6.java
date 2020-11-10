import java.util.PriorityQueue;

public class fa14mt2p6 {
    public static void zorkSort(int[] A, int k) {
        int i;
        int n = A.length;
        i = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (i < k) {
            pq.add(A[i]);
            i += 1;
        }
        while (i < n) {
            A[i - k] = pq.remove();
            pq.add(A[i]);
            i += 1;
        }
        while (!pq.isEmpty()) {
            A[i - k] = pq.remove();
            i += 1;
        }
    }
}
