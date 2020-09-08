public class Corgi extends Dog {
    public void bark(Corgi c) { /* Method B */ }

    @Override
    public void bark(Dog d) { /* Method C */ }

    public void play(Dog d) { /* Method D */ }

    public void play(Corgi c) { /* Method E */ }

    public static void main(String[] args) {
        Dog d = new Corgi();
        Corgi c = new Corgi();

        //d.play(d);
        // Compile - Error
        //d.play(c);
        // Compile - Error
        c.play(d);
        // D
        c.play(c);
        // E

        c.bark(d);
        // C
        c.bark(c);
        // B
        d.bark(d);
        // C
        d.bark(c);
        // C
    }
}