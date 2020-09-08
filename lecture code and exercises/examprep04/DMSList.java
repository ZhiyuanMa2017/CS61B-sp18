public class DMSList {
    private IntNode sentinel;

    public DMSList() {
        sentinel = new IntNode(-1000, new LastIntList());
    }

    public class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode h) {
            item = i;
            next = h;
        }

        public int max() {
            return Math.max(item, next.max());
        }
    }

    public class LastIntList extends IntNode{
        public LastIntList() {
            super(0, null);
        }
        @Override
        public int max() {
            return 0;
        }
    }

    public int max() {
        return sentinel.next.max();
    }
    public void insertFront(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
    }

    public static void main(String[] args) {
        DMSList dmsl = new DMSList();
        dmsl.insertFront(20);
        dmsl.insertFront(10);
        dmsl.insertFront(5);
        int x = dmsl.max();
        System.out.println(dmsl.max());
    }
}