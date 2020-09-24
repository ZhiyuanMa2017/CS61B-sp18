package byog.Core;


import java.util.Random;

public class Test {
    public static boolean saveornot(char[] inputchar) {
        int len = inputchar.length;
        if (inputchar[len - 1] == 'q') {
            if (inputchar[len - 2] == ':') {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        String s = "1234";
        int seeD = s.hashCode();

        Random rand = new Random();
        int hallwayy = RandomUtils.uniform(rand, Math.max(5, 5), Math.min(15, 15));

        char key = Character.toLowerCase('1');

        String input = "n1234sqwuidhui:q";
        char[] inputchar = input.toCharArray();
        int sEEDD = Integer.parseInt(input.replaceAll("\\D+", ""));
        System.out.println(inputchar);
        System.out.println(saveornot(inputchar));
        String seed = "";
        String movestring = "123";

        for (int i = 1; i < inputchar.length; i++) {
            if (inputchar[i] != 's') {
                seed += inputchar[i];
            } else {
                if (saveornot(inputchar)) {
                    movestring = input.substring(i + 1, inputchar.length - 2);
                } else {
                    movestring = input.substring(i + 1);
                }
                break;
            }
        }
        System.out.println(seed);
        System.out.println(movestring);
    }
}
