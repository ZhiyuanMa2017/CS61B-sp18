public class max{
	public static int max(int[] m) {
		int i = 0;
		int maxnumber = 0;
		while(i<m.length){
			if (maxnumber < m[i]){
				maxnumber = m[i];
			}
			i = i + 1;
		}
		return maxnumber;
	}
	public static void main(String[] args) {
		int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};   
		System.out.println(max(numbers));

	}
}