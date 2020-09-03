public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public void resize(int capacity) {
        T[] tmp = (T[]) new Object[capacity];
        System.arraycopy(items, 0, tmp, 0, nextLast);
        if (capacity > items.length) {
            if (nextFirst + 1 < nextLast) {
                System.arraycopy(items, 0, tmp, 0, size);
                nextFirst = -1;
                nextLast = size - 1;
                //  [a][b][c][d] --> [a][b][c][d][][][][]
                // F            L   F            L
            } else {
                System.arraycopy(items, nextFirst + 1, tmp, 0, size - nextLast);
                System.arraycopy(items, 0, tmp, size - nextLast, nextLast);
                nextFirst = -1;
                nextLast = size - 1;
                // change order so that ArrayDeque[i] is lodated at items[i]
                // [d][a][b][c] --> [a][b][c][d][][][][]
                //  F  L           F            L
            }
        } else {
            if (nextFirst < nextLast) {
                System.arraycopy(items, nextFirst + 1, tmp, 0, size);
                nextFirst = -1;
                nextLast = size - 1;
                // [][][a][b][c][][][] --> [a][b][c][]
                //    F         L         F         L
            } else {
                System.arraycopy(items, nextFirst + 1, tmp, 0, items.length - nextFirst - 1);
                System.arraycopy(items, 0, tmp, items.length - nextFirst - 1, nextLast);
                nextFirst = -1;
                nextLast = size - 1;
                // [c][][][][][][a][b] --> [a][b][c][]
                //    L        F          F         L
            }
        }
        items = tmp;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        int firstindex = (nextFirst + items.length) % items.length;
        items[firstindex] = item;
        nextFirst = firstindex - 1;
        size = size + 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        int nextindex = nextLast % items.length;
        items[nextindex] = item;
        nextLast = nextindex + 1;
        size = size + 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[(i + nextFirst + 1) % items.length] + " ");
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            T firstvalue = items[(nextFirst + 1) % items.length];
            items[(nextFirst + 1) % items.length] = null;
            size = size - 1;
            if (items.length >= 16 && (double) size / items.length < 0.25) {
                resize(items.length / 2);
            }
            return firstvalue;
        }
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            T lastvalue = items[(nextLast - 1 + items.length) % items.length];
            items[(nextLast - 1 + items.length) % items.length] = null;
            size = size - 1;
            if (items.length >= 16 && (double) size / items.length < 0.25) {
                resize(items.length / 2);
            }
            return lastvalue;
        }
    }

    public T get(int index) {
        if (index > size - 1) {
            return null;
        } else {
            return items[(nextFirst + 1 + index) % items.length];
        }
    }
}
