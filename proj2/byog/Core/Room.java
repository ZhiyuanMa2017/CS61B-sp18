package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;


import java.util.Random;

public class Room {
    private int width;
    private int height;
    private Position position;
    private Random rand;

    Room(int w, int h, Random rand) {
        width = w;
        height = h;
        position = new Position(0, 0);
        this.rand = rand;
    }

    public void generateRoomFloor(TETile[][] world, Position p) {
        position.x = p.x;
        position.y = p.y;
        int floorx = p.x;
        int floory = p.y;
        for (int i = 0; i < height; i++) {
            addRow(world, new Position(floorx, floory), this.width);
            floory += 1;
        }
    }

    public void clearRoomFloor(TETile[][] world) {
        int floorx = position.x;
        int floory = position.y;
        for (int i = 0; i < height; i++) {
            addNothing(world, new Position(floorx, floory), this.width);
            floory += 1;
        }
    }

    public static void addNothing(TETile[][] world, Position p, int width) {
        for (int xi = 0; xi < width; xi += 1) {
            int xCoord = p.x + xi;
            int yCoord = p.y;
            world[xCoord][yCoord] = Tileset.NOTHING;
        }
    }

    public static void addRow(TETile[][] world, Position p, int width) {
        for (int xi = 0; xi < width; xi += 1) {
            int xCoord = p.x + xi;
            int yCoord = p.y;
            world[xCoord][yCoord] = Tileset.FLOOR;
        }
    }

    public static void addColumn(TETile[][] world, Position p, int height) {
        for (int yi = 0; yi < height; yi += 1) {
            int xCoord = p.x;
            int yCoord = p.y + yi;
            world[xCoord][yCoord] = Tileset.FLOOR;
        }
    }

    public boolean isOverlap(TETile[][] world, Position p) {
        for (int i = -3; i < width + 3; i++) {
            for (int j = -3; j < height + 3; j++) {
                if (world[p.x + i][p.y + j] != Tileset.NOTHING) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isRowNothing(TETile[][] world, Position p, int rowlen) {
        for (int i = 0; i < rowlen; i++) {
            if (world[p.x + i][p.y] == Tileset.FLOOR) {
                return false;
            }
        }
        return true;
    }
    public boolean isColumnNothing(TETile[][] world, Position p, int columnlen) {
        for (int i = 0; i < columnlen; i++) {
            if (world[p.x][p.y + i] == Tileset.FLOOR) {
                return false;
            }
        }
        return true;
    }

    public boolean generateHallway(TETile[][] world, Room room2) {
        int thisx = position.x;
        int thisy = position.y;
        int thatx = room2.position.x;
        int thaty = room2.position.y;
        int thatwidth = room2.width;
        int thatheight = room2.height;
        if (checkoverlap(thisx, width, thatx, thatwidth)) {
            int hallwayx = RandomUtils.uniform(
                    rand, Math.max(thisx, thatx),
                    Math.min(thisx + width - 1, thatx + thatwidth - 1) + 1);
            if (thisy > thaty) {
                int ylength = thisy - thaty - thatheight + 1;
                if (isColumnNothing(world,
                        new Position(hallwayx, thaty + thatheight), ylength - 1)) {
                    addColumn(world, new Position(hallwayx, thaty + thatheight), ylength);
                    return true;
                }
            }
            if (thisy < thaty) {
                int ylength = thaty - thisy - height + 1;
                if (isColumnNothing(world, new Position(hallwayx, thisy + height), ylength - 1)) {
                    addColumn(world, new Position(hallwayx, thisy + height), ylength);
                    return true;
                }
            }

        }
        if (checkoverlap(thisy, height, thaty, thatheight)) {
            int hallwayy = RandomUtils.uniform(rand,
                    Math.max(thisy, thaty),
                    Math.min(thisy + height - 1, thaty + thatheight - 1) + 1);
            if (thisx > thatx) {
                int xlength = thisx - thatx - thatwidth + 1;
                if (isRowNothing(world, new Position(thatx + thatwidth, hallwayy), xlength - 1)) {
                    addRow(world, new Position(thatx + thatwidth, hallwayy), xlength);
                    return true;
                }
            }
            if (thisx < thatx) {
                int xlength = thatx - thisx - width + 1;
                if (isRowNothing(world, new Position(thisx + width, hallwayy), xlength - 1)) {
                    addRow(world, new Position(thisx + width, hallwayy), xlength);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkoverlap(int p1, int len1, int p2, int len2) {
        int min1 = p1;
        int max1 = p1 + len1 - 1;
        int min2 = p2;
        int max2 = p2 + len2 - 1;
        if (min1 > max2 || min2 > max1) {
            return false;
        }
        return true;
    }
}
