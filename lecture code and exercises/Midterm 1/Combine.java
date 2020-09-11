public class Combine {
    public static int combine(ComFunc f, int[] x) {
        if (x.length == 0) {
            return 0;
        }
        if (x.length == 1){
            return x[0];
        }
        int result = f.apply(x[0], x[1]);
        return combinehelper(f, x, result ,2);

    }
    // your private helper function cannot create new arrays (too slow)
    private static int combinehelper(ComFunc f, int[] x, int result, int numbercounted) {
        if (numbercounted == x.length) {
            return result;
        }
        result = f.apply(result, x[numbercounted]);
        return combinehelper(f, x, result, numbercounted+1);
    }
}

