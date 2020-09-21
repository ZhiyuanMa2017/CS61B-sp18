package byog.Core;

import edu.princeton.cs.introcs.StdDraw;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        String s = "1234";
        int SEED = s.hashCode();
        System.out.println(SEED);
        Random rand = new Random();
        int hallwayy = RandomUtils.uniform(rand, Math.max(5,5), Math.min(15, 15));
        System.out.println(hallwayy);
        char key = Character.toLowerCase('1');
        System.out.println(key);
        String input = "adhakdhasjkd";
        char[] inputchar = input.toCharArray();
        int seed = Integer.parseInt(input.replaceAll("\\D+",""));
        System.out.println(seed);
    }
}
