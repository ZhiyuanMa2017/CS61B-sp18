package byog.lab6;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Font;


public class StdDrawDemo {
    public static void main(String[] args) {
//        StdDraw.setPenRadius(0.05);
//        StdDraw.setPenColor(StdDraw.BLUE);
//        StdDraw.point(0.5, 0.5);
//        StdDraw.setPenColor(StdDraw.MAGENTA);
//        StdDraw.line(0.2, 0.2, 0.8, 0.2);

        String s = "Your input: 123456";
        s = s.substring(12);
        StdDraw.clear();
        //StdDraw.setCanvasSize();
        StdDraw.setPenColor(StdDraw.BLUE);
        Font font = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(0.5, 0.5, s);
        StdDraw.show();
        StdDraw.pause(1000);
        StdDraw.clear();

    }
}
