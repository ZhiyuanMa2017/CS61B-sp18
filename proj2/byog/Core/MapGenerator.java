package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.*;

public class MapGenerator {
    static int WIDTH = 80;
    static int HEIGHT = 30;
    static Random rand;
    static ArrayList<Room> rooms;


    public static void generateRooms(TETile[][] world) {
        int roomnumber = RandomUtils.uniform(rand, 15, 25);
        rooms = new ArrayList<>(roomnumber);
        int i = 0;
        while (i < roomnumber) {
            int w = RandomUtils.uniform(rand, 1, 15);
            int h = RandomUtils.uniform(rand, 1, 15);
            Room thisroom = new Room(w, h, rand);
            int xp  = RandomUtils.uniform(rand, 3, WIDTH - w - 2);
            int yp  = RandomUtils.uniform(rand, 3, HEIGHT - h - 2);
            if (thisroom.isOverlap(world, new Position(xp, yp))) {
                continue;
            } else {
                thisroom.generateRoomFloor(world, new Position(xp, yp));
                rooms.add(thisroom);
                i = i + 1;
            }
        }
    }

    public static void generateHallways(TETile[][] world) {
        Map<Integer, Integer> connectmap = new HashMap<>();
        ArrayList<Integer> connecti = new ArrayList<>();
        for (int i = 0; i < rooms.size(); i++) {
            boolean connect = false;
            breakfor:
            for (int j = 0; j < rooms.size(); j++) {
                if (j != i) {
                    if (connectmap.containsKey(i)) {
                        if (connectmap.get(i) == j) {
                            continue;
                        }
                    }
                    if (connectmap.containsKey(j)) {
                        if (connectmap.get(j) == i) {
                            continue;
                        }
                    }
                    Room room1 = rooms.get(i);
                    Room room2 = rooms.get(j);
                    boolean whether = room2.generateHallway(world, room1);
                    if (whether) {
                        connectmap.put(i, j);
                        connecti.add(i);
                        connecti.add(j);
                        connect = true;
                        break breakfor;
                    }
                }
            }
            if (!connect) {
                for (Integer integer : connecti) {
                    Room room1 = rooms.get(i);
                    Room room2 = rooms.get(integer);
                    boolean whether = room2.generateHallway(world, room1);
                    if (whether) {
                        connect = true;
                        break;
                    }
                }
            }
            if (!connect) {
                Room room1 = rooms.get(i);
                room1.clearRoomFloor(world);
            }
        }
    }


    public static void generateWall(TETile[][] world) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (world[i][j] == Tileset.FLOOR) {
                    for (int k = -1; k < 2; k++) {
                        for (int l = -1; l < 2; l++) {
                            if (world[i + k][j + l] == Tileset.NOTHING) {
                                world[i + k][j + l] = Tileset.WALL;
                            }
                        }
                    }
                }
            }
        }
    }

}
