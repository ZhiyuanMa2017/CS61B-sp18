import java.util.PriorityQueue;

public class ZorkSort {
    public static void zorkSort(int[] A, int k) {
        int i;
        int n = A.length;
        i = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (i < k) {
            pq.add(A[i]);
            i++;
        }
        while (i < n) {
            A[i - k] = pq.remove();
            i++;
        }
        while (!pq.isEmpty()) {
            A[i - k] = pq.remove();
            i++;
        }
    }
}
