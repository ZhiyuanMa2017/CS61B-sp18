import edu.princeton.cs.algs4.Picture;

import java.awt.Color;

public class SeamCarver {
    private Picture picture;
    private int width;
    private int height;

    public SeamCarver(Picture picture) {
        this.picture = new Picture(picture);
        width = picture.width();
        height = picture.height();
    }

    public Picture picture() {
        return new Picture(picture);
    }
    // current picture

    public int width() {
        return width;
    }
    // width of current picture

    public int height() {
        return height;
    }
    // height of current picture

    public double energy(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            Color right = picture.get((x + 1) % width, y);
            Color left = picture.get((x - 1 + width) % width, y);
            Color up = picture.get(x, (y - 1 + height) % height);
            Color down = picture.get(x, (y + 1) % height);
            int rx = (right.getBlue() - left.getBlue()) * (right.getBlue() - left.getBlue())
                    + (right.getGreen() - left.getGreen()) * (right.getGreen() - left.getGreen())
                    + (right.getRed() - left.getRed()) * (right.getRed() - left.getRed());

            int ry = (up.getBlue() - down.getBlue()) * (up.getBlue() - down.getBlue())
                    + (up.getGreen() - down.getGreen()) * (up.getGreen() - down.getGreen())
                    + (up.getRed() - down.getRed()) * (up.getRed() - down.getRed());
            return rx + ry;
        } else {
            throw new IndexOutOfBoundsException();
        }

    }
    // energy of pixel at column x and row y

    public int[] findHorizontalSeam() {
        Picture transpose = new Picture(height, width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                transpose.set(i, j, picture.get(j, i));
            }
        }
        Picture temp = picture;
        picture = transpose;
        int tmp = width;
        width = height;
        height = tmp;
        int[] seam = findVerticalSeam();
        picture = temp;
        height = width;
        width = tmp;
        return seam;
    }
    // sequence of indices for horizontal seam

    public int[] findVerticalSeam() {
        int[] seam = new int[height];
        double[][] m = new double[width][height];
        double[][] e = new double[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                e[i][j] = energy(i, j);
            }
        }
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (j == 0) {
                    m[i][j] = e[i][j];
                } else {
                    if (i == 0) {
                        if (i + 1 < width) {
                            m[i][j] = e[i][j] + Math.min(m[i][j - 1], m[i + 1][j - 1]);
                        } else {
                            m[i][j] = e[i][j] + m[i][j - 1];
                        }
                    } else if (i == width - 1) {
                        m[i][j] = e[i][j] + Math.min(m[i][j - 1], m[i - 1][j - 1]);
                    } else {
                        m[i][j] = e[i][j]
                                + Math.min(m[i - 1][j - 1], Math.min(m[i][j - 1], m[i + 1][j - 1]));
                    }
                }
            }
        }
        int lasti = 0;
        for (int i = 0; i < width; i++) {
            if (m[i][height - 1] < m[lasti][height - 1]) {
                lasti = i;
            }
        }


        for (int j = height - 1; j > 0; j--) {
            seam[j] = lasti;
            if (lasti == 0) {
                if (lasti + 1 < width) {
                    if (m[lasti][j - 1] <= m[lasti + 1][j - 1]) {
                        continue;
                    } else {
                        lasti++;
                    }
                } else {
                    continue;
                }
            } else if (lasti == width - 1) {
                if (m[lasti][j - 1] <= m[lasti - 1][j - 1]) {
                    continue;
                } else {
                    lasti--;
                }
            } else {
                if (m[lasti - 1][j - 1] < m[lasti][j - 1]) {
                    if (m[lasti - 1][j - 1] <= m[lasti + 1][j - 1]) {
                        lasti--;
                    } else {
                        lasti++;
                    }
                } else {
                    if (m[lasti][j - 1] <= m[lasti + 1][j - 1]) {
                        continue;
                    } else {
                        lasti++;
                    }
                }
            }
        }
        seam[0] = lasti;
        return seam;
    }
    // sequence of indices for vertical seam

    public void removeHorizontalSeam(int[] seam) {
        SeamRemover.removeHorizontalSeam(picture, findHorizontalSeam());
    }
    // remove horizontal seam from picture

    public void removeVerticalSeam(int[] seam) {
        SeamRemover.removeVerticalSeam(picture, findVerticalSeam());
    }

    // remove vertical seam from picture
}
