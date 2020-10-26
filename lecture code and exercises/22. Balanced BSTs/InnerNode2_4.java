class InnerNode2_4 extends Node2_4 {
    @Override
    boolean contains(String key) {
        for (int k = 0; k < size()-1; k += 1 ) {
            if (key(k).equals(key) ) {
                return true;
            } else if (key(k).compareTo(key) > 0 ) {
                return kid(k ).contains(key);
            }
        }
        return kid(size() - 1).contains(key);
    }
}