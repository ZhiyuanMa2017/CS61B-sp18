public class DLListOfInts implements ListOfInts {
    @Override
    public void addLast(int i) {

    }

    @Override
    public int get(int i) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void set(int i, int value) {

    }

    public class IntNode {
        public int item;
        public IntNode next, prev;
    }
    public IntNode sentinel;
    public int size;

    @Override
    public void plusEquals(DLListOfInts x) {
        if (this.size() != x.size() || x == null) {
            return;
        }

        IntNode xnode = x.sentinel.next;
        // doubly linked list!!! So the last one's next is sentinel!
        for (IntNode p = sentinel.next; p.next != sentinel; p =p.next) {
            p.item = p.item + xnode.item;
            xnode = xnode.next;
        }
    }
}
