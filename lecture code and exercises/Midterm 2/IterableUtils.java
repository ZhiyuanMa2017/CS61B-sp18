import java.util.ArrayList;
import java.util.List;

public class IterableUtils {
    public static <T> List<T> toList(Iterable<T> iterable) {
        ArrayList<T> al = new ArrayList<>();
        for (T t : iterable) {
            if (t == null) {
                throw new IllegalArgumentException();
            }
            al.add(t);
        }
        return al;
    }
} // assume any classes you need from java.util have been imported
