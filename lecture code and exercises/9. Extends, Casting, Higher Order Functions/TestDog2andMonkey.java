public class TestDog2andMonkey {
    public static void main(String[] args) {
        Monkey jimmy = new Monkey("Jimmy");
        Dog2 limmy = (Dog2) jimmy; //java: incompatible types: Monkey cannot be converted to Dog
        Monkey orangutan = new Monkey("fruitful");
        Dog2 mangotan = (Dog2)(Animal) orangutan;
    }
}
