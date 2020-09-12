import java.util.ArrayList;

public class BasicArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> L = new ArrayList<>();
        // can not use int !!!
        // required: reference type!!!
        L.add(5);
        L.add(6); // autoboxed
        int first = L.get(0); // auto unboxed
    }
    // 8 primitive types | corresponding reference type called wrapper class
    //      boolean             Boolean
    //      char                Character
    //      byte                Byte
    //      short               Short
    //      int                 Integer
    //      long                Long
    //      float               Float
    //      double              Double
}
