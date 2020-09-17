package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, long seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        String s = "";
        for (int i = 0; i < n; i++) {
            s = s + CHARACTERS[rand.nextInt(26)];
        }
        return s;
    }

    public void drawFrame(String s) {
        StdDraw.clear();
        //StdDraw.setCanvasSize();
        StdDraw.setPenColor(StdDraw.BLUE);
        Font font = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(width / 2, height / 2, s);
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        for (int i = 0; i < letters.length(); i++) {
            drawFrame(letters.substring(i, i + 1));
            StdDraw.pause(1000);
            drawFrame("");
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        String input = "";
        drawFrame(input);
        while (input.length() < n) {
            if (StdDraw.hasNextKeyTyped()) {
                input = input + StdDraw.nextKeyTyped();
            }
        }
        String show = "Your input: " + input;
        drawFrame(show);
        StdDraw.pause(1500);
        return input;
    }

    public void startGame() {
        gameOver = false;
        round = 1;
        while (!gameOver) {
            String roundstr = "Round:" + round;
            drawFrame(roundstr);
            StdDraw.pause(500);
            String thisroundstr = generateRandomString(round);
            flashSequence(thisroundstr);
            String input = solicitNCharsInput(round);
            if (input.equals(thisroundstr)) {
                round += 1;
            } else {
                String s = "Game Over! You made it to round:" + round;
                drawFrame(s);
                gameOver = true;
            }
        }

    }

}
