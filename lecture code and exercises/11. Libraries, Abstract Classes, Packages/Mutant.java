public class Mutant extends Tree implements Dog{
    public static void main(String[] args) {
        Mutant m = new Mutant();
        Tree t = new Mutant();
        Dog d = new Mutant();
        m.bark();
        t.bark();
        d.bark();
    }
}