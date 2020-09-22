package byog.Core;

import byog.TileEngine.TERenderer;
import edu.princeton.cs.introcs.StdDraw;

public class TestplayWithKeyboard {



    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        World playworld = new World(80, 30, 123);
        playworld.init();
        ter.initialize(80, 30);
        ter.renderFrame(playworld.world);
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = Character.toLowerCase(StdDraw.nextKeyTyped());
                System.out.println(key);
                System.out.println(key == 'w');

                if (key == 'w' || key == 'a' || key == 's' || key == 'd') {
                    System.out.println("enter");
                    playworld.move(key);
                    ter.renderFrame(playworld.world);
                }
            }
        }
    }

}
