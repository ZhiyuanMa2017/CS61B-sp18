import java.util.HashSet;

public class UnionAndIntersect {
    public int[] union(int[] A, int[] B) {
        HashSet<Integer> tempset = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            tempset.add(A[i]);
        }
        for (int i = 0; i < B.length; i++) {
            tempset.add(B[i]);
        }
        int[] C = new int[tempset.size()];
        int i = 0;
        for (Integer integer : tempset) {
            C[i] = integer;
            i += 1;
        }
        return C;
    }

    public int[] intersect(int[] A, int[] B) {
        HashSet<Integer> tempset = new HashSet<>();
        HashSet<Integer> tempset2 = new HashSet<>();

        for (int i = 0; i < A.length; i++) {
            tempset.add(A[i]);
        }
        for (int i = 0; i < B.length; i++) {
            if (tempset.contains(B[i])) {
                tempset2.add(B[i]);
            }
        }
        int[] C = new int[tempset2.size()];
        int i = 0;
        for (Integer integer : tempset2) {
            C[i] = integer;
            i += 1;
        }
        return C;
    }



}
