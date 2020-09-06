public class Flatten {
	public static int[] flatten(int[][] x) {
		int totalLength = 0;

		for(int i = 0; i < x.length; i++) {
			totalLength = totalLength + x[i].length;
		}

		int[] a = new int[totalLength];
		int aIndex = 0;

		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[i].length; j++) {
				a[aIndex] = x[i][j];
				aIndex = aIndex + 1;
			}
		}
		return a;
	}
}