public class WhatItDo {
    public static int f(int x) {
        if (x == 1) {
            return 1;
        }
        return 2 * f(x / 2);
    }

    public static void g(IntList x) {
        if (x == null) { return; }
        g(x.rest);
        x.rest = x;
    }

    public static void main(String[] args) {
        IntList L = IntList.of(3, 4, 5); //creates an IntList containing 3, 4, and 5
        g(L);
    }



    private static class IntList {
        public int first;
        public IntList rest;
        public IntList (int f, IntList r) {
            this.first = f;
            this.rest = r;
        }

        public static IntList of(Integer... args) {
            IntList result, p;

            if (args.length > 0) {
                result = new IntList(args[0], null);
            } else {
                return null;
            }

            int k;
            for (k = 1, p = result; k < args.length; k += 1, p = p.rest) {
                p.rest = new IntList(args[k], null);
            }
            return result;
        }
    }
}
