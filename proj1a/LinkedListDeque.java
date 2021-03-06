public class LinkedListDeque<T> {
    private class StuffNode {
        private StuffNode prev;
        private T item;
        private StuffNode next;

        StuffNode(StuffNode n1, T i, StuffNode n2) {
            prev = n1;
            item = i;
            next = n2;
        }
    }

    private StuffNode sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new StuffNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

//    public LinkedListDeque(LinkedListDeque other) {
//        size = 0;
//        sentinel = new StuffNode(null, null, null);
//        sentinel.prev = sentinel;
//        sentinel.next = sentinel;
//
//        for (int i = 0; i < other.size(); i += 1) {
//            addLast((T) other.get(i));
//        }
//    }

    public void addFirst(T item) {
        size += 1;
        sentinel.next = new StuffNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
    }

    public void addLast(T item) {
        size += 1;
        sentinel.prev = new StuffNode(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
    }

    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        StuffNode p = sentinel;
        while (p.next != sentinel) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
    }

    public T removeFirst() {
        if (sentinel.next != sentinel) {
            T i = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return i;
        }
        return null;
    }

    public T removeLast() {
        if (sentinel.prev != sentinel) {
            T i = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return i;
        }
        return null;
    }

    public T get(int index) {
        StuffNode p = sentinel.next;
        if (index > size - 1) {
            return null;
        } else {
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            return p.item;
        }
    }

    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }
    private T getRecursiveHelper(StuffNode s, int i) {
        if (i == 0) {
            return s.item;
        } else {
            return getRecursiveHelper(s.next, i - 1);
        }
    }

//    public static void main(String[] args) {
//        LinkedListDeque<Integer> test = new LinkedListDeque<>();
//        test.addFirst(20);
//        test.addLast(30);
//        test.addLast(40);
//        test.addLast(50);
//        test.addLast(60);
//        test.addFirst(10);
//        test.printDeque();
//        test.removeFirst();
//        test.removeLast();
//        int b = test.getRecursive(3);
//        int c = test.getRecursive(2);
//        int d = test.getRecursive(1);
//        int e = test.getRecursive(0);
//        System.out.print(b + " " + c + " " + d + " " + e);
//    }
}
