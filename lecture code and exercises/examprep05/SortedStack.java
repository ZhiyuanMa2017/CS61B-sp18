import java.util.Stack;

public class SortedStack<Integer extends Comparable<Integer>>{
    public Stack<Integer> a;
    public Stack<Integer> b;
    public void push(Integer i) {
        while (!a.empty() && a.peek().compareTo(i) < 0) {
            b.push(a.pop());
        }
        a.push(i);
        while (!b.empty()) {
            a.push(b.pop());
        }
    }
    public Integer poll() {
        return a.pop();
    }

}
