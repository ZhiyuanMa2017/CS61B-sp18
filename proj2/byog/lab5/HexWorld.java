package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final long SEED = 1648948916;
    private static final Random RANDOM = new Random(SEED);

    private static class Position {
        int x;
        int y;

        Position(int posX, int posY) {
            x = posX;
            y = posY;
        }

    }

    /**
     * Computes the width of row i for a size s hexagon.
     * @param s The size of the hex.
     * @param i The row number where i = 0 is the bottom row.
     * @return
     */
    public static int hexRowWidth(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }

        return s + 2 * effectiveI;
    }

    /**
     * Computes relative x coordinate of the leftmost tile in the ith
     * row of a hexagon, assuming that the bottom row has an x-coordinate
     * of zero. For example, if s = 3, and i = 2, this function
     * returns -2, because the row 2 up from the bottom starts 2 to the left
     * of the start position, e.g.
     *   xxxx
     *  xxxxxx
     * xxxxxxxx
     *xxxxxxxxxx
     *xxxxxxxxxx
     * xxxxxxxx <-- i = 2, starts 2 spots to the left of the bottom of the hex
     *  xxxxxx
     *   xxxx
     *
     * @param s size of the hexagon
     * @param i row num of the hexagon, where i = 0 is the bottom
     * @return
     */
    public static int hexRowOffset(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return -effectiveI;
    }

    /** Adds a row of the same tile.
     * @param world the world to draw on
     * @param p the leftmost position of the row
     * @param width the number of tiles wide to draw
     * @param t the tile to draw
     */
    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int xi = 0; xi < width; xi += 1) {
            int xCoord = p.x + xi;
            int yCoord = p.y;
            world[xCoord][yCoord] = TETile.colorVariant(t, 150, 150, 150, RANDOM);
        }
    }

    /**
     * Adds a hexagon to the world.
     * @param world the world to draw on
     * @param p the bottom left coordinate of the hexagon
     * @param s the size of the hexagon
     * @param t the tile to draw
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {

        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        // hexagons have 2*s rows. this code iterates up from the bottom row,
        // which we call row 0.
        for (int yi = 0; yi < 2 * s; yi += 1) {
            int thisRowY = p.y + yi;

            int xRowStart = p.x + hexRowOffset(s, yi);
            Position rowStartP = new Position(xRowStart, thisRowY);

            int rowWidth = hexRowWidth(s, yi);

            addRow(world, rowStartP, rowWidth, t);

        }
    }

    /**
     * Draws a column of N hexes
     * @param world the world to draw on
     * @param p the bottom left coordinate of the hexagon
     * @param n the number of the hexagon
     * @param s the size of the hexagon
     */
    public static void drawRandomVerticalHexes(TETile[][] world, Position p, int n, int s) {
        Position pp = p;
        for (int i = 0; i < n; i++) {
            addHexagon(world, pp, s, randomTile());
            pp = new Position(pp.x, pp.y + s * 2);

        }
    }

    /**
     * Return the bottomRight Hexagon's position
     * @param p the bottom left coordinate of the hexagon
     * @param s the size of the hexagon
     */
    public static Position bottomRight(Position p, int s) {
        Position pp = new Position(p.x + 2 * s - 1, p.y - s);
        return pp;
    }

    /**
     * Return the topRight Hexagon's position
     * @param p the bottom left coordinate of the hexagon
     * @param s the size of the hexagon
     */
    public static Position topRight(Position p, int s) {
        Position pp = new Position(p.x + 2 * s - 1, p.y + s);
        return pp;
    }

    public static void drawingATesselationofHexagons(TETile[][] world, Position p, int s) {
        Position pp = p;
        drawRandomVerticalHexes(world, pp, 3, s);
        pp = bottomRight(pp, s);
        drawRandomVerticalHexes(world, pp, 4, s);
        pp = bottomRight(pp, s);
        drawRandomVerticalHexes(world, pp, 5, s);
        pp = topRight(pp, s);
        drawRandomVerticalHexes(world, pp, 4, s);
        pp = topRight(pp, s);
        drawRandomVerticalHexes(world, pp, 3, s);
    }

    /** Picks a RANDOM tile with a 33% change of being
     *  a wall, 33% chance of being a flower, and 33%
     *  chance of being empty space.
     */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.SAND;
            case 1: return Tileset.MOUNTAIN;
            case 2: return Tileset.TREE;
            case 3: return Tileset.WATER;
            case 4: return Tileset.FLOWER;
            default: return Tileset.FLOWER;
        }
    }

    @Test
    public void testHexRowWidth() {
        assertEquals(3, hexRowWidth(3, 5));
        assertEquals(5, hexRowWidth(3, 4));
        assertEquals(7, hexRowWidth(3, 3));
        assertEquals(7, hexRowWidth(3, 2));
        assertEquals(5, hexRowWidth(3, 1));
        assertEquals(3, hexRowWidth(3, 0));
        assertEquals(2, hexRowWidth(2, 0));
        assertEquals(4, hexRowWidth(2, 1));
        assertEquals(4, hexRowWidth(2, 2));
        assertEquals(2, hexRowWidth(2, 3));
    }

    @Test
    public void testHexRowOffset() {
        assertEquals(0, hexRowOffset(3, 5));
        assertEquals(-1, hexRowOffset(3, 4));
        assertEquals(-2, hexRowOffset(3, 3));
        assertEquals(-2, hexRowOffset(3, 2));
        assertEquals(-1, hexRowOffset(3, 1));
        assertEquals(0, hexRowOffset(3, 0));
        assertEquals(0, hexRowOffset(2, 0));
        assertEquals(-1, hexRowOffset(2, 1));
        assertEquals(-1, hexRowOffset(2, 2));
        assertEquals(0, hexRowOffset(2, 3));
    }

    public static void main(String[] args) {
        int wIDTH = 50;
        int hEIGHT = 50;
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(wIDTH, hEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[wIDTH][hEIGHT];
        for (int x = 0; x < wIDTH; x += 1) {
            for (int y = 0; y < hEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        // draw hexagon from position
        Position p = new Position(10, 10);

        //addHexagon(world, p, 3, randomTile());

        //drawRandomVerticalHexes(world, p, 3, 3);
        drawingATesselationofHexagons(world, p, 3);

        // draws the world to the screen
        ter.renderFrame(world);
    }
    
}
