public class PartC {
    /** Non-destructively computes the sum of the given lists. Assumes
     * that all lists are of the same length and that none are null. */
    public static DLListOfInts sumOfLists(DLListOfInts[] lists) {
        ListOfInts result = lists[0];
        for (int i = 1; i < lists.length; i += 1) {
            result.plusEquals(lists[i]);
        }
        //return result;

        //- Return type is upcast from result, so the code will not compile.
        //- A 0-length array input will cause an ArrayIndexOutOfBoundsException.
        //- It’s destructive. plusEquals changed this! So this changed list[0]!
        //- (quadratic runtime is out of scope, but acceptable answer) result.plusEquals calls the default
        //plusEquals (in ListOfInts), a slower implementation which results in a quadratic runtime.

        return null;
    }

}