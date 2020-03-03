 public class xing {
 	public static void main(String[] args) {
 		int row = 1;
 		int SIZE = 5;
 		while (row <= SIZE) {
 			int col = 1;
 			while (col <row) {
 				System.out.print('*');
 				col = col + 1;
 			}
 			System.out.println('*');
 			row = row + 1;

 		}
 	}
}

// col = col + 1;

// row = row + 1;
// System.out.print('*');
// System.out.println('*');
// System.out.println();
// while (col <= row) {
// while (col < row) {
// while (row < SIZE) {
