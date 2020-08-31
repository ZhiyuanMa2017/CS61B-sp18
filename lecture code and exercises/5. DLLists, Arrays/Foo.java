/**
problem: https://sp18.datastructur.es/materials/discussion/examprep02.pdf#page=3
solution: https://sp18.datastructur.es/materials/discussion/examprep02sol.pdf#page=3
*/
public class Foo {
  public int x, y;
  
  public Foo (int x, int y) {
	this.x = x;
	this.y = y;
	}   
	
  public static void switcheroo (Foo a, Foo b) { 
  	/** get help from https://github.com/TomYang1993/javacs/blob/4afebe5af5458fd7424cfb869b3f3b25c5bfb5ab/week2/exam_prep/Foo.java*/
	/**
	  * classical one: switch doesn't switch foobar with baz, pass by value a, b
	  * copies the addresses of foobar and baz variable, but all the function below
	  * is doing is just swap a,b values, which are addresses copied from foobar and
	  * baz, it only affect a,b references, not affecting foobar, baz values. imaging
	  * a points to foobar variable and b points to baz variable, after the function,
	  * a points to baz, b points to foobar, foobar and baz points to the same
	  * location as before.
	  */	   	
	Foo temp = a;
	a = b;
	b = temp;
	}
	
  public static void fliperoo (Foo a, Foo b) {
	Foo temp = new Foo(a.x, a.y);
		a.x = b.x;
		a.y = b.y;
		b.x = temp.x;
		b.y = temp.y;
	}
	
	public static void swaperoo (Foo a, Foo b) {
	Foo temp = b;
		a.x = b.x;
		a.y = b.y;
		b.x = temp.x;
		b.y = temp.y;
	}
	
	public static void main (String[] args) {
	Foo foobar = new Foo(10, 20);
	Foo baz = new Foo(30, 40);
		switcheroo(foobar, baz);  // foobar.x: 10 foobar.y: 20 baz.x: 30 baz.y: 40  
		fliperoo(foobar, baz);    // foobar.x: 30 foobar.y: 40 baz.x: 10 baz.y: 20  
		swaperoo(foobar, baz);    // foobar.x: 10 foobar.y: 20 baz.x: 10 baz.y: 20  
	}
}