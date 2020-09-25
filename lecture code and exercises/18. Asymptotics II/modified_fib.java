public class modified_fib{
	public void modified_fib(int n, int[] values){
		if(n == 0){
			values[0] = 0;
			return
		}
		if(n == 1){
			values[1] = 1;
			return 1;
		}
		else{
			int val = values[n];
			if(n == 0){
				val = modified_fib(n-1) + modified_fib(n-2);
				values[n] = val;
			}
			return val;
		}
	}  
}