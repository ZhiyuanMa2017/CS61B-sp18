import java.util.Iterator;
import java.util.Stack;

public class Queue<Item> {
    public Stack<Item> a;
    public Stack<Item> b;
// push(E e) which adds an element,
// peek() which returns the next element
// poll() which returns and removes the next element
    public void push(Item item) {
        a.push(item);
    }
    public Item poll() {
        while(!a.empty()) {
            b.push(a.pop());
        }
        Item returnval =  b.pop();
        while(!b.empty()) {
            a.push(b.pop());
        }
        return returnval;
    }
}
