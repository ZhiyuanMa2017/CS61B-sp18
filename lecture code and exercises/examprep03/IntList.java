public class IntList {
	public int first;
	public IntList rest;
	public IntList (int f, IntList r) {
		this.first = f;
		this.rest = r;
	}

	public boolean equals(Object o) {

	}

	public static IntList list(int args) {

	}

	public void skippify() {
		IntList p = this;
		int n = 1;
		while (p != null) {
			IntList next = p.rest;
			for(int i = 0; i < n; i++) {
				if (next == null){
					break;
				}
				next = next.rest;
			}
			p.rest = next;
			p = p.rest;
			n = n + 1;
		}
	}

	public stat void removeDuplicates(IntList p) {
		if (p == null) {
			return;
		}
		IntList current = p.rest;
		IntList previous = p;
		while (currernt != null) {
			if (currernt.first == previous.first) {
				previous.rest = previous.rest.rest;

			} else {
				previous = currernt;
			}
			current = current.rest;
		}
	}

}