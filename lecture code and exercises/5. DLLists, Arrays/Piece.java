/**
problem: http://www.kartikkapur.com/documents/mt1.pdf#page=14
solution: http://www.kartikkapur.com/documents/mt1sols.pdf#page=17
*/

public class Piece{

	public int longitude;
	public int latitude;

	public Piece(int x, int y) {
		longitude = x;
		latitude = y;
	}

	public Piece[][] groupByLat(Piece[] p) {
		int width = (int) Math.sqrt(p.length);
		Piece[][] latSort = new Piece[width][width];
		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j < latSort.length; j++) {
				if (latGroup[j][0] == null){
					latGroup[j][0] = p[i];
					break;
				}
				else if (latGroup[j][0].latitude == p[i].latitude){
					int counter;
					for (counter = 0; counter < width; counter ++) {
						if (latGroup[j][counter] == null) {
							break;
						}
					}
					latSort[j][counter] = p[i];
					break;
				}
			}
		}
		return latSort;
	}

	/**Takes in a 2d array of Pieces and returns it sorted in the correct order 
	   such that the row that contains the smallest latitudes is at the 0th index.*/
	public Piece[][] sortbyLat(Piece[][] p) {
	/** 可参考 
		https://github.com/yunhaj47/cs61b-remote/blob/77cfbaec116eb2faa570ab5ec09fd75a6bdae71b/lectureCode-sp18/exercises/SLList/src/Arrrrghrays.java
		*/
	}


	/** Takes in a 1D array of Pieces and half sorts them all by longitude. 
	    In this problem, the term half sort means that the array is fully 
	    sorted except the first half of the sorted array is switched with 
	    the second half of the sorted array.*/
	public Piece[] sortHalfLong(Piece[] p) {
	/** 可参考 
		https://github.com/yunhaj47/cs61b-remote/blob/77cfbaec116eb2faa570ab5ec09fd75a6bdae71b/lectureCode-sp18/exercises/SLList/src/Arrrrghrays.java
		*/
	}

	public Piece[][] solvePuzzle(Piece[] scattered) {
		Piece[][] grouped = groupByLat(scattered);
		int upper = (int) Math.ceil((double) grouped.length/2);
		int lower = (int) Math.floor((double) grouped.length/2);
		for (Piece[] row : grouped){
			Piece[] halfsort = sortHalfLong(row);
			Piece[] tmp = new Piece[row.length];
			System.arraycopy(halfsort, 0, tmp, lower, upper);
			System.arraycopy(halfsort, upper, tmp, 0, lower);
			row = tmp;
			//答案这里没有将结果写进row里，是不是少了一步。
		}
		Piece[][] sorted = sortbyLat(grouped);
		return sorted;
	}
}