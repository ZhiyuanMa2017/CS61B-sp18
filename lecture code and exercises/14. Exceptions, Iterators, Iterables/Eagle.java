import java.io.IOException;

public class Eagle {
    static String today;
    // IOException: checked exception

    public static void gulgate() {
        // Catch
        try {
            if (today.equals("Thursday")) {
                throw new IOException("hi");
            }
        } catch (Exception e) {
            System.out.println("psych!");
        }
    }

    public static void gulgate2() throws IOException {
        // Specify
        // this method might throw a checked exception!!!
        // If a method uses a dangerous method, it becomes dangerous itself.
        if (today.equals("Thursday")) {
            throw new IOException("hi");
        }
    }

    public static void main(String[] args) {

        //Eagle.gulgate2(); // Unhandled exception: java.io.IOException
        try {
            gulgate2();
        } catch (IOException e) {
            System.out.println("Averted!");
        }
    }
}
