public class HuffmanDecoder {
    public static void main(String[] args) {
        ObjectReader or = new ObjectReader(args[0]);

        BinaryTrie binaryTrie = (BinaryTrie) or.readObject();
        int number = (int) or.readObject();
        BitSequence bs = (BitSequence) or.readObject();

        char[] res = new char[number];

        for (int i = 0; i < number; i++) {
            Match match = binaryTrie.longestPrefixMatch(bs);
            res[i] = match.getSymbol();
            bs = bs.allButFirstNBits(match.getSequence().length());
        }

        FileUtils.writeCharArray(args[1], res);
    }
}
