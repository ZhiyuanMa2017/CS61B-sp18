package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;
    public int capacity() {
        return capacity;
    }
    public int fillCount() {
        return fillCount;
    }
    /* Note that isEmpty, isFull, peek, dequeue, enqueue, are inherited from BoundedQueue,
    so you should not to declare these explicitly in your AbstractBoundedQueue.java file. */
}
