public class Rock {
    public final int weight;

    public Rock(int w) {
        weight = w;
    }
}
// This class is immutable. Rock’s weight field is final,
// so it cannot be reassigned once a rock is initialized.
