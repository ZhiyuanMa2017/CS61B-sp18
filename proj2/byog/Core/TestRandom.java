package byog.Core;

import java.util.Random;

public class TestRandom {


    public static void main(String[] args) {
        long sEED = 1234567894;
        Random rand = new Random(sEED);
        int hallwayy = RandomUtils.uniform(rand, Math.max(5, 5), Math.min(15, 15));
        System.out.println(hallwayy);
    }



}
