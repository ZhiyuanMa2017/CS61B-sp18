import java.util.ArrayList;

public interface Stack<Item> {
    void push(Item x); // places an item on “top” of the stack
    Item pop(); // removes and returns “top” item of the stack
    int size(); // returns the number of items on the stack

    public default void purge(Item x) {
        if (size()==0) {
            return;
        }
        ArrayStack<Item> temp = new ArrayStack<>();
        while(size() != 0) {
            Item tmp = pop();
            if(!tmp.equals(x)) {
                temp.push(x);
            }
        }
        while(temp.size() != 0) {
            push(temp.pop());
        }
    }
}
