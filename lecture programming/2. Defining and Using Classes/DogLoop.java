public class DogLoop{
	public static void main(String[] args){
		Dog smallDog = new Dog(5);
		Dog meidumDog = new Dog(25);
		Dog hugeDog = new Dog(150);

		Dog[] manyDogs = new Dog[4];
		manyDogs[0] = smallDog;
		manyDogs[1] = hugeDog;
		manyDogs[2] = new Dog(130);

		int i = 0;
		while(i < manyDogs.length){
			Dog.maxDog(manyDogs[i],meidumDog).makeNoise();
			i = i + 1;
		}
	}
}