package byog.Core;

import byog.SaveDemo.Square;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World implements Serializable {
    private static final long serialVersionUID = 123123123123123L;
    Random r = new Random(123);
    int WIDTH = 80;
    int HEIGHT = 40;
    TETile[][] world;
    int[] player;

    public World(int width, int height, long seed) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.r = new Random(seed);
    }
    public World() {
    }

    public void init() {
        this.world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        MapGenerator.rand = this.r;
        MapGenerator.GenerateRooms(world);
        MapGenerator.GenerateHallways(world);
        MapGenerator.GenerateWall(world);
        GeneratePlayer();
    }

    public void GeneratePlayer() {
        int x = RandomUtils.uniform(r, 0, WIDTH);
        int y = RandomUtils.uniform(r, 0, HEIGHT);
        while(world[x][y] != Tileset.FLOOR) {
            x = RandomUtils.uniform(r, 0, WIDTH);
            y = RandomUtils.uniform(r, 0, HEIGHT);
        }
        player = new int[]{x, y};
        world[x][y] = Tileset.PLAYER;
    }

    public void MoveInt(int x, int y){
        int px = player[0];
        int py = player[1];
        if (world[px + x][py + y] == Tileset.FLOOR) {
            world[px + x][py + y] = Tileset.PLAYER;
            world[x][y] = Tileset.FLOOR;
            player = new int[]{px + x, py + y};
        }
    }

    public void move(char c){
        if (c == 'w') {
            MoveInt(0, 1);
        }
        if (c == 'a') {
            MoveInt(-1, 0);
        }
        if (c == 's') {
            MoveInt(0, -1);
        }
        if (c == 'd') {
            MoveInt(1, 0);
        }

    }


}
