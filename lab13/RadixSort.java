import java.util.Arrays;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        int longest = 0;
        String[] tmp = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            longest = Math.max(longest, asciis[i].length());
            tmp[i] = asciis[i];
        }
        for (int i = longest - 1; i >= 0; i--) {
            sortHelperLSD(tmp, i);
        }
        return tmp;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     *
     * @param asciis Input array of Strings
     * @param index  The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        int[] counts = new int[257];
        for (String s : asciis) {
            if (s.length() <= index) {
                counts[0]++;
            } else {
                counts[(int) s.charAt(index) + 1]++;
            }
        }

        int[] starts = new int[257];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        String[] sorted2 = new String[asciis.length];
        for (int i = 0; i < asciis.length; i += 1) {
            String s = asciis[i];
            int id;
            if (s.length() <= index) {
                id = 0;
            } else {
                id = (int) s.charAt(index) + 1;
            }
            int place = starts[id];
            sorted2[place] = asciis[i];
            starts[id] += 1;
        }

        for (int i = 0; i < asciis.length; i++) {
            asciis[i] = sorted2[i];
        }
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start  int for where to start sorting in this method (includes String at start)
     * @param end    int for where to end sorting in this method (does not include String at end)
     * @param index  the index of the character the method is currently sorting on
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"ABSDVD", "hadk", "!d"};
        String[] sorted = sort(arr);
        System.out.println(Arrays.toString(sorted));
    }
}
