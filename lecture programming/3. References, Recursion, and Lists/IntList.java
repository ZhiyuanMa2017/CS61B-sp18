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

	public static void main(String[] args) {
		IntList L = new IntList(15, null);
		L = new IntList(10, L);
		L = new IntList(5, L);

		System.out.println(L.iterativeSize());
		System.out.println(L.size());
		System.out.println(L.get(0));
	}
} 