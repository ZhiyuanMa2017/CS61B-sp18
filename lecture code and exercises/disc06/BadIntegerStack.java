public class BadIntegerStack {
    public class Node {
        public Integer value;
        public Node prev;

        public Node(Integer v, Node p) {
            value = v;
            prev = p;
        }
    }

    private Node top;

    public boolean isEmpty() {
        return top == null;
    }

    public void push(Integer num) {
        top = new Node(num, top);
    }

    public Integer pop() {
        if (isEmpty()) {
            return null;
        }
        Integer ans = top.value;
        top = top.prev;
        return ans;
    }

    public Integer peek() {
        if (isEmpty()) {
            return null;
        }
        return top.value;
    }



}
