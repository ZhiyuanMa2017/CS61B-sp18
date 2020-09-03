/**
problem: https://sp18.datastructur.es/materials/discussion/disc03.pdf
solution: https://sp18.datastructur.es/materials/discussion/disc03sol.pdf
*/

public class SLList{
	private static class IntNode {
		public int item;
		public IntNode next;
		public IntNode(int item, IntNode next) {
			this.item = item;
			this.next = next;
		}
	}

	private IntNode first;

	public void addFirst(int x) {
		first = new IntNode(x, first);
	}

	public SLList(int x){
		first = new IntNode(x, null);
	}

	public void insert(int item, int position) {
		if(first == null || position == 0) {
			addFirst(item);
			return;
		}
		IntNode p = first;
		while(position > 1 && p.next != null){
			position = position - 1;
			p = p.next;
		}
		IntNode pp = new IntNode(item, p.next);
		p.next = pp;
	}

	public void reverse() {
		IntNode prev = null;
		IntNode p = first;
		while(p != null) {
			IntNode q = p.next;//store the next
			p.next = prev;//reverse, the current node's next is the previous node
			prev = p;//the current node is the next node's previous node
			p = q;//go to next node to do the loop
		}
		first = prev;//the last prev which is the last node in previous SLList now is the first node
	}

	public void reverseRecursive() {
		first = reverseRecursiveHelper(first);
	}

	private IntNode reverseRecursiveHelper(IntNode x) {
		if(x == null || x.next == null) {
			return x;
		} else {
			IntNode reversed = reverseRecursiveHelper(x.next);
			x.next.next = x;
			x.next = null;
			return reversed;
		}
	}
	public static void main(String[] args) {
		SLList p = new SLList(1);
		p.addFirst(2);
		p.addFirst(3);
		p.addFirst(4);
		p.reverseRecursive();
	}
}