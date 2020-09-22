package byog.Core;


import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;
import java.util.Random;

public class World implements Serializable {
    private static final long serialVersionUID = 123123123123123L;
    Random r;
    int WIDTH;
    int HEIGHT;
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
        MapGenerator.generateRooms(world);
        MapGenerator.generateHallways(world);
        MapGenerator.generateWall(world);
        generatePlayer();
    }

    public void generatePlayer() {
        int x = RandomUtils.uniform(r, 0, WIDTH);
        int y = RandomUtils.uniform(r, 0, HEIGHT);
        while (world[x][y] != Tileset.FLOOR) {
            x = RandomUtils.uniform(r, 0, WIDTH);
            y = RandomUtils.uniform(r, 0, HEIGHT);
        }
        player = new int[]{x, y};
        world[x][y] = Tileset.PLAYER;
    }

    public void moveInt(int x, int y) {
        int px = this.player[0];
        int py = this.player[1];

        if (this.world[px + x][py + y].equals(Tileset.FLOOR)) {
            this.world[px + x][py + y] = Tileset.PLAYER;
            this.world[px][py] = Tileset.FLOOR;
            this.player = new int[]{px + x, py + y};
        }
    }

    public void move(char c) {
        if (c == 'w') {
            moveInt(0, 1);
        }
        if (c == 'a') {
            moveInt(-1, 0);
        }
        if (c == 's') {
            moveInt(0, -1);
        }
        if (c == 'd') {
            moveInt(1, 0);
        }

    }


}
