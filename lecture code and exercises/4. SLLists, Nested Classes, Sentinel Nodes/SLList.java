public class SLList{

	/** If the nested class has no need to use any 
	  * of the instance methods or variables of SLList, 
	  * you may declare the nested class static */
	private static class IntNode {
		public int item;
		public IntNode next;

		public IntNode(int i, IntNode n) {
			item = i;
			next = n;
		}
	}
	/* The first item (if it exists) is at sentinel.next. */
	private IntNode sentinel;
	private int size;

	public SLList(){
		sentinel = new IntNode(11111, null);
		size = 0;
	}	

	public SLList(int x){
		sentinel = new IntNode(11111, null);
		sentinel.next = new IntNode(x, null);
		size = 1;
	}

	public SLList(int [] anarray){
		sentinel = new IntNode(11111, null);
		size = 0;
		for (int i : anarray){
			this.addLast(i);
		}
	}

 	/** Adds x to the front of the list. */
	public void addFirst(int x){
		sentinel.next = new IntNode(x, sentinel.next);
		size += 1;
	}


 	/** Returns the first item in the list. */
	public int getFirst(){
		return sentinel.next.item;
	}
 	/** Adds x to the end of the list. */
	public void addLast(int x){
		size += 1;

		IntNode p = sentinel;

		while (p.next != null){
			p = p.next;
		}
		p.next = new IntNode(x,null);
	}

 	/** Deletes the first item in the list. */
	public void deleteFirst(){
		if (sentinel.next != null){
			sentinel.next = sentinel.next.next;
			size -= 1;
		}

	}

 	/** e the size of the list. */
 	public int size(){
 		return size;
 	}


	// private static int size(IntNode p){
	// 	if (p.next == null) {
	// 		return 1;
	// 	}
	// 	return 1 + size(p.next);
	// }

 	// public int size(){
 	// 	return size(first);
 	// }


 // 	/** My method of size */
	// public int size(){
	// 	IntNode p = first;
	// 	Int countnumber = 0;
	// 	while (p.next != null){
	// 		p = p.next;
	// 		countnumber +=1;
	// 	}
	// 	return countnumber;	
	// }

	public static void main(String[] args) {
		SLList L = new SLList(15);
		L.addFirst(10);
		L.addFirst(5);
		L.addLast(20);
		System.out.println(L.size());
		int[] array = new int[]{1,2,3,4,5};
		SLList Larray = new SLList(array);
		Larray.deleteFirst();
		System.out.println(Larray.size());
	}
}