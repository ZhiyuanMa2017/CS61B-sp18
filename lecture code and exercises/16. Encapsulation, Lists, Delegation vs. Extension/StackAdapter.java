import java.util.List;

public class StackAdapter<Item> {
    private List L;
    public StackAdapter(List<Item> worker) {
        L = worker;
    }
    public void push(Item x) {
        L.add(x);
    }
}
