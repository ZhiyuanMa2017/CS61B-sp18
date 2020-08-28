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
        LinkedListDeque<T> d = this;
        StuffNode p = d.sentinel;
        if (index == 0) {
            return p.item;
        }
        d.removeFirst();
        return d.getRecursive(index - 1);
    }

}
