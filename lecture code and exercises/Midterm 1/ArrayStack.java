public class ArrayStack<Item> implements Stack<Item> {
    private Item[] items;
    private int size;
    public ArrayStack() { // initial array size is 8
        items = (Item[]) new Object[8];
        size = 0;
    }
    private void resize(int capacity) { // resizes array to given capacity
        Item[] newitem = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, newitem, 0, size);
    }
    public void push(Item x) {
        if (usageRatio() == 1) {
            resize(size * 2);
        }
        items[size] = x;
        size = size + 1;
    }
    public Item pop() { // returns null if stack is empty
        if (size == 0) {
            return null;
        }
        if (usageRatio() < 0.25 && items.length > 8) {
            resize(items.length / 2);
        }
        Item returnitem = items[size];
        items[size] = null;
        size = size - 1;
        return returnitem;
    }
    public int size() {
        return size;
    }
    private double usageRatio() {
        return ((double) size()) / items.length;
    }
}
