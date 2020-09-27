public class print_fib2{
	public void print_fib(int n){
		for(int i = 0; i < n; i++i){
			System.out.println(fib(n));
		}
	}

	public int fib(int n){
		if(n <= 0){
			return 0;
		}
		elif(n == 1){
			return 1;
		}
		else{
			return fib(n-1) + fib(n-2);
		}
	}
}