public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> worddeque = new LinkedListDeque<>();

        for (int i = 0; i < word.length(); i++) {
            worddeque.addLast(word.charAt(i));
        }
        return worddeque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> worddeque = wordToDeque(word);
        if (worddeque.size() == 0) {
            return true;
        } else {
            while (!worddeque.isEmpty()) {
                if (worddeque.size() == 1) {
                    return true;
                }
                Character first = worddeque.removeFirst();
                Character last =  worddeque.removeLast();
                if (!first.equals(last)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> worddeque = wordToDeque(word);
        if (worddeque.size() == 0) {
            return true;
        } else {
            while (!worddeque.isEmpty()) {
                if (worddeque.size() == 1) {
                    return true;
                }
                char first =  worddeque.removeFirst();
                char last =  worddeque.removeLast();
                if (!cc.equalChars(first, last)) {
                    return false;
                }
            }
        }
        return true;
    }
}
