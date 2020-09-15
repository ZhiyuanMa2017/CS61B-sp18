import java.util.LinkedList;

public class DelegationStack<Item> {
    private LinkedList<Item> L = new LinkedList<>();
    public void push(Item x) {
        L.add(x);
    }

}
