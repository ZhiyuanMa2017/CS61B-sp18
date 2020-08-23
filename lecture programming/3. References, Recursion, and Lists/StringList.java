public class StringList {
   String head;
   StringList tail;
   public StringList(String head, StringList tail) {
      this.head = head;
      this.tail=tail;
   }
   public static void main(String[] args) {
   StringList L = new StringList("eat", null);
	L = new StringList("shouldn't", L);
	L = new StringList("you", L);
	L = new StringList("sometimes", L);
	StringList M = L.tail;
	StringList R = new StringList("many", null);
	R = new StringList("potatoes", R);
	R.tail.tail = R;
	M.tail.tail.tail = R.tail;
	L.tail.tail = L.tail.tail.tail;
	L = M.tail;
   }
}