public class CasttheLine {

    /** Consider each line independently of all other lines.*/

    static void main(String[] args) {
        //Cat c = new Animal(); //compilation error
        Animal a = new Cat(); //runs successfully
        //Dog2 d = new Cat(); //compilation error
        //Tree t = new Animal(); //compilation error

        Animal aa = (Cat) new Cat(); //runs successfully
        Animal aaa = (Animal) new Cat(); //runs successfully
        Dog2 d = (Dog2) new Animal(); //runtime error
        //Cat c = (Cat) new Dog2(); //compilation error
        //Animal a = (Animal) new Tree(); //compilation error

        //compilation error,
        //runtime error, or
        //runs successfully
    }
}