package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class MapGenerator {
    static int WIDTH = 80;
    static int HEIGHT = 40;

    public static void GenerateWall(TETile[][] world) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (world[i][j] == Tileset.FLOOR) {
                    for (int k = -1; k < 2; k++) {
                        for (int l = -1; l < 2; l++) {
                            if (world[i+k][j+l] == Tileset.NOTHING) {
                                world[i+k][j+l] = Tileset.WALL;
                            }
                        }
                    }
                }
            }
        }
    }
}
