/**
problem: https://sp18.datastructur.es/materials/discussion/examprep02.pdf#page=2
solution: https://sp18.datastructur.es/materials/discussion/examprep02sol.pdf#page=2
*/
 public class Horse {
 	Horse same;
 	String jimmy;

 	public Horse(String lee) {
 		jimmy = lee;
 	}

 	public Horse same(Horse horse) {
 		if (same != null) {
 			Horse same = horse;
 			same.same = horse;
 			same = horse.same;
 		}
 		// System.out.println(same.same.jimmy);
 		return same.same; // 这里的same是指this.same，不是刚创建的same
 	}

 	public static void main(String[] args) {
 		Horse horse = new Horse("youve been");
 		Horse cult = new Horse("horsed");
 		cult.same = cult;
 		cult = cult.same(horse);
 		System.out.println(cult.jimmy);// horsed
 		System.out.println(horse.jimmy);// youve been
 	}
 }