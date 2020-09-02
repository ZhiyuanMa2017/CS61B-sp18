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
			IntNode q = p.next;//???????
			p.next = prev;//reverse ????ڵ??һ????һ???ڵ?
			prev = p;//???? ?һ???ڵ????????ڵ?????ѭ??
			p = q;//???? ?????????ڵ?????ѭ??
		}
		first = prev;//??????prev?Ϊ??????ڵ?p???ΪNull
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