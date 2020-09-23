package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestplayWithInputString {
    @Test
    public void testphase1() {
        TETile[][] test1 = Game.playWithInputString("N999SDDDWWWDDD");

        TETile[][] test2tmp = Game.playWithInputString("N999SDDD:Q");
        TETile[][] test2 = Game.playWithInputString("LWWWDDD");

        TETile[][] test3tmp = Game.playWithInputString("N999SDDD:Q");
        TETile[][] test3tmp2 = Game.playWithInputString("LWWW:Q");
        TETile[][] test3 = Game.playWithInputString("LDDD:Q");

        TETile[][] test4tmp = Game.playWithInputString("N999SDDD:Q");
        TETile[][] test4tmp2 = Game.playWithInputString("L:Q");
        TETile[][] test4tmp3 = Game.playWithInputString("L:Q");
        TETile[][] test4 = Game.playWithInputString("LWWWDDD");

        String result1 = TETile.toString(test1);
        String result2 = TETile.toString(test2);
        String result3 = TETile.toString(test3);
        String result4 = TETile.toString(test4);
        assertEquals(result1, result2);
        assertEquals(result1, result3);
        assertEquals(result1, result4);

    }
    public static void main(String[] args) {
        TETile[][] test = Game.playWithInputString("n5643591630821615871swwaawd");
        String result = TETile.toString(test);

        TERenderer ter = new TERenderer();
        //ter.initialize(80, 30);
        //ter.renderFrame(test);

        TETile[][] test1 = Game.playWithInputString("N999SDDDWWWDDD");

        TETile[][] test2tmp = Game.playWithInputString("N999SDDD:Q");
        TETile[][] test2 = Game.playWithInputString("LWWWDDD");

        TETile[][] test3tmp = Game.playWithInputString("N999SDDD:Q");
        TETile[][] test3tmp2 = Game.playWithInputString("LWWW:Q");
        TETile[][] test3 = Game.playWithInputString("LDDD:Q");

        TETile[][] test4tmp = Game.playWithInputString("N999SDDD:Q");
        TETile[][] test4tmp2 = Game.playWithInputString("L:Q");
        TETile[][] test4tmp3 = Game.playWithInputString("L:Q");
        TETile[][] test4 = Game.playWithInputString("LWWWDDD");

        String result1 = TETile.toString(test1);
        String result2 = TETile.toString(test2);
        String result3 = TETile.toString(test3);
        String result4 = TETile.toString(test4);
        System.out.println(result1.equals(result2));
        System.out.println(result1.equals(result3));
        System.out.println(result1.equals(result4));

    }
}
