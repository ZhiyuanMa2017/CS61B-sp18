package Map61B;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

/**
 * Class to demonstrate how generic methods work in Java.
 */
public class MapHelper {
    /* Write the following three methods:

    /* get(Key) : Return item in map if it exists. */
    public static <K, V> V get(Map61B<K, V> sim, K key) {
        // <K, V> is type parameter definitions
        // specification of generic types for methods (before return type)
        if (sim.containsKey(key)) {
            return sim.get(key);
        }
        return null;
    }
    /* maxKey() : Returns max of all keys. Works only if x and y have comparable data. */
    public static <K extends Comparable<K>, V> K maxKey(Map61B<K, V> map) {
        // Any ArrayMap you give must have actual parameter type that is a subtype of Comparable<T>
        // Here extends is being used as a statement of fact, not a definition of K.
        // It's like 'is a subtype of'
        List<K> keylist = map.keys();
        K largest = keylist.get(0);
        for (K k : keylist) {
            if (k.compareTo(largest) > 0) {
                largest = k;
            }
        }
        return largest;
        }

    @Test
    public void testGet() {
        Map61B<String, Integer> m = new ArrayMap<String, Integer>();
        m.put("horse", 3);
        m.put("fish", 9);
        m.put("house", 10);

        Integer actual = MapHelper.get(m, "fish");
        Integer expected = 9;
        assertEquals(expected, actual);
        assertEquals(null, MapHelper.get(m, "dwqdqwdqwqd"));
    }

    @Test
    public void testMaxKey() {
        Map61B<String, Integer> m = new ArrayMap<String, Integer>();
        m.put("horse", 3);
        m.put("fish", 9);
        m.put("house", 10);

        String actual = MapHelper.maxKey(m);
        String expected = "house";
        assertEquals(expected, actual);
    }
}
