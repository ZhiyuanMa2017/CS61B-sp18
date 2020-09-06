public class SquaringList{
	
	public static IntList square(IntList L) {
	     if(L==null){
	     	return L;
	     }
	     else{
	     	IntList rest = square(L.rest)
	     	IntList Q = new IntList(L.first*L.first,rest)
	     	return Q;
	}

	public static IntList squareMutative(IntList L) {
	     	IntList Q = L;
	     	while(Q != null){
	     		Q.first = Q.first*Q.first;
	     		Q = Q.rest;
	     	}
	     	return L;
	}
	
	public static IntList square2(IntList L) {
		if(L==null){
			return L;
		}
		IntList Q = L.rest;
		IntList QQ = new IntList(L.first*L.first, null);
		IntList QQQ = QQ;
		while(Q != null){
			QQQ.rest = new IntList(Q.first*Q.first,null);
			Q = Q.rest;
			QQQ = QQQ.rest;
		}
		return QQ;
	}

	public static IntList squareMutative2(IntList L) {
		if(L==null){
			return L;
		}
		else{
			squareMutative2(L.rest);
			L.first = L.first*L.first;
		}
		return L;
	}

}
