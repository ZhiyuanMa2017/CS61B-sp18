import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    StudentArrayDeque<Integer> a = new StudentArrayDeque<>();
    ArrayDequeSolution<Integer> b = new ArrayDequeSolution<>();

    @Test
    public void testtask1() {

        String message = "\n";

        while (true) {

            double numberBetweenZeroAndOne = StdRandom.uniform();
            int i = StdRandom.uniform(200);

            if (numberBetweenZeroAndOne < 0.25) {
                a.addLast(i);
                b.addLast(i);
                message = message+ "addLast(" + i + ")\n";
            }
            else if (numberBetweenZeroAndOne < 0.5) {
                a.addFirst(i);
                b.addFirst(i);
                message += "addLast(" + i + ")\n";
            }
            else if (numberBetweenZeroAndOne < 0.75 && !a.isEmpty()) {
                Integer aa = a.removeFirst();
                Integer bb = b.removeFirst();
                message += "removeFirst()\n";
                assertEquals(message, bb, aa);
            }
            else if (numberBetweenZeroAndOne < 1 && !a.isEmpty()) {
                Integer aa = a.removeLast();
                Integer bb = b.removeLast();
                message += "removeLast()\n";
                assertEquals(message, bb, aa);
            }
        }
    }


//        for (int j = 0; j < 10; j = j + 1) {
//            Integer aa = a.get(j);
//            Integer bb = b.get(j);
//            assertEquals("Oh noooo!\nThis is bad:\n   Studentarray number " + aa
//                            + " not equal to " + bb + "!",
//                    bb, aa);
//        }
//
//        for (int j = 0; j < 10; j = j + 1) {
//            Integer aa = a.removeLast();
//            Integer bb = b.removeLast();
//            assertEquals("Oh noooo!\nThis is bad:\n   Studentarray number " + aa
//                            + " not equal to " + bb + "!",
//                    bb, aa);
//        }
////
//        for (int j = 0; j < 10; j = j + 1) {
//            Integer aa = a.removeFirst();
//            Integer bb = b.removeFirst();
//            assertEquals("Oh noooo!\nThis is bad:\n   Studentarray number " + aa
//                            + " not equal to " + bb + "!",
//                    bb, aa);
//        }
}

