/**
problem: https://tbp.berkeley.edu/exams/4695/download/#page=9
solution: https://tbp.berkeley.edu/exams/4659/download/#page=12
get help from: https://github.com/junhaowww/cs61b-sp18/blob/e18c6f7b99b5687b5e85ac5de6cfeec20e66583c/classcode/w2/HigherOrderFunctionsAndArrays.java
*/
HigherOrderFunctionsandArrays

public interface NullSafeStringComparator {
	/** Returns a negative number if s1 is ‘less than’ s2, 0 if ‘equal’,
	  * and a positive number otherwise. Null is considered less than
	  * any String. If both inputs are null, return 0. */
	public int compare(String s1, String s2);
}

class LengthComparator implements NullSafeStringComparator{
	public int compare(String s1, String s2) {
		if(s1 == null) && (s2 == null) {
			return 0;
		} 
		if(s1 == null){
			return -1;
		}
		if(s2 == null){
			reurn 1;
		}
		return s1.length() - s2.length();
	}
}

public static String max(String[] a, NullSafeStringComparator sc){
	String maxstr = a[0];
	for (int i = 0; i < a.length; i += 1) {
		if(sc.compare(a[i], maxstr) > 0) {
			maxstr = a[i];
		}
	}
	return maxstr;
}

public static String[][] step(String[][] arr) {
		/* Recall: All String references in stepped are null by
	   default, so the edges are correct on initialization */
	String[][] stepped = new String[arr.length][arr[0].length];
	for (int i = 1; i < arr.length - 1; i += 1) {
		for (int j = 1; j < arr[0].length - 1; j += 1) {
			String[] temp = new String[9];
			int count = 0;
			for (int k = -1; k <= 1; k += 1) {
				for (int m = -1; m <= 1; m += 1) {
					temp[count] = arr[i+k][j+m];
					count += 1;
				}
			}
			stepped[i][j] = max(temp);
		}
		return stepped;
	}
