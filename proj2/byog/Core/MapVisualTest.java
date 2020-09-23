package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MapVisualTest {
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        /* Feel free to change the width and height. */
        int wIDTH = 80;
        int hEIGHT = 30;

        ter.initialize(wIDTH, hEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[wIDTH][hEIGHT];
        for (int x = 0; x < wIDTH; x += 1) {
            for (int y = 0; y < hEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

//        Room testroom = new Room(15, 10, rand);
//        testroom.GenerateRoomFloor(world, new Position(5,5));
//        Room testroom2 = new Room(2, 2, rand);
//        if (testroom2.isOverlap(world, new Position(50,20))) {
//            System.out.println("overlap");
//        }
//        testroom2.GenerateRoomFloor(world, new Position(20,20));
//        testroom.GenerateHallway(world, testroom2);
//
//        Room testroom3 = new Room(50, 10, rand);
//        testroom3.GenerateRoomFloor(world, new Position(15,20));
//        testroom2.GenerateHallway(world,testroom);
//        testroom2.GenerateHallway(world,testroom3);
//        testroom3.GenerateHallway(world,testroom);
        MapGenerator.rand = new Random(123);
        MapGenerator.generateRooms(world);
        MapGenerator.generateHallways(world);
        MapGenerator.generateWall(world);

        System.out.println(TETile.toString(world));
        ter.renderFrame(world);
        while (true) {
            int x = (int) StdDraw.mouseX();
            int y = (int) StdDraw.mouseY();
            String text = "";

            text = world[x][y].description();
            StdDraw.setPenColor(Color.white);
            Font smallFont = new Font("Monaco", Font.PLAIN, 20);
            StdDraw.setFont(smallFont);
            StdDraw.textLeft(1, 29, text);
            StdDraw.show();
            ter.renderFrame(world);
        }

    }
}
