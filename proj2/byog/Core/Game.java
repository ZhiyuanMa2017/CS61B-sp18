package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

import edu.princeton.cs.introcs.StdDraw;


import java.awt.Color;
import java.awt.Font;
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
        StdDraw.text(320, 345, "New Game (N)");
        StdDraw.text(320, 320, "Load Game (L)");
        StdDraw.text(320, 295, "Quit (Q)");
        StdDraw.show();
        char inputkey = solicitInputNLQ();
        World playworld = new World();
        if (inputkey == 'n') {
            StdDraw.clear(Color.BLACK);
            StdDraw.text(320, 320, "Enter seed. Press S to end.");
            StdDraw.show();
            String input = "";
            while (true) {
                if (StdDraw.hasNextKeyTyped()) {
                    char key = Character.toLowerCase(StdDraw.nextKeyTyped());
                    if (key == 's') {
                        break;
                    }
                    input = input + key;
                    StdDraw.clear(Color.BLACK);
                    StdDraw.text(320, 320, "Enter seed. Press S to end.");
                    StdDraw.text(320, 300, "Your input: " + input);
                    StdDraw.show();
                }
            }
            long intseed = Long.parseLong(input);
            playworld = new World(WIDTH, HEIGHT, intseed);
            playworld.init();
        }
        if (inputkey == 'q') {
            System.exit(0);
        }
        if (inputkey == 'l') {
            playworld = loadWorld();
        }
        play(playworld);
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
    public static TETile[][] playWithInputString(String input) {
        // Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        char[] inputchar = input.toLowerCase().toCharArray();
        int len = inputchar.length;
        String movestring = "";
        String seed = "";
        World playworld = new World();
        if (inputchar[0] == 'l') {
            playworld = loadWorld();
            if (saveornot(inputchar)) {
                movestring = input.substring(1, len - 2);
            } else {
                movestring = input.substring(1);
            }
            for (int i = 0; i < movestring.length(); i++) {
                playworld.move(movestring.charAt(i));
            }
            if (saveornot(inputchar)) {
                saveWorld(playworld);
            }

        } else if (inputchar[0] == 'n') {
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
            long intseed = Long.parseLong(seed);
            playworld = new World(WIDTH, HEIGHT, intseed);
            playworld.init();
            for (int i = 0; i < movestring.length(); i++) {
                playworld.move(movestring.charAt(i));
            }
            if (saveornot(inputchar)) {
                saveWorld(playworld);
            }
        }
        return playworld.world;
    }


    public static boolean saveornot(char[] inputchar) {
        int len = inputchar.length;
        if (inputchar[len - 1] == 'q') {
            if (inputchar[len - 2] == ':') {
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

    public void play(World playworld) {
        ter.initialize(WIDTH, HEIGHT);
        ter.renderFrame(playworld.world);
        showTile(playworld);
        while (true) {
            showTile(playworld);
            if (StdDraw.hasNextKeyTyped()) {
                char key = Character.toLowerCase(StdDraw.nextKeyTyped());
                if (key == 'w' || key == 'a' || key == 's' || key == 'd') {
                    playworld.move(key);
                    ter.renderFrame(playworld.world);
                    showTile(playworld);
                }
                if (key == ':') {
                    while (true) {
                        if (StdDraw.hasNextKeyTyped()) {
                            char exit = Character.toLowerCase(StdDraw.nextKeyTyped());
                            if (exit == 'q') {
                                saveWorld(playworld);
                                System.exit(0);
                            } else {
                                showTile(playworld);
                                ter.renderFrame(playworld.world);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void showTile(World playworld) {
        int x = (int) StdDraw.mouseX();
        int y = (int) StdDraw.mouseY();
        String text = "";
        if (x < WIDTH && y < HEIGHT) {
            text = playworld.world[x][y].description();
            StdDraw.setPenColor(Color.white);
            Font smallFont = new Font("Monaco", Font.PLAIN, 20);
            StdDraw.setFont(smallFont);
            StdDraw.textLeft(1, 29, text);
        }
        StdDraw.show();
        ter.renderFrame(playworld.world);
    }

}
