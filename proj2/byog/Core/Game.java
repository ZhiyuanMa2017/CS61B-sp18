package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;



    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        long SEED =1648948916;
        Random rand = new Random(SEED);
        StdDraw.setCanvasSize(640, 640);
        StdDraw.setXscale(0, 640);
        StdDraw.setYscale(0, 640);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        Font bigFont = new Font("Time New Rome", Font.PLAIN, 30);
        StdDraw.setFont(bigFont);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(640 / 2, 640 *3 / 4, "CS61B: THE GAME");
        Font smallFont = new Font("Monaco", Font.PLAIN, 20);
        StdDraw.setFont(smallFont);
        StdDraw.text(640 / 2, 345,  "New Game (N)");
        StdDraw.text(640 / 2, 320,  "Load Game (L)");
        StdDraw.text(640 / 2, 295,  "Quit (Q)");
        StdDraw.show();
        Character inputkey = solicitInput();
        if (inputkey.equals('n')) {
            StdDraw.clear(Color.BLACK);
            StdDraw.text(320, 320, "Enter seed. Press S to end.");
            StdDraw.show();
            String input = "";
            boolean s = true;
            while(s) {
                if (StdDraw.hasNextKeyTyped()) {
                    char key = Character.toLowerCase(StdDraw.nextKeyTyped());
                    input += key;
                    StdDraw.clear(Color.BLACK);
                    StdDraw.text(320, 320, "Enter seed. Press S to end.");
                    StdDraw.text(320, 300, input);
                    StdDraw.show();
                    if(key == 's'){
                        s = false;
                    }
                }
            }
            System.out.println("13215614561456");
            StdDraw.clear(Color.BLACK);
            StdDraw.text(320, 320, "Your input: " + input);
            StdDraw.show();
            //playWithInputString(input);
        }
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

//        TETile[][] finalWorldFrame = null;


        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        return world;
    }

    public char solicitInput() {
        ArrayList<Character> nlq = new ArrayList<>();
        nlq.add('n');
        nlq.add('l');
        nlq.add('q');

        while (true) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            char key = Character.toLowerCase(StdDraw.nextKeyTyped());
            if(nlq.contains(key)) {
                return key;
            }
        }
    }
}

