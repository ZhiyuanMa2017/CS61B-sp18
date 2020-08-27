public class Lists1Exercises {
    /** Returns an IntList identical to L, but with
      * each element incremented by x. L is not allowed
      * to change. */
    public static IntList incrList(IntList L, int x) {
        IntList Q = new IntList(0,null);
        Q.first = L.first + x;
        Q.rest = new IntList(0,null);
        IntList QQ = Q;
        while(L.rest != null){
            L = L.rest;
            QQ.rest = new IntList(0,null);
            QQ = QQ.rest;
            QQ.first = L.first + x;

        }
        return Q;        
    }

    /** Returns an IntList identical to L, but with
      * each element incremented by x. Not allowed to use
      * the 'new' keyword. */
    public static IntList dincrList(IntList L, int x) {
        IntList Q = L;
        Q.first = L.first + x;
        IntList QQ = Q;
        while(L.rest != null){
            L = L.rest;
            QQ = QQ.rest;
            QQ.first = L.first + x;

        }
        return Q;   
    }

    public static void main(String[] args) {
        IntList L = new IntList(5, null);
        L.rest = new IntList(7, null);
        L.rest.rest = new IntList(9, null);

        System.out.println(L.size());
        System.out.println(L.iterativeSize());

        // Test your answers by uncommenting. Or copy and paste the
        // code for incrList and dincrList into IntList.java and
        // run it in the visualizer.
        System.out.println(L.get(1));
        System.out.println(incrList(L, 3));
        System.out.println(dincrList(L, 3));        
    }
}