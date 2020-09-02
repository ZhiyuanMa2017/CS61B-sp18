/**
problem: https://sp18.datastructur.es/materials/discussion/disc03.pdf#page=2
solution: https://sp18.datastructur.es/materials/discussion/disc03sol.pdf#page=3
*/
public class Arrays{
	public static int[] insert(int[] arr, int item, int position) {
		position = MAth.min(arr.length, position);
		Int[] result = new Int[arr.length + 1];
		System.arraycopy(arr, 0, result, 0, position);
		result[position] = item;
		System.arraycopy(arr, position, result, position + 1,arr.length - position);
		return result;
	}

	public static void reverse(int[] arr) {
		for (int i = 0; i < arr.length/2, i++){
			int j = arr.length - i - 1;
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		} 
	}

	public static int[] replicate(int[] arr) {
		int totallength = 0;
		for (int item : arr) {
			totallength = totallength + item;
		}
		int result = new int[totallength];
		int i = 0;
		for (int item : arr) {
			for(int counter = 0; counter < item; counter++) {
				result[i] = item;
				i = i + 1;
			}
		}
		return result;
}