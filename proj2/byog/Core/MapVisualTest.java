package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class MapVisualTest {
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        /* Feel free to change the width and height. */
         int WIDTH = 80;
         int HEIGHT = 40;

        long SEED = 1234567894;
        Random rand = new Random(SEED);
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        Room testroom = new Room(10, 10, rand);
        testroom.GenerateRoomFloor(world, new Position(5,5));


        Room testroom2 = new Room(20, 10, rand);
//        if (testroom2.isOverlap(world, new Position(50,20))) {
//            System.out.println("overlap");
//        }
        testroom2.GenerateRoomFloor(world, new Position(50,5));
        testroom.GenerateHallway(world,testroom2);
        MapGenerator.GenerateWall(world);

        System.out.println(TETile.toString(world));
        ter.renderFrame(world);
    }
}