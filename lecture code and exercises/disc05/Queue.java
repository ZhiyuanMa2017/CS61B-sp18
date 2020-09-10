public class Queue<T> {
    private Stack<T> stackqueue = new Stack<>();

    public void push(T t) {
    	stackqueue.push(t);
    }

    public <T> poll(T t) {
    	Stack<T> tmp = new Stack<>();
    	while(!stackqueue.isEmty()) {
    		tmp.push(stackqueue.pop());
    	}
    	T returnnumber = tmp.pop();
    	while(!tmp.isEmty()) {
    		stackqueue.push(tmp.pop());
    	}
    	return returnnumber;
    }
}
