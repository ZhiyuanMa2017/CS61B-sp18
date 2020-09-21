package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.ArrayList;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;



    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        StdDraw.setCanvasSize(640, 640);
        StdDraw.setXscale(0, 640);
        StdDraw.setYscale(0, 640);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        Font bigFont = new Font("Time New Rome", Font.PLAIN, 30);
        StdDraw.setFont(bigFont);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(320, 480, "CS61B: THE GAME");
        Font smallFont = new Font("Monaco", Font.PLAIN, 20);
        StdDraw.setFont(smallFont);
        StdDraw.text(320, 345,  "New Game (N)");
        StdDraw.text(320, 320,  "Load Game (L)");
        StdDraw.text(320, 295,  "Quit (Q)");
        StdDraw.show();
        char inputkey = solicitInputNLQ();
        if (inputkey == 'n') {
            StdDraw.clear(Color.BLACK);
            StdDraw.text(320, 320, "Enter seed. Press S to end.");
            StdDraw.show();
            String input = "";
            boolean goon = true;
            while(goon) {
                if (StdDraw.hasNextKeyTyped()) {
                    char key = Character.toLowerCase(StdDraw.nextKeyTyped());
                    input = input + key;
                    StdDraw.clear(Color.BLACK);
                    StdDraw.text(320, 320, "Enter seed. Press S to end.");
                    StdDraw.text(320, 300, "Your input: " + input);
                    StdDraw.show();
                    if(key == 's'){
                        goon = false;
                    }
                }
            }
            System.out.println("13215614561456");
            StdDraw.clear(Color.BLACK);
            StdDraw.text(320, 320, "Your input: " + input);
            StdDraw.show();
            TETile[][] world = playWithInputString(input);
            ter.renderFrame(world);

        }
        if (inputkey == 'q') {
            System.exit(0);
        }
        if (inputkey == 'l') {
            TETile[][] world = playWithInputString(String.valueOf(inputkey));
            ter.renderFrame(world);
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
        // Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        char[] inputchar = input.toCharArray();
        int len = inputchar.length;
        String movestring = "";
        if (inputchar[0] == 'l') {
            World loadworld = loadWorld();
            loadworld.init();
            if (saveornot(inputchar)) {
                movestring = input.substring(1, len - 2);
            } else {
                movestring = input.substring(1);
            }
            for(int i = 0; i < movestring.length(); i++) {
                loadworld.move(movestring.charAt(i));
            }

        }

        if (inputchar[0] == 'n') {

        }


        return null;
    }


    public boolean saveornot(char[] inputchar) {
        int len = inputchar.length;
        if (inputchar[len - 1] == 'q') {
            if(inputchar[len - 2] == ':'){
                return true;
            }
        }
        return false;
    }

    public char solicitInputNLQ() {
        ArrayList<Character> nlq = new ArrayList<>();
        nlq.add('n');
        nlq.add('l');
        nlq.add('q');
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = Character.toLowerCase(StdDraw.nextKeyTyped());
                if (nlq.contains(key)) {
                    return key;
                }
            }
        }
    }

    private static World loadWorld() {
        File f = new File("./world.ser");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                World loadWorld = (World) os.readObject();
                os.close();
                return loadWorld;
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }

        /* In the case no World has been saved yet, we return a new one. */
        return new World();
    }

    private static void saveWorld(World w) {
        File f = new File("./world.ser");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(w);
            os.close();
        }  catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }



}
