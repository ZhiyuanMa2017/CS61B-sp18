public interface Dog {
    default void bark() {
        System.out.println("Dog Bark");
    }
}