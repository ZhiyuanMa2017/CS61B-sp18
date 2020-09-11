public class WhatItDo {
    public static int f(int x) {
        if (x == 1) {
            return 1;
        }
        return 2 * f(x / 2);
        //For x >= 1, prints out the largest power of 2 that is smaller than x. For x < 1, goes into infinite loop.
    }

    public static void g(IntList x) {
        if (x == null) {
            return;
        }
        g(x.rest);
        x.rest = x;
        //https://sp18.datastructur.es/materials/exam/cs61b-sp18-mt1-solutionv2.pdf#page=5
    }

    public static void main(String[] args) {
        IntList L = IntList.of(3, 4, 5); //creates an IntList containing 3, 4, and 5
        g(L);
    }
}
