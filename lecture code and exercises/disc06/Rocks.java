public class Rocks {
    public final Rock[] rocks;

    public Rocks(Rock[] rox) {
        rocks = rox;
    }
}
// Though rocks cannot be reassigned, we can still change what the array holds and
// thus Rocks is mutable.
