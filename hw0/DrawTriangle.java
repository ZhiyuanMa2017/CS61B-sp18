public class DrawTriangle {
   public static void DrawTriangle(int N){
      int row = 1;
 		while (row <= N) {
 			int col = 1;
 			while (col <row) {
 				System.out.print('*');
 				col = col + 1;
 			}
 			System.out.println('*');
 			row = row + 1;

 		}
 	}

   public static void main(String[] args) {
      DrawTriangle(10);
      
   }
}