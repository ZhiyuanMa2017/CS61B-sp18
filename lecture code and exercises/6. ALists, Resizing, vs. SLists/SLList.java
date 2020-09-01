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

	public void insert(int item, int position) {
		if(first == null || position == 0) {
			addFirst(item);
			return
		}
		IntNode p = first;
		while(position > 1 && p.first != null){
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
			IntNode q = p.next;//保存后面的
			p.next = prev;//reverse 这个节点的下一个是上一个节点
			prev = p;//迭代 上一个节点变成下一个节点进入下个循环
			p = q;//迭代 换成下一个节点进入下个循环
		}
		first = prev;//最后剩个prev作为第一个节点 p此时为Null
	}
}