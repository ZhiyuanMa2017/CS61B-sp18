public class modified_fib{
	public void print_fib(int n){
		for(int i = 0; i < n; i++i){
			System.out.println(fib(i));
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