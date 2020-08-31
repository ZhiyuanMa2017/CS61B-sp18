/**
problem: https://sp18.datastructur.es/materials/discussion/examprep02.pdf
solution: https://sp18.datastructur.es/materials/discussion/examprep02sol.pdf
*/
public class Shock {
	public static int bang;
	public static Shock baby;
	public Shock() {
		this.bang = 100;
	}
	public Shock (int num) {
		this.bang = num;  
		baby = starter();
		this.bang += num;
	}
	public static Shock starter() {
		Shock gear = new Shock();
		return gear;
	}
	public static void shrink(Shock statik) {
		statik.bang -= 1;
	}
	public static void main(String[] args) {
		Shock gear = new Shock(200);
		System.out.println(gear.bang); //300
		shrink(gear);
		shrink(starter());
		System.out.println(gear.bang); //99
	}
}