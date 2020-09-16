public class AltList<X, Y> {
    private X item;
    private AltList<Y, X> next;

    AltList(X item, AltList<Y, X> next) {
        this.item = item;
        this.next = next;
    }

    public AltList<Y, X> pairsSwapped() {
        AltList<Y, X> swap = new AltList<Y, X>(next.item, new AltList<X, Y>(this.item, null));
        if (next.next != null) {
            swap.next.next = next.next.pairsSwapped();
        }
        return swap;
    }


    public static void main(String[] args) {
        AltList<Integer, String> list =
                new AltList<Integer, String>(5,
                        new AltList<String, Integer>("cat",
                                new AltList<Integer, String>(10,
                                        new AltList<String, Integer>("dog", null))));

        AltList<String, Integer> tsil = list.pairsSwapped();
    }
}
