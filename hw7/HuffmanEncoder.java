import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        Map<Character, Integer> table = new HashMap<>();
        for (char c : inputSymbols) {
            table.compute(c, (k, v) -> (v == null) ? 1 : v + 1);
        }
        return table;
    }

    public static void main(String[] args) {
        char[] inputSymbols = FileUtils.readFile(args[0]);

        Map<Character, Integer> frequencyTable = buildFrequencyTable(inputSymbols);

        BinaryTrie binaryTrie = new BinaryTrie(frequencyTable);

        ObjectWriter ow = new ObjectWriter(args[0] + ".huf");
        ow.writeObject(binaryTrie);
        ow.writeObject(inputSymbols.length);

        Map<Character, BitSequence> lookupTable = binaryTrie.buildLookupTable();

        List<BitSequence> sequences = new ArrayList<>();

        for (char c : inputSymbols) {
            BitSequence bs = lookupTable.get(c);
            sequences.add(bs);
        }

        ow.writeObject(BitSequence.assemble(sequences));
    }
}
