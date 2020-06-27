public class DogLauncher{
	public static void main(String[] args){
		Dog d = new Dog(15);
		Dog d2 = new Dog(100);
		Dog bigger = Dog.maxDog(d,d2); /*static methods*/
		bigger.makeNoise();
		Dog bigger2 = d.maxDog2(d2); /*instance methods */
		bigger2.makeNoise();
		// d.makeNoise();

		Dog smallDog;
		new Dog(20);
		smallDog=new Dog(5);
		Dog hugeDog = new Dog(150);
		smallDog.makeNoise();
		hugeDog.makeNoise();

		Dog[] dogs=new Dog[2];
		dogs[0]=new Dog(8);
		dogs[1]=new Dog(20);
		dogs[0].makeNoise();

		System.out.println(Dog.binomen);
			}
}