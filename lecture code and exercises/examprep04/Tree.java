public class Tree {
    protected String name;
    protected int age;

    public Tree() {

    }
    public Tree(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void bark(){
        System.out.println("Tree bark");
    }
}
