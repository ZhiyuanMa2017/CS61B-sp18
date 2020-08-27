public class IntList {
	public int first;
	public IntList rest;

	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}

	/** Return the size of the list using... recursion! */
	public int size() {
		IntList p = this;
		if(p.rest == null){
			return 1;
		}
		return 1 + p.rest.size();
	}

	/** Return the size of the list using no recursion! */
	public int iterativeSize() {
		IntList p = this;
		int number = 1;
		while(p.rest != null){
			number += 1;
			p = p.rest;
		}
		return number;
	}

	/** Returns the ith value in this list.*/
	public int get(int i) {
		if (i == 0){
			return first;
		}
		return rest.get(i-1);
	}

	/** We want to add a method to IntList so that if 
	    2 numbers in a row are the same, we add them together 
	    and make one large node. */
	public void addAdjacent(){
		IntList p = this;
		while(p.rest != null){
			if(p.first == p.rest.first){
				p.first = p.first + p.first;
				p.rest = p.rest.rest;
				this.addAdjacent();
			}
			else{
				p = p.rest;
			}
		}
	}
	
	/** Modify the Intlist class so that every time 
	    you add a value you “square” the IntList.*/
	public void addsquare(int x){
		IntList p = this;
		if (p.rest == null){
			p.rest = new IntList(p.first*p.first, new IntList(x,null));
		}else{
			p.rest = new IntList(p.first*p.first, p.rest);
			p.rest.rest.addsquare(x);
		}
	}

	/** my solution to get */
	// public int get(int i){
	// 	IntList p = this;
	// 	int countnumber = 0;
	// 	int getnumber = 0;
	// 	if(countnumber == i){
	// 		getnumber = p.first;
	// 	}
	// 	else{
	// 		while(countnumber != i){
	// 			countnumber += 1;
	// 			p = p.rest;
	// 			if(countnumber == i){
	// 				getnumber = p.first;
	// 			}

	// 		}
	// 	}
	// 	return getnumber;
	// }

	/** Exercise 2.2.1 
	  * write an addFirst method to the IntList class*/
	// public void addFirst(int x){
	// 	IntList p = this;
	// 	this = new IntList(x, p);

	// }



	public static void main(String[] args) {
		IntList L = new IntList(15, null);
		L = new IntList(10, L);
		L = new IntList(5, L);
		// System.out.println(L.iterativeSize());
		// System.out.println(L.size());
		// System.out.println(L.get(0));
		L.addsquare(1);
		System.out.println(L.size());
		System.out.println(L.get(0));
		System.out.println(L.get(1));		
		System.out.println(L.get(2));
		System.out.println(L.get(3));
		System.out.println(L.get(4));
		System.out.println(L.get(5));
		System.out.println(L.get(6));


		IntList LL = new IntList(5, null);
		LL = new IntList(3, LL);
		LL = new IntList(2, LL);
		LL = new IntList(1, LL);
		LL = new IntList(1, LL);
		LL.addAdjacent();
		// System.out.println(LL.size());
		// System.out.println(LL.get(1));

	}
} 